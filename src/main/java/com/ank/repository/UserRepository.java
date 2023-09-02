package com.ank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ank.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Modifying
	@Query("UPDATE User u SET u.status='inactive' WHERE LOWER(u.userId) = LOWER(:id) and LOWER(u.firstName) = LOWER(:userName)")
	void deleteUserDetailByById(@Param("id") Long id,@Param("userName") String userName);
	
	@Query("SELECT count(*) from User u WHERE LOWER(u.userId) = LOWER(:id)")
	Integer findRecordCountById(@Param("id") Long id);
	
	@Query("SELECT u from User u WHERE LOWER(u.userId) = LOWER(:id)")
	User findUserById(@Param("id") Long id);

}
