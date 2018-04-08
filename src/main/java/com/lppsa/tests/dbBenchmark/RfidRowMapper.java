package com.lppsa.tests.dbBenchmark;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RfidRowMapper implements RowMapper<RfidEvent> {
	@Override
	public RfidEvent mapRow(ResultSet row, int rowNum) throws SQLException {
		RfidEvent event = new RfidEvent();
		event.setEventId(row.getInt("event_id"));
		event.setEpc(row.getString("epc"));
		event.setItemId(row.getString("item_id"));
		event.setTokenSN(row.getInt("token_sn"));
		return event;
	}

}
