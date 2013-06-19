package com.hp.hpl.logkv.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ResponseTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ResourceStatusResponse rsr = new ResourceStatusResponse(1);
		rsr.setCPUStatus("16.158.159.16", 8);
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data/test"));
			oos.writeObject(rsr);
			oos.flush();
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data/test"));
			Object obj = ois.readObject();
			obj.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
