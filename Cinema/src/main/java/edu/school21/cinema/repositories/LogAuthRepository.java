package edu.school21.cinema.repositories;

import java.util.List;

public interface LogAuthRepository<T> extends CrudRepository<T>{
	boolean findByEmail(String string);
	T findObjByEmail(String string);
	List<T> findAllByEmail(String email);
}
