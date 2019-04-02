package com.app.sugarcrush.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.sugarcrush.model.Cake;

@Repository
public interface CakeRepo extends ItemRepo{

	@Query("select cake from Cake cake where cake.flavour like %:flavour%")
	public List<Cake> getCakesByFlavour(@Param("flavour") String flavour);
	
}
