package com.courtalon.gigaMvcGalerie.repositories;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.courtalon.gigaMvcGalerie.metier.Image;



public interface ImageRepositoryCustom {
	
	boolean saveImageFile(int id, File f);
	boolean saveImageFile(int id, InputStream s);
	Optional<File> getImageFile(int id);
	Optional<File> getImageThumbFile(int id);
	boolean removeImageFile(int id);
	boolean removeImageThumbFile(int id);
	Page<Image> findByTagList(List<Integer> tagids, Pageable page, boolean includeTags);
	Image findOneIncludingTags(int id);
}
