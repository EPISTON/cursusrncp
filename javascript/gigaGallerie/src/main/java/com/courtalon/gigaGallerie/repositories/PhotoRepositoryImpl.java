package com.courtalon.gigaGallerie.repositories;

import java.io.File;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.courtalon.gigaGallerie.metier.Photo;
import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.utils.FileStorageManager;

public class PhotoRepositoryImpl implements PhotoRepositoryCustom
{
	Logger log = LogManager.getLogger(PhotoRepositoryCustom.class);
	
	
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

	@Transactional
	@Override
	public Iterable<Photo> findPhotoByTags(Iterable<Integer> tagids) {
		
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1
		//	         join  p.tags as tinc2
		//			 where tinc1.id=:tincid1 and tinc2.id=:tincid2"
		//
		//	Photo   Tag(tinc1)
		//	1		1
		//	1		2
		//	2		2
		//  2		3
		// 
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1 where tinc1.id=1 -> photo1
		//
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1 where tinc1.id=1 AND tinc1.id=2 ->  rien
		//	Photo   Tag(tinc1) Tag(tinc2)
		//	1		1			2
		//	1		2			1
		//	1		1			1
		//	1		2			2
		// 
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1  join p.tags as tinc2 where tinc1.id=1 AND tinc2.id=2
		//	1		1			2 -> oui, photo1 
		int nbTags = 0;
		String ejbRequete = "select DISTINCT p from Photo as p";
		StringBuilder sbjoin = new StringBuilder();
		StringBuilder sbwhere = new StringBuilder();
		for (Integer i : tagids) {
			nbTags++;
			sbjoin.append(", IN(p.tags) tinc" + nbTags);
			if (nbTags > 1)
				sbwhere.append(" AND");
			sbwhere.append(" tinc" + nbTags +".id=:tincid" + nbTags);
		}
		if (nbTags > 0)
			ejbRequete += sbjoin.toString() + " WHERE " + sbwhere.toString();
		
		log.info("requette crée = " + ejbRequete);
		// on creer la requette
		TypedQuery<Photo> request = em.createQuery(ejbRequete, Photo.class);
		int pos = 1;
		for (Integer i : tagids) {
			request.setParameter("tincid" + pos, i);
			pos++;
		}
		// la requette est prete
		return request.getResultList();
	}
	
	

}
