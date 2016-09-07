package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.springStrutsJpaExo4Form.metier.Illustration;
import com.courtalon.springStrutsJpaExo4Form.utils.FileStorageManager;

public class IllustrationDAO implements IIllustrationDAO {

	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {return fileStorageManager;}
	public void setFileStorageManager(FileStorageManager fileStorageManager) {this.fileStorageManager = fileStorageManager;}
	
	@Override
	@Transactional
	public List<Illustration> findAll() {
		return em.createQuery("from Illustration", Illustration.class).getResultList();
	}

	@Override
	@Transactional
	public Illustration findByID(int id) {
		return em.find(Illustration.class, id);
	}

	@Override
	@Transactional
	public Illustration save(Illustration i, File f) {
		Illustration existing = em.find(Illustration.class, i.getId());
		if (existing == null) {
			em.persist(i);
			// stockage effectif du fichier
			fileStorageManager.saveFile("Illustration", i.getId(), f);
			return i;
		}
		else {
			existing = em.merge(i);
			fileStorageManager.saveFile("Illustration", existing.getId(), f);
			return existing;
		}
	}

	@Override
	public File getIllustrationFile(Illustration i) {
		Optional<File> f = fileStorageManager.getFile("Illustration", i.getId());
		if( f.isPresent())
			return f.get();
		else
			return null;
	}

}
