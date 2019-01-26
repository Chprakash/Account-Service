package chandra.prakash.registration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import chandra.prakash.registration.entity.TUser;

@Repository
public interface UserRepository extends JpaRepository<TUser, Long>{
	
	@Query("Select u from TUser u where u.emailID = :emailID")
	public TUser getUserByEmailID(@Param("emailID") String emailID);

}
