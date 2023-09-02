package com.ank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ank.domain.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long>{

	
	//@Query("SELECT f FROM Foo f WHERE LOWER(f.name) = LOWER(:name)")
	//Foo retrieveByName(@Param("name") String name);
	
	
	@Query("SELECT l FROM Login l WHERE LOWER(l.userName) = LOWER(:name) and LOWER(l.password) = LOWER(:password)")
	Login getLoginUserDetailByUserAndPassword(@Param("name") String name,@Param("password") String password);
	
	@Query("SELECT l FROM Login l WHERE LOWER(l.loginId) = LOWER(:id)")
	Login getLoginUserDetailByById(@Param("id") Long id);
	
	@Query("SELECT l FROM Login l WHERE LOWER(l.loginId) = LOWER(:id) and (l.userName) = LOWER(:name)")
	Login getLoginUserDetailByByIdAndUser(@Param("id") Long id,@Param("name") String name);
}
