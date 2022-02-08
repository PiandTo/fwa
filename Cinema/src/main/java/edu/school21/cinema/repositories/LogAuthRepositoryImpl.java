package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Log;
import edu.school21.cinema.models.LogMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class LogAuthRepositoryImpl implements LogAuthRepository<Log>{

	final
	JdbcTemplate jdbcTemplate;

	public LogAuthRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Log findById(Long id)  {
		return null;
	}

	@Override
	public List<Log> findAll()  {
		return null;
	}

	@Override
	public void save(Log entity)  {
		Timestamp localDateTime = new Timestamp(System.currentTimeMillis());
		String sql = "INSERT INTO cinema.logs (email, ip, date) values (?, ?, ?)";
		jdbcTemplate.update(sql, entity.getEmail(), entity.getIp(), localDateTime);
	}

	@Override
	public void update(Log entity)  {

	}

	@Override
	public void delete(Log entity)  {

	}

	@Override
	public List<Log> findAllByEmail(String email)  {
		String sql = "select * from cinema.logs where email = ?";
		return jdbcTemplate.query(sql, new LogMapper(), email);
	}

	@Override
	public boolean findByEmail(String string) {
		return false;
	}

	@Override
	public Log findObjByEmail(String string) {
		return null;
	}
}
