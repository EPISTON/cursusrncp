package com.courtalon.firstStrutsSpringJpaForm.repository;

import java.io.File;
import java.util.List;

import com.courtalon.firstStrutsSpringJpaForm.metier.Avatar;

public interface IAvatarDAO {
	List<Avatar> findAll();
	Avatar save(Avatar a, File f);
	Avatar findByID(int id);
	// cette fonction permet de récupérer le fichier associé a un avatar dans la base
	File getAvatarFile(Avatar a);

}
