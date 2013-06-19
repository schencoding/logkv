package com.hp.hpl.logkv.demo.model;

import com.hp.hpl.logkv.ui.ShuffleStatusRequest;

/**
 * Shuffling process wrap request.
 * @author Edmond
 */
public class ShufflingProcessWrapRequest implements java.io.Serializable {

	/**
	 * serial version UID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * request client id.
	 */
	public String requestClientId;

	/**
	 * shuffling status request.
	 */
	public ShuffleStatusRequest shufflingStatusRequest;

}
