package com.courtalon.gigaGallerie.repositories;

import java.io.File;
import java.util.Optional;

// cette interface contiendra des methodes de mon repository
// non généré par Spring data
public interface PhotoRepositoryCustom {
	boolean saveImageFile(int id, File f);
	Optional<File> getImageFile(int id);

}
