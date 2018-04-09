package com.lppsa.tests.dbBenchmark;

public class RfidEvent {

	private int eventId;
	private String epc;
	private String itemId;
	private int tokenSN;

	public RfidEvent() {
	}

	public RfidEvent(int eventId, String epc, String itemId, int tokenSN) {
		this.eventId = eventId;
		this.epc = epc;
		this.itemId = itemId;
		this.tokenSN = tokenSN;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getEpc() {
		return epc;
	}

	public void setEpc(String epc) {
		this.epc = epc;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemIdl) {
		this.itemId = itemIdl;
	}

	public int getTokenSN() {
		return tokenSN;
	}

	public void setTokenSN(int tokenSN) {
		this.tokenSN = tokenSN;
	}

	@Override
	public String toString() {
		return "RfidEvent [eventId=" + eventId + ", epc=" + epc + ", itemId=" + itemId + ", tokenSN=" + tokenSN + "]";
	}

}
