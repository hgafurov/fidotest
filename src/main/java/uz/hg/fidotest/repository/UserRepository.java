package uz.hg.fidotest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uz.hg.fidotest.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByLogin(String login);
	
	@Query("select count(u) from User u where u.login=?1")
	int countLogin(String login);  
}
