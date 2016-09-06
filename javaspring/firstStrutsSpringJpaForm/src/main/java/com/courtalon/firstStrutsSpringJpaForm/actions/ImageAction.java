package com.courtalon.firstStrutsSpringJpaForm.actions;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

public class ImageAction extends ActionSupport {
	private static Logger log = LogManager.getLogger(ImageAction.class); 
	
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	/*
	 *  sert a interagir avec l'upload de fichier en provenance d'un champ de type File
	 * 
	 */
	// le champs file va s'appeler image
	private File image; // le fichier temporaire uploadé
	private String imageContentType; // type du fichier uploadé
	private String imageFileName; // le nom du fichier original
	
	public void setImage(File image) {this.image = image;}
	public void setImageContentType(String imageContentType) {this.imageContentType = imageContentType;}
	public void setImageFileName(String imageFileName) {this.imageFileName = imageFileName;}
	
	
	
	public String edit() {
		return SUCCESS;
	}
	
	public String save() {
		log.info("nom image uploadée = " + imageFileName);
		log.info("content type image uploadée = " + imageContentType);
		log.info("chemin du fichier temporaire uplaodé = " + image.getAbsolutePath()
		+ " " + image.getName());
		Path source = image.toPath();
		File f2 = new File("c:\\gallerie\\fichier_uploade.jpg");
		Path destination = f2.toPath();
		
		try {
			Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) { log.error(e); }
		
		return SUCCESS;
	}
	
	
}
