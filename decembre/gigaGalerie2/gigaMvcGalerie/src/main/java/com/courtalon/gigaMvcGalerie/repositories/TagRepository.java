package com.courtalon.gigaMvcGalerie.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.gigaMvcGalerie.metier.Tag;



public interface TagRepository extends TagRepositoryCustom, PagingAndSortingRepository<Tag, Integer>
{
	public static final String UPLOADED = "system_uploaded";
	
	Tag findByLibelle(String libelle);
	Page<Tag> findByLibelleContainingAndSystemTag(String libelle, boolean systemTag, Pageable page);
	Tag findByLibelleAndSystemTag(String libelle, boolean systemTag);
	Tag findByIdAndSystemTag(int id, boolean systemTag);
	Page<Tag> findBySystemTag(boolean systemTag, Pageable page);
	
	
}
