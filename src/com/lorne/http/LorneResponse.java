package com.lorne.http;

import java.io.OutputStream;

/**
 * @Description:  lorne response
 * @author zhangxl
 * @date 2017��4��9�� ����10:29:39 
 * @version Ver 1.0
 */
public class LorneResponse {

	private OutputStream out ;
	
	/**
	 * ���캯�������������
	 * @param out
	 */
	public LorneResponse(OutputStream out){
		this.out = out;
	}
	
	/**
	 * @Description: ��ͻ��˷������� 
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:33:13
	 */
	public void write(String outStr) throws Exception{
		out.write(outStr.getBytes());
		out.flush();
	}
	
}
