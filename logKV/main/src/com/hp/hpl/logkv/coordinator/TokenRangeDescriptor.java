package com.hp.hpl.logkv.coordinator;

import org.apache.cassandra.dht.BigIntegerToken;

public class TokenRangeDescriptor implements Comparable<TokenRangeDescriptor>{
	private BigIntegerToken startToken;
	private BigIntegerToken endToken;
	private String endPoint;	
	
	public TokenRangeDescriptor(BigIntegerToken startToken, BigIntegerToken endToken, String endPoint) {
		super();
		this.startToken = startToken;
		this.endToken = endToken;
		this.endPoint = endPoint;
	}

	public BigIntegerToken getStartToken() {
		return startToken;
	}

	public void setStartToken(BigIntegerToken startToken) {
		this.startToken = startToken;
	}

	public BigIntegerToken getEndToken() {
		return endToken;
	}

	public void setEndToken(BigIntegerToken endToken) {
		this.endToken = endToken;
	}

	public String getEndPoint() {
		return endPoint;
	}


	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	@Override
	public int compareTo(TokenRangeDescriptor desc) {
		
		TokenRangeDescriptor td = (TokenRangeDescriptor) desc;
		return this.startToken.compareTo(td.getStartToken());
		
	}

	@Override
	public String toString() {
		return "TokenRangeDescriptor [startToken=" + startToken + ", endToken=" + endToken + ", endPoint=" + endPoint
				+ "]";
	}
}
