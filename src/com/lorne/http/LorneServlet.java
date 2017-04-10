package com.lorne.http;
/**
 * @Description: lorne 的servlet 
 * @author zhangxl
 * @date 2017年4月9日 上午10:11:23 
 * @version Ver 1.0
 */
public abstract class LorneServlet {

	/**
	 * @Description: servlet doService 方法
	 * @param request
	 * @param response
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:36:21
	 */
	public void deService(LorneRequest request,LorneResponse response) throws Exception{
		if("GET".equalsIgnoreCase(request.getMethod())){
			this.doGet(request,response);
		}else if("POST".equalsIgnoreCase(request.getMethod())){
			this.doPost(request,response);
		}
	}
	/**
	 * @Description: 执行GET方法 
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:36:55
	 */
	public abstract void doGet(LorneRequest request,LorneResponse response) throws Exception;
	/**
	 * @Description: 执行POST方法 
	 * @author zhangxl
	 * @date 2017年4月9日 上午10:37:08
	 */
	public abstract void doPost(LorneRequest request,LorneResponse response) throws Exception;
}
