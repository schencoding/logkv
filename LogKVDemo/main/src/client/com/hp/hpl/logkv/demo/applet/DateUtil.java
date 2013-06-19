package com.hp.hpl.logkv.demo.applet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for Date.
 * @author Edmond
 */
public final class DateUtil {

	/**
	 * simple file date format.
	 */
	private static final DateFormat SIMPLE_FILE_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	/**
	 * simple date format.
	 */
	private static final DateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

	/**
	 * simple date time format.
	 */
	private static final DateFormat SIMPLE_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * prevent instance object.
	 */
	private DateUtil() {
	}

	/**
	 * get current simple date string.
	 * @return	current simple date string
	 */
	public static String getCurrentSimpleDateString() {
		return SIMPLE_DATE_FORMAT.format(new Date());
	}

	/**
	 * get current simple date.
	 * @return	current simple date
	 */
	public static Date getCurrentSimpleDate() {
		try {
			return SIMPLE_DATE_FORMAT.parse(SIMPLE_DATE_FORMAT.format(new Date()));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * get simple date string.
	 * @param date	date object
	 * @return	simple date string
	 */
	public static String getSimpleDateString(Date date) {
		if (date != null) {
			return SIMPLE_DATE_FORMAT.format(date);
		} else {
			return null;
		}
	}

	/**
	 * get simple file date string.
	 * @param date	date object
	 * @return	simple file date string
	 */
	public static String getSimpleFileDateString(Date date) {
		if (date != null) {
			return SIMPLE_FILE_DATE_FORMAT.format(date);
		} else {
			return null;
		}
	}

	/**
	 * get simple date time string.
	 * @param date	date object
	 * @return	simple date time string
	 */
	public static String getSimpleDateTimeString(Date date) {
		if (date != null) {
			return SIMPLE_DATE_TIME_FORMAT.format(date);
		} else {
			return null;
		}
	}

	/**
	 * get current date object.
	 * @return	current date object
	 */
	public static Date getCurrentDate() {
		return new Date();
	}
}
