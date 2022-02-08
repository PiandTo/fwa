package edu.school21.cinema.services;


import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UsersRepositoryImpl;
import org.springframework.stereotype.Service;
import java.util.List;


@Service ("userService")
public class UserService {

	private final UsersRepositoryImpl usersRepository;

	public UserService(UsersRepositoryImpl usersRepository) {
		this.usersRepository = usersRepository;
	}

	public User findById(Long id)  {
		return usersRepository.findById(id);
	}

	public List<User> findAll()  {
		return usersRepository.findAll();
	}

	public void save(User entity)  {
		usersRepository.save(entity);
	}

	public void update(User entity)  {
		usersRepository.update(entity);
	}

	public void delete(User entity)  {
		usersRepository.delete(entity);
	}

	public boolean findByEmail(String str)  {
		return usersRepository.findByEmail(str);
	}

	public User findObjByEmail(String str)  {
		return usersRepository.findObjByEmail(str);
	}

	public Boolean equalsScript(String password, String email){
		return usersRepository.equalsScript(password, email);
	}
}
