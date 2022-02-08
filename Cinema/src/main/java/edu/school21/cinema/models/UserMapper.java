package edu.school21.cinema.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
	public User mapRow(ResultSet resultSet, int i) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong("user_id"));
		user.setFirstName(resultSet.getString("first_name"));
		user.setLastName(resultSet.getString("last_name"));
		user.setEmail(resultSet.getString("email"));
		user.setPhoneNumber(resultSet.getString("phone_number"));
		user.setPassword(resultSet.getString("password"));
		return user;
	}
}
