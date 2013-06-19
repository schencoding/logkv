package com.hp.hpl.logkv.util;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.hp.hpl.logkv.model.FieldType;

public class SerializeHelper {
	
	public static void write(DataOutput out, Object obj, FieldType fieldType) throws IOException{
		switch(fieldType){
		case BOOLEAN:
			out.writeBoolean(((Boolean) obj).booleanValue());
			break;
		case BYTE:
			out.writeByte(((Byte) obj).intValue());
			break;
		case CHAR:
			//???;
			break;
		case INT:
			out.writeInt(((Integer)obj).intValue());
			break;
		case LONG:
			out.writeLong(((Long)obj).longValue());
			break;
		case FLOAT:
			out.writeFloat(((Float)obj).floatValue());
			break;
		case DOUBLE:
			out.writeDouble(((Double)obj).doubleValue());
			break;
		case STRING:
			String str = (String) obj;
			out.writeUTF(str);
			break;
		}
	}
	
	public static Object read(DataInput in, FieldType fieldType) throws IOException{
		Object obj = null;
		switch(fieldType){
		case BOOLEAN:
			obj = in.readBoolean();
			break;
		case BYTE:
			obj = in.readByte();
			break;
		case CHAR:
			obj = in.readChar();
			break;
		case INT:
			obj = in.readInt();
			break;
		case LONG:
			obj = in.readLong();
			break;
		case FLOAT:
			obj = in.readFloat();
			break;
		case DOUBLE:
			obj = in.readDouble();
			break;
		case STRING:
			String str = in.readUTF();
			obj = str;
			break;
		}
		return obj;
	}
	
	public static byte[] longToByte(long number){
		long temp = number;
		byte[] bytes = new byte[8];
		for(int i=0; i<bytes.length; i++){
			bytes[i] = new Long(temp & 0xff).byteValue();
			temp = temp >> 8;
		}		
		return bytes;
	}
	
	public static long bytesToLong(byte[] bytes){
		long s = 0;
		long s0 = bytes[0]&0xff;
		long s1 = bytes[1]&0xff;
		long s2 = bytes[2]&0xff;
		long s3 = bytes[3]&0xff;
		long s4 = bytes[4]&0xff;
		long s5 = bytes[5]&0xff;
		long s6 = bytes[6]&0xff;
		long s7 = bytes[7]&0xff;
		
		s1 <<= (8*1);
		s2 <<= (8*2);
		s3 <<= (8*3);
		s4 <<= (8*4);
		s5 <<= (8*5);
		s6 <<= (8*6);
		s7 <<= (8*7);
		
		s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
		return s;
	}
}
