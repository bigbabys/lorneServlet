package com.lorne.http;

import java.io.OutputStream;

/**
 * @Description:  lorne response
 * @author zhangxl
 * @date 2017年4月9日 上午10:29:39 
 * @version Ver 1.0
 */
public class LorneResponse {

	private OutputStream out ;
	
	/**
	 * 构造函数，传入输出流
	 * @param out
	 */
	public LorneResponse(OutputStream out){
		this.out = out;
	}
	
	/**
	 * @Description: 向客户端返回内容 
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:33:13
	 */
	public void write(String outStr) throws Exception{
		out.write(outStr.getBytes());
		out.flush();
	}
	
}
