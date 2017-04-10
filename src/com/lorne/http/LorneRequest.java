package com.lorne.http;

import java.io.InputStream;
import java.util.Map;

import com.sun.xml.internal.fastinfoset.util.StringArray;

/**
 * @Description:  lorne 的request
 * @author zhangxl
 * @date 2017年4月9日 上午10:29:11 
 * @version Ver 1.0
 */
public class LorneRequest {

	private String url ;
	private String method;
	
	/**
	 * 构造函数 传入输入流
	 * @param in
	 */
	public LorneRequest(InputStream in){
		//获取输入参数，如url，method
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
			//截取method
			this.setMethod(temp[0]);
			//截取URl
			this.setUrl(temp[1]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description: 获取请求地址 
	 * @return
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:30:23
	 */
	public String getUrl(){
		return this.url;
	}
	
	/**
	 * @Description: 获取请求类型
	 * @return
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:31:00
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
	 * @Description: 获取请求的参数 
	 * @return
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:31:58
	 */
	public Map<String, String> getParmerts(){
		return null;
	}
}
