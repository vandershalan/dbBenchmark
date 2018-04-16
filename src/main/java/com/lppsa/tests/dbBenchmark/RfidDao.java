package com.lppsa.tests.dbBenchmark;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RfidDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Async
	public void addEvent(RfidEvent event) {
		String sql = "INSERT INTO rfid_events (event_id, epc, item_id, token_sn) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, event.getEventId(), event.getEpc(), event.getItemId(), event.getTokenSN());
	}

	@Async
	public void addEventByPreparedStatement(RfidEvent event) {
		String sql = "INSERT INTO rfid_events (event_id, epc, item_id, token_sn) values (?, ?, ?, ?)";
		PreparedStatementCallback<Boolean> psc = new PreparedStatementCallback<Boolean>() {
			@Override
			public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setInt(1, event.getEventId());
				ps.setString(2, event.getEpc());
				ps.setString(3, event.getItemId());
				ps.setInt(4, event.getTokenSN());
				return ps.execute();
			}
		};

		jdbcTemplate.execute(sql, psc);
	}

	public RfidEvent getEvent(int rfidEventId) {
		String sql = "SELECT event_id, epc, item_id, token_sn FROM rfid_events WHERE event_id = ?";
		RowMapper<RfidEvent> rowMapper = new RfidRowMapper();
		return jdbcTemplate.queryForObject(sql, rowMapper, rfidEventId);
	}

	public List<RfidEvent> getAllEvents() {
		String sql = "SELECT event_id, epc, item_id, token_sn FROM rfid_events LIMIT 100";
		RowMapper<RfidEvent> rowMapper = new RfidRowMapper();
		return jdbcTemplate.query(sql, rowMapper);
	}

	public void deleteEvent(int rfidEventId) {
		String sql = "DELETE FROM rfid_events WHERE event_id = ?";
		jdbcTemplate.update(sql, rfidEventId);
	}

	public int getEventsCount() {
		String sql = "SELECT COUNT(*) FROM rfid_events";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	public int getMaxSN() {
		String sql = "SELECT MAX(token_sn) FROM rfid_events";
		Integer maxSN = jdbcTemplate.queryForObject(sql, Integer.class);
		if (maxSN == null) {
			maxSN = 0;
		}
		return maxSN;
	}
}
