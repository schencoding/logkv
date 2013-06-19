package com.hp.hpl.logkv.ui;

import java.util.Arrays;

import com.hp.hpl.logkv.ingestkv.ShuffleStatus;

public class ShuffleStatusResponse extends Response {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 423731069496587366L;
	
	private ShuffleStatus[] shuffleStatuses = null;
	private Long[] ingestedTRUs = null;
	
	public ShuffleStatusResponse(long timestamp) {
		super(timestamp);
	}

	public ShuffleStatus[] getShuffleStatuses() {
		return shuffleStatuses;
	}

	public void setShuffleStatuses(ShuffleStatus[] shuffleStatuses) {
		this.shuffleStatuses = shuffleStatuses;
	}

	public Long[] getIngestedTRUs() {
		return ingestedTRUs;
	}

	public void setIngestedTRUs(Long[] ingestedTRUs) {
		this.ingestedTRUs = ingestedTRUs;
	}

	@Override
	public String toString() {
		return "ShuffleStatusResponse [shuffleStatuses=" + Arrays.toString(shuffleStatuses) + ", ingestedTRUs=" + Arrays.toString(ingestedTRUs) + "]";
	}
	

}
