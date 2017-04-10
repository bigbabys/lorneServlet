package com.lorne.http;

import java.io.InputStream;
import java.util.Map;

import com.sun.xml.internal.fastinfoset.util.StringArray;

/**
 * @Description:  lorne ��request
 * @author zhangxl
 * @date 2017��4��9�� ����10:29:11 
 * @version Ver 1.0
 */
public class LorneRequest {

	private String url ;
	private String method;
	
	/**
	 * ���캯�� ����������
	 * @param in
	 */
	public LorneRequest(InputStream in){
		//��ȡ�����������url��method
		try {
			String content = "";
			byte[] buff = new byte[1024];
			int len = in.read(buff);
			if(len>0){
				content  = new String(buff,0,len);
			}
			System.out.println(content);
			String line = content.split("\n")[0];
			String[] temp = line.split("\\s");
			//��ȡmethod
			this.setMethod(temp[0]);
			//��ȡURl
			this.setUrl(temp[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: ��ȡ�����ַ 
	 * @return
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:30:23
	 */
	public String getUrl(){
		return this.url;
	}
	
	/**
	 * @Description: ��ȡ��������
	 * @return
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:31:00
	 */
	public String getMethod(){
		return this.method;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @Description: ��ȡ����Ĳ��� 
	 * @return
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:31:58
	 */
	public Map<String, String> getParmerts(){
		return null;
	}
}
