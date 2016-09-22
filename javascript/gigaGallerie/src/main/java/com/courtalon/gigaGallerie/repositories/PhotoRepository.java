package com.courtalon.gigaGallerie.repositories;

import org.springframework.data.repository.CrudRepository;

import com.courtalon.gigaGallerie.metier.Photo;

public interface PhotoRepository 
		extends CrudRepository<Photo, Integer>, PhotoRepositoryCustom
{
	
}
