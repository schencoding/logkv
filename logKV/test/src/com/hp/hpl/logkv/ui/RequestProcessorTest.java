package com.hp.hpl.logkv.ui;

public class RequestProcessorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		UIRequestProcessor rp = new UIRequestProcessor();
		rp.start();
		System.out.println("RequestProcessor started.");

	}

}
