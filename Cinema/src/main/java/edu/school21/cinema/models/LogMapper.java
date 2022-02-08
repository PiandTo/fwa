package edu.school21.cinema.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.*;

public class LogMapper implements RowMapper<Log> {
	public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
		Log log = new Log();
		log.setId(rs.getLong("log_id"));
		log.setIp(rs.getString("ip"));
		Timestamp data = rs.getTimestamp("date");
		Date date = new Date(data.getTime());
		Time time = new Time(data.getTime());
		log.setDate(date);
		log.setTime(time);
		log.setEmail(rs.getString("email"));
		return log;
	}
}
