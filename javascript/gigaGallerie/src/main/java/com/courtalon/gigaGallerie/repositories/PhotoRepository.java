package com.courtalon.gigaGallerie.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.gigaGallerie.metier.Photo;

public interface PhotoRepository 
		extends PagingAndSortingRepository<Photo, Integer>, PhotoRepositoryCustom
{
	
}
