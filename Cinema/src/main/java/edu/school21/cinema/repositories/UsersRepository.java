package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;

import java.sql.SQLException;

public interface UsersRepository<T> extends CrudRepository<T> {
	boolean findByEmail (String str) throws SQLException;
	User findObjByEmail (String str) throws SQLException;
	boolean equalsScript (String password, String email);
}
