package com.courtalon.springStrutsJpaExo4Form.repositories;

import java.io.File;
import java.util.List;

import com.courtalon.springStrutsJpaExo4Form.metier.Illustration;

public interface IIllustrationDAO {
	List<Illustration> findAll();
	Illustration findByID(int id);
	Illustration save(Illustration i, File f);
	File getIllustrationFile(Illustration i);

}
