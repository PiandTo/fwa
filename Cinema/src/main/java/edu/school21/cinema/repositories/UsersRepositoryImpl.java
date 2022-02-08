package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import edu.school21.cinema.models.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class UsersRepositoryImpl implements UsersRepository<User>{
	private final PasswordEncoder passwordEncoder;
	private final JdbcTemplate jdbcTemplate;

	public UsersRepositoryImpl(PasswordEncoder passwordEncoder, JdbcTemplate jdbcTemplate) {
		this.passwordEncoder = passwordEncoder;
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public User findById(Long id)  {
		return null;
	}

	@Override
	public List<User> findAll()  {
		String sql = "select * from cinema.users;";
		return this.jdbcTemplate.query(sql, new UserMapper());
	}

	@Override
	public void save(User entity)  {
		String password = passwordEncoder.encode(entity.getPassword());
		String sql = "INSERT INTO cinema.users (first_name, last_name, email, phone_number, password) values (?, ?, ?, ?, ?)";
		this.jdbcTemplate.update(sql, entity.getFirstName(), entity.getLastName(), entity.getEmail(), entity.getPhoneNumber(), password);
	}

	@Override
	public void update(User entity) {

	}

	@Override
	public void delete(User entity)  {

	}

	@Override
	public boolean findByEmail(String str)  {
		String sql = "SELECT EXISTS(SELECT FROM cinema.users WHERE email = ?)";
		return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, str));
	}

	@Override
	public User findObjByEmail(String email)  {
		String sql = "select * from cinema.users where email = ?";
		List<User> users = jdbcTemplate.query(sql, new UserMapper(), email);
		if (!users.isEmpty()) {
			for (User user : users)
				return user;
		}
		return null;
	}

	@Override
	public boolean equalsScript (String password, String email)
	{
		User user = this.findObjByEmail(email);
		return passwordEncoder.matches(password, user.getPassword());
	}
}
