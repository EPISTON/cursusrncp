package com.courtalon.gigaGallerie.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.gigaGallerie.metier.Tag;

public interface TagRepository extends PagingAndSortingRepository<Tag, Integer> {
	Iterable<Tag> findByLibelleContaining(String search);
}
