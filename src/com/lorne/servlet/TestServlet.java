package com.lorne.servlet;

import com.lorne.http.LorneRequest;
import com.lorne.http.LorneResponse;
import com.lorne.http.LorneServlet;

/**
 * @Description: ����servlet
 * @author zhangxl
 * @date 2017��4��9�� ����10:49:46 
 * @version Ver 1.0
 */
public class TestServlet extends LorneServlet {

	@Override
	public void doGet(LorneRequest request, LorneResponse response) throws Exception {
		this.doPost(request, response);
	}

	@Override
	public void doPost(LorneRequest request, LorneResponse response) throws Exception {
		response.write(request.getMethod()+" this is my servlet ..");
	}


}
