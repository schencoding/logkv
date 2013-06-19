package com.hp.hpl.logkv.model;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.hp.hpl.logkv.query.FieldFilter;
import com.hp.hpl.logkv.query.Query;
import com.hp.hpl.logkv.queryprocess.ResultSet;
import com.hp.hpl.logkv.queryprocess.ResultSet.Record;
import com.hp.hpl.logkv.util.CompareHelper;
import com.hp.hpl.logkv.util.SerializeHelper;
import com.hp.hpl.logkv.util.Util;

import javaewah.EWAHCompressedBitmap;

public class KVColumnValue {

	private int fieldId = 0;
	private FieldType fieldType;
	public EWAHCompressedBitmap notNullMap = new EWAHCompressedBitmap();
	public List<Object> notNullValues = new ArrayList<Object>();

	/***
	 * 
	 * @param columnId
	 * @param schema
	 */
	public KVColumnValue(int columnId, FieldType fieldType) {
		this.fieldId = columnId;
		this.fieldType = fieldType;
	}

	/***
	 * 
	 * @param localRowId
	 * @param value
	 */
	public void setValue(int localRowId, Object value) {
		if (value != null) {
			notNullMap.set(localRowId);
			notNullValues.add(value);
		}
	}

	public Object getValue(int localRowId) {
		EWAHCompressedBitmap tempBitMap = new EWAHCompressedBitmap();
		tempBitMap.set(localRowId);

		int cardinality1 = notNullMap.andCardinality(tempBitMap);
		if (cardinality1 == 0) {
			return null;
		}
		tempBitMap.clear();

		tempBitMap.setSizeInBits(localRowId, true);
		int cardinality2 = notNullMap.andCardinality(tempBitMap);
		if (cardinality2 >= 0 && cardinality2 < notNullValues.size()) {
			return notNullValues.get(cardinality2);
		}

		return null;
	}

	public void readBitMap(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
		DataInputStream dis = new DataInputStream(bais);

		try {
			this.notNullMap.deserialize(dis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void readNotNullValues(byte[] bytes) {
		ByteArrayInputStream bais = new ByteArrayInputStream(bytes);

		try {
			GZIPInputStream zis = new GZIPInputStream(bais);
			DataInputStream dis = new DataInputStream(zis);
			int size = dis.readInt();
			for (int i = 0; i < size; i++) {
				Object obj = SerializeHelper.read(dis, fieldType);
				notNullValues.add(obj);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bais.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public byte[] serializeNotNullValues() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] bytes = null;
		try {
			DataOutputStream output = new DataOutputStream(baos);
			int size = this.notNullValues.size();
			output.writeInt(size);
			for (int i = 0; i < size; i++) {
				Object obj = notNullValues.get(i);
				if (obj != null) {
					SerializeHelper.write(output, obj, fieldType);
				}
			}
			output.flush();
			baos.flush();

			bytes = baos.toByteArray();

			output.close();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}

	public byte[] compressNotNullValues() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		byte[] bytes = null;
		try {
			GZIPOutputStream gzipOS = new GZIPOutputStream(baos);
			DataOutputStream dos = new DataOutputStream(gzipOS);
			int size = this.notNullValues.size();
			dos.writeInt(size);
			for (int i = 0; i < size; i++) {
				Object obj = notNullValues.get(i);
				if (obj != null) {
					SerializeHelper.write(dos, obj, fieldType);
				}
			}

			dos.flush();
			gzipOS.flush();
			gzipOS.finish();
			baos.flush();

			bytes = baos.toByteArray();

			dos.close();
			gzipOS.close();
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bytes;
	}

	public ByteBuffer compressNotNullValues1(boolean bCompressed) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(this.notNullValues);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteBuffer buffer = ByteBuffer.wrap(baos.toByteArray());
		if (!bCompressed) {
			return buffer;
		}

		ByteArrayOutputStream os1 = new ByteArrayOutputStream();
		ZipOutputStream out1 = new ZipOutputStream(new BufferedOutputStream(os1));

		try {
			out1.putNextEntry(new ZipEntry("ColumnValue"));
			out1.write(buffer.array());
			out1.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		ByteBuffer buffer1 = ByteBuffer.wrap(os1.toByteArray());

		return buffer1;
	}

	public byte[] getCompressedBitMap() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutput output = new DataOutputStream(baos);
		try {
			notNullMap.serialize(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bytes = baos.toByteArray();
		try {
			baos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}

	public int getNumNotNullValues() {
		return notNullValues.size();
	}

	public EWAHCompressedBitmap getBitMap() {
		return this.notNullMap;
	}

	public String toString() {
		String ret = "";
		ret += "Field : " + fieldId;
		ret += "\t:";
		List<Integer> positions = notNullMap.getPositions();
		for (int i = 0; i < notNullMap.cardinality(); i++) {
			int position = positions.get(i);
			Object value = notNullValues.get(i);
			ret += "" + position + "->" + " " + value + " ";

		}
		return ret;
	}

	public void print() {
		List<Integer> positions = notNullMap.getPositions();
		for (int i = 0; i < notNullMap.cardinality(); i++) {
			int position = positions.get(i);
			Object value = notNullValues.get(i);
			System.out.println(position + "=" + value.toString());

		}
	}

	public void buildRecords(Query query, ResultSet results, boolean b) {
		Util.log("Starting build column " + this.fieldId, this.getClass());

		List<Integer> positions = notNullMap.getPositions();
		ArrayList<Integer> remainedRecordIds = new ArrayList<Integer>();
		
		int size = positions.size();
		for (int i = 0; i < size; i++) {
			int position = positions.get(i);
			Object value = notNullValues.get(i);
			boolean satisfy = true;
			
			Record record = results.getRecord((long) position);
			if (record == null) {
					record = new Record(position);
			}
			results.addRecord(record);
			record.setFieldValue(this.fieldId, value);
		}
		Util.log("Finished build column " + this.fieldId, this.getClass());
	}

}
