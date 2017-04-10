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
 * @Description:手写tomcat 
 * @author zhangxl
 * @date 2017年4月9日 上午9:49:48 
 * @version Ver 1.0
 */
public class LorneTomcat {

	private int port =8080;
	//获取webPath
	private String webPath = this.getClass().getResource("/").getPath();
	private Properties webxml = new Properties();
	//存放servlet 配置
	Map<Pattern, Class<?>> servletMapping = new HashMap<Pattern, Class<?>>();
	
	public LorneTomcat(){
		
	}
	public LorneTomcat(int port){
		this.port = port;
	}
	/**
	 * @Description: 启动tomcat 
	 * @author zhangxl
	 * @date 2017年4月9日 上午9:51:23
	 */
	public void start(){
		try {
			//获取servlet配置
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
			//启动tcp/ip socket
			ServerSocket server = new ServerSocket(this.port);
			System.out.println("tomcat容器已启动 监听端口："+this.port);
			while (true) {
				Socket client = server.accept();
				//客户端输入
				InputStream in = client.getInputStream();
				//客户端输出
				OutputStream out =client.getOutputStream();
				try {
					//初始化 request 、response 
					//request 就是inputStream的封装
					//response 就是outSteam 的封装
					LorneRequest request = new LorneRequest(in);
					LorneResponse response = new LorneResponse(out);
					//初始化 servlet
					//根据用户输入的URL 自动匹配servlet
					String userUrl = request.getUrl();
					boolean falg = false;
					for (Entry<Pattern, Class<?>> entry : servletMapping.entrySet()) {
						//根据URL匹配正则
						if(entry.getKey().matcher(userUrl).matches()){
							falg = true;
							//初始化对象
							LorneServlet servlet = (LorneServlet)entry.getValue().newInstance();
							servlet.doGet(request, response);
						}
						if(falg)
							continue;
					}
					if(!falg){
						out.write("404------找不到要访问的地址".getBytes());
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
