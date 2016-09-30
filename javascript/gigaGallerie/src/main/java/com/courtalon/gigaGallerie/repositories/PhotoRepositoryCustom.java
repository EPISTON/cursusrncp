package com.courtalon.gigaGallerie.repositories;

import java.io.File;
import java.util.Optional;

import com.courtalon.gigaGallerie.metier.Photo;
import com.courtalon.gigaGallerie.metier.Tag;

// cette interface contiendra des methodes de mon repository
// non généré par Spring data
public interface PhotoRepositoryCustom {
	boolean saveImageFile(Photo p, File f);
	Optional<File> getImageFile(Photo p);
	Optional<File> getImageThumbFile(Photo p);
	boolean removeImageFile(int id);
	
	Tag addTagToPhoto(int photoId, int tagId);
	Tag removeTagFromPhoto(int photoId, int tagId);
	Iterable<Photo> findPhotoByTags(Iterable<Integer> tagids);
}
