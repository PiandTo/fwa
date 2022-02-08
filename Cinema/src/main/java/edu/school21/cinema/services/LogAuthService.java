package edu.school21.cinema.services;

import edu.school21.cinema.models.Log;
import edu.school21.cinema.repositories.LogAuthRepositoryImpl;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service("logAuthService")
public class LogAuthService {

	final
	LogAuthRepositoryImpl logAuthRepository;

	public LogAuthService(LogAuthRepositoryImpl logAuthRepository) {
		this.logAuthRepository = logAuthRepository;
	}

	public void save (Log entity) throws SQLException
	{
		logAuthRepository.save(entity);
	}

	public List<Log> findAllLogsByEmail(String email) {
		return logAuthRepository.findAllByEmail(email);
	}
}
