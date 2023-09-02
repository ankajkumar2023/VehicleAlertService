package com.ank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ank.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	
	@Query("SELECT v FROM Vehicle v WHERE LOWER(v.vehicleId) = LOWER(:vehicleId)")
	Vehicle getVehicleByVehicleId(@Param("vehicleId") Long vehicleId);
	
	@Modifying
	@Query("DELETE from Vehicle v WHERE LOWER(v.vehicleId) = LOWER(:id) and LOWER(v.user.userId) = LOWER(:userId)")
	void deleteVehicleByVehicleIdAndUserId(@Param("id") Long id,@Param("userId") Long userId);
	
	@Query("SELECT v FROM Vehicle v WHERE LOWER(v.vehicleId) = LOWER(:id) and LOWER(v.user.userId) = LOWER(:userId)")
	Vehicle getVehicleByVehicleIdAndUserId(@Param("id") Long id,@Param("userId") Long userId);


}
