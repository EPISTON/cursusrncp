package com.courtalon.gigaGallerie.repositories;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.gigaGallerie.utils.FileStorageManager;

public class PhotoRepositoryImpl implements PhotoRepositoryCustom
{
	@Autowired
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {
		return fileStorageManager;
	}
	public void setFileStorageManager(FileStorageManager fileStorageManager) {
		this.fileStorageManager = fileStorageManager;
	}

	@Override
	public boolean saveImageFile(int id, File f) {
		return getFileStorageManager().saveFile("Photo", id, f);
	}

	@Override
	public Optional<File> getImageFile(int id) {
		return getFileStorageManager().getFile("Photo", id);
	}

}
