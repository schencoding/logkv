package com.hp.hpl.logkv.eventsource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.hp.hpl.logkv.model.FieldType;

public class EventGeneratorHelper {
	public static int NUM_OF_WORDS = 106181;
	public static String[] WORDS = new String[NUM_OF_WORDS];
	
	static{
		try {			
			FileReader fr = new FileReader("input/words.txt");
			BufferedReader br = new BufferedReader(fr);
			String word = null;
			int count = 0;
			while((word = br.readLine()) != null){
				WORDS[count] = word;
				count ++;
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object generate(Random random, FieldType fieldType){
		Object obj = null;
		switch(fieldType){
		case BOOLEAN:
			obj = random.nextBoolean();
			break;
		case BYTE:
			
			break;
		case CHAR:
			//???;
			break;
		case INT:
			obj = random.nextInt();
			break;
		case LONG:
			obj = random.nextLong();
			break;
		case FLOAT:
			obj = random.nextFloat();
			break;
		case DOUBLE:
			obj = random.nextDouble();
			break;
		case STRING:
			int index = random.nextInt(NUM_OF_WORDS);
			obj = WORDS[index];
			break;
		}
		return obj;
	}

}
