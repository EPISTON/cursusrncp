package com.courtalon.MyMessageApp.ws;

import javax.jws.WebService;

@WebService(endpointInterface="com.courtalon.MyMessageApp.ws.MessageService")
public class MessageServiceImpl implements MessageService {

	@Override
	public String salutation(String name) {
		return "hello from cxf and spring, " + name;
	}

}
