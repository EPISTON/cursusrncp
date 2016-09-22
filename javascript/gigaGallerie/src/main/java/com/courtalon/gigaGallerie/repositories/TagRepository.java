package com.courtalon.gigaGallerie.repositories;

import org.springframework.data.repository.CrudRepository;

import com.courtalon.gigaGallerie.metier.Tag;

public interface TagRepository extends CrudRepository<Tag, Integer> {
	Iterable<Tag> findByLibelleContaining(String search);
}
