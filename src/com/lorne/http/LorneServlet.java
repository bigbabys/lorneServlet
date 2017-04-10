package com.lorne.http;
/**
 * @Description: lorne ��servlet 
 * @author zhangxl
 * @date 2017��4��9�� ����10:11:23 
 * @version Ver 1.0
 */
public abstract class LorneServlet {

	/**
	 * @Description: servlet doService ����
	 * @param request
	 * @param response
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:36:21
	 */
	public void deService(LorneRequest request,LorneResponse response) throws Exception{
		if("GET".equalsIgnoreCase(request.getMethod())){
			this.doGet(request,response);
		}else if("POST".equalsIgnoreCase(request.getMethod())){
			this.doPost(request,response);
		}
	}
	/**
	 * @Description: ִ��GET���� 
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:36:55
	 */
	public abstract void doGet(LorneRequest request,LorneResponse response) throws Exception;
	/**
	 * @Description: ִ��POST���� 
	 * @author zhangxl
	 * @date 2017��4��9�� ����10:37:08
	 */
	public abstract void doPost(LorneRequest request,LorneResponse response) throws Exception;
}
