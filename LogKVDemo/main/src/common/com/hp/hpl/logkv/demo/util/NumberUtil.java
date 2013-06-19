package com.hp.hpl.logkv.demo.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Utility class for number.
 * @author Edmond
 */
public final class NumberUtil {

	/**
	 * prevent instance object.
	 */
	private NumberUtil() {
	}

	/**
	 * number format instance.
	 */
	private static final NumberFormat NUM_FORMAT = DecimalFormat.getNumberInstance();

	/**
	 * number format instance.
	 */
	private static final NumberFormat NUM_FORMAT_4 = DecimalFormat.getNumberInstance();

	static {
		NUM_FORMAT.setGroupingUsed(false);
		NUM_FORMAT.setMaximumFractionDigits(2);
		NUM_FORMAT.setMinimumFractionDigits(2);

		NUM_FORMAT_4.setGroupingUsed(false);
		NUM_FORMAT_4.setMaximumFractionDigits(4);
		NUM_FORMAT_4.setMinimumFractionDigits(4);
	}

	/**
	 * number format instance.
	 */
	private static final NumberFormat NUM_FORMAT_2 = DecimalFormat.getNumberInstance();

	static {
		NUM_FORMAT_2.setGroupingUsed(false);
		NUM_FORMAT_2.setMaximumFractionDigits(0);
		NUM_FORMAT_2.setMinimumFractionDigits(0);
	}

	/**
	 * get require fraction digits float string.
	 * @param fNum	float number
	 * @return	float number format string
	 */
	public static String getNumberFormatDigitWithTwoFractionDigits(Double fNum) {

		if (fNum == null) {
			return null;
		}

		return NUM_FORMAT.format(fNum);
	}

	/**
	 * get require fraction digits double string.
	 * @param dNum	double number
	 * @return	double number format string
	 */
	public static String convertToTwoDigits(double dNum) {
		return NUM_FORMAT.format(dNum);
	}

	/**
	 * get require fraction digits float string.
	 * @param fNum	float number
	 * @return	float number format string
	 */
	public static String getNumberFormatDigitWithZeroFractionDigits(Float fNum) {

		if (fNum == null) {
			return null;
		}

		return NUM_FORMAT_2.format(fNum);
	}

	/**
	 * get require fraction digits double string.
	 * @param dNum	double number
	 * @return	double number format string
	 */
	public static String getNumberFormatDigitWithZeroFractionDigits(double dNum) {
		return NUM_FORMAT_2.format(dNum);
	}

	/**
	 * get require fraction digits double string.
	 * @param dNum	double number
	 * @return	double number format string
	 */
	public static String getNumberFormatDigitWithFourFractionDigits(double dNum) {
		return NUM_FORMAT_4.format(dNum);
	}

	/**
	 * get next sequence number string.
	 * @param currentSequence	current sequence string
	 * @param maxLength			max length
	 * @return	next sequence
	 */
	public static String getNextSequenceNumberString(
			String currentSequence,
			int maxLength) {

		String nextSequenceNumberString = null;
		Integer nextSequenceNumber = null;

		try {
			nextSequenceNumber = Integer.parseInt(currentSequence);
		} catch (Exception igonre) {
			igonre.printStackTrace();
		}

		if (nextSequenceNumber != null) {

			nextSequenceNumber++;

			if (nextSequenceNumber.toString().length() <= maxLength) {
				nextSequenceNumberString = nextSequenceNumber.toString();
				for (int i = 0; i < maxLength - nextSequenceNumber.toString().length(); i++) {
					nextSequenceNumberString = "0" + nextSequenceNumberString;
				}
			}
		}

		return nextSequenceNumberString;
	}

	/**
	 * judge string is integer.
	 * @param value	string value
	 * @return	string value is integer flag
	 */
	public static boolean isInteger(String value) {

		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

	/**
	 * parse string value to integer.
	 * @param value	string value
	 * @return	integer result
	 */
	public static Integer parseInteger(String value) {

		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	/**
	 * is null in histogram.
	 * @param value	double value
	 * @return	is null result
	 */
	public static boolean isNullInHistogram(double value) {

		return Double.compare(Double.MIN_VALUE, value) == 0;
	}

	/**
	 * is null in histogram.
	 * @param value	double value
	 * @return	is null result
	 */
	public static boolean isNotNullInHistogram(double value) {

		return Double.compare(Double.MIN_VALUE, value) != 0;
	}

	/**
	 * is null in histogram.
	 * @param value	double value
	 * @return	is null result
	 */
	public static boolean isNullInHistogram(int value) {

		return Integer.MIN_VALUE == value;
	}

	/**
	 * is null in histogram.
	 * @param value	double value
	 * @return	is null result
	 */
	public static boolean isNotNullInHistogram(int value) {

		return Integer.MIN_VALUE != value;
	}

	/**
	 * compare two double.
	 * @param d1	double 1
	 * @param d2	double 2
	 * @return	compare result
	 */
    public static int compare(double d1, double d2) {
        if (d1 < d2) {
            return -1;           // Neither value is NaN, thisVal is smaller
        }
        if (d1 > d2) {
            return 1;            // Neither value is NaN, thisVal is larger
        }

        // Cannot use doubleToRawLongBits because of possibility of NaNs.
        long thisBits    = Double.doubleToLongBits(d1);
        long anotherBits = Double.doubleToLongBits(d2);

        return (thisBits == anotherBits ?  0 : // Values are equal
                (thisBits < anotherBits ? -1 : // (-0.0, 0.0) or (!NaN, NaN)
                 1));                          // (0.0, -0.0) or (NaN, !NaN)
    }

    /**
     * compare two integer.
     * @param x		x value
     * @param y		y value
     * @return	compare result
     */
    public static int compare(int x, int y) {
        return (x < y) ? -1 : ((x == y) ? 0 : 1);
    }
}
