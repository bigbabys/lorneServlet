package com.lorne.main;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.regex.Pattern;

import com.lorne.http.LorneRequest;
import com.lorne.http.LorneResponse;
import com.lorne.http.LorneServlet;
import com.lorne.servlet.TestServlet;

/**
 * @Description:��дtomcat 
 * @author zhangxl
 * @date 2017��4��9�� ����9:49:48 
 * @version Ver 1.0
 */
public class LorneTomcat {

	private int port =8080;
	//��ȡwebPath
	private String webPath = this.getClass().getResource("/").getPath();
	private Properties webxml = new Properties();
	//���servlet ����
	Map<Pattern, Class<?>> servletMapping = new HashMap<Pattern, Class<?>>();
	
	public LorneTomcat(){
		
	}
	public LorneTomcat(int port){
		this.port = port;
	}
	/**
	 * @Description: ����tomcat 
	 * @author zhangxl
	 * @date 2017��4��9�� ����9:51:23
	 */
	public void start(){
		try {
			//��ȡservlet����
			FileInputStream fin = new FileInputStream(webPath+"/web.properties");
			webxml.load(fin);
			for(Object k : webxml.keySet()){
				String key = k.toString();
				if(key.endsWith(".url")){
					String servletName = key.replaceAll(".url$", "");
					String servletUrl = webxml.getProperty(servletName+".url");
					String className = webxml.getProperty(servletName+".className");
					
					Pattern servletPattern = Pattern.compile(servletUrl);
					Class<?> servletClass = Class.forName(className);
					servletMapping.put(servletPattern, servletClass);
				}
			}
			//����tcp/ip socket
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("tomcat���������� �����˿ڣ�"+this.port);
			while (true) {
				Socket client = server.accept();
				//�ͻ�������
				InputStream in = client.getInputStream();
				//�ͻ������
				OutputStream out =client.getOutputStream();
				try {
					//��ʼ�� request ��response 
					//request ����inputStream�ķ�װ
					//response ����outSteam �ķ�װ
					LorneRequest request = new LorneRequest(in);
					LorneResponse response = new LorneResponse(out);
					//��ʼ�� servlet
					//�����û������URL �Զ�ƥ��servlet
					String userUrl = request.getUrl();
					boolean falg = false;
					for (Entry<Pattern, Class<?>> entry : servletMapping.entrySet()) {
						//����URLƥ������
						if(entry.getKey().matcher(userUrl).matches()){
							falg = true;
							//��ʼ������
							LorneServlet servlet = (LorneServlet)entry.getValue().newInstance();
							servlet.doGet(request, response);
						}
						if(falg)
							continue;
					}
					if(!falg){
						out.write("404------�Ҳ���Ҫ���ʵĵ�ַ".getBytes());
						out.flush();
					}
				} catch (Exception e) {
					out.write(("500---------"+e.getMessage()).getBytes());
					out.flush();
				}finally{
					in.close();
					out.close();
					client.close();
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new LorneTomcat().start();
	}
}
