package com.courtalon.gigaGallerie.repositories;

import java.io.File;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.gigaGallerie.metier.Photo;
import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.utils.FileStorageManager;

public class PhotoRepositoryImpl implements PhotoRepositoryCustom
{
	@Autowired
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {
		return fileStorageManager;
	}
	
	@PersistenceContext
	private EntityManager em;
	
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

	@Transactional
	@Override
	public Tag addTagToPhoto(int photoId, int tagId) {
		Tag t = em.find(Tag.class, tagId);
		Photo p = em.find(Photo.class, photoId);
		if (t == null || p == null)
			return null;
		p.getTags().add(t);
		return t;
	}

	@Transactional
	@Override
	public Tag removeTagFromPhoto(int photoId, int tagId) {
		Tag t = em.find(Tag.class, tagId);
		Photo p = em.find(Photo.class, photoId);
		if (t == null || p == null)
			return null;
		p.getTags().remove(t);
		return t;
	}

}
