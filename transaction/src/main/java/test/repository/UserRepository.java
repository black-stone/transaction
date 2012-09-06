package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import test.persistence.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Modifying
	@Query(value = "update User set name=:name where id=:id")
	void updateName(@Param("name") String name, @Param("id") Long id);

	@Query(value = "select user from User user")
	List<User> findAllUsers();

}
