package com.hp.hpl.logkv.demo.applet;



/**
 * Utility class for string.
 * @author Edmond
 */
public final class StringUtil {


	/**
	 * prevent instance object.
	 */
	private StringUtil() {
	}

	/**
	 * check string is empty or null.
	 * @param str	string object
	 * @return	string status result
	 */
	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * check string is not empty or null.
	 * @param str	string object
	 * @return	string status result
	 */
	public static boolean isNotEmptyOrNull(String str) {
		return !isEmptyOrNull(str);
	}

	/**
	 * return empty string if parameter is null.
	 * @param str	string object
	 * @return	string status result
	 */
	public static String getEmptyIfNull(Object str) {

		if (str == null) {
			return "";
		} else {
			return str.toString();
		}
	}

	/**
	 * convert node name.
	 * @param originalNodeName	original node name
	 * @return	convert node name
	 */
	public static String convertNodeName(String originalNodeName) {

		String convertNodeName = originalNodeName;

		if (convertNodeName.indexOf(".") > 0 &&  !convertNodeName.endsWith(".")) {
			convertNodeName = "Node" + convertNodeName.substring(convertNodeName.lastIndexOf(".") + 1);
		}

		return convertNodeName;
	}

}
