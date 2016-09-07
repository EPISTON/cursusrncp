package com.courtalon.firstStrutsSpringJpaForm.repository;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import com.courtalon.firstStrutsSpringJpaForm.metier.Avatar;
import com.courtalon.firstStrutsSpringJpaForm.utils.FileStorageManager;

public class AvatarDAO implements IAvatarDAO {

	// ceci indique a spring d'injecter ici l'entity manager en provenance
	// de l'entity manager factory (grace a annotationDriven)
	@PersistenceContext
	private EntityManager em;
	public EntityManager getEm() {return em;}
	public void setEm(EntityManager em) {this.em = em;}
	
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {return fileStorageManager;}
	public void setFileStorageManager(FileStorageManager fileStorageManager) {this.fileStorageManager = fileStorageManager;}
	@Override
	@Transactional
	public Avatar save(Avatar a, File f) {
		Avatar existing = em.find(Avatar.class, a.getId());
		if (existing == null) {
			em.persist(a);
			// stockage effectif du fichier
			fileStorageManager.saveFile("Avatar", a.getId(), f);
			return a;
		}
		else {
			existing = em.merge(a);
			fileStorageManager.saveFile("Avatar", existing.getId(), f);
			return existing;
		}

	}
	
	@Override
	@Transactional
	public Avatar findByID(int id) {
		return em.find(Avatar.class, id);
	}
	@Override
	public File getAvatarFile(Avatar a) {
		Optional<File> f = fileStorageManager.getFile("Avatar", a.getId());
		if( f.isPresent())
			return f.get();
		else
			return null;
	}
	
	@Override
	@Transactional
	public List<Avatar> findAll() {
		return em.createQuery("from Avatar", Avatar.class).getResultList();
	}

	
}
