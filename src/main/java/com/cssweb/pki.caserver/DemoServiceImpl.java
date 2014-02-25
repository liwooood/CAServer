package com.cssweb.pki.caserver;
public class DemoServiceImpl implements DemoService {

	public String sayHello(String name) {
		String r ="Hello " + name; 
		return r;
	}
}