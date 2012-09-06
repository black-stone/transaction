package test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import test.persistence.UserName;

public interface UserNameRepository extends JpaRepository<UserName, Long> {
	
	@Query("select userName from User userName")
	<T extends UserName> List<T> findAllEntities();

}
