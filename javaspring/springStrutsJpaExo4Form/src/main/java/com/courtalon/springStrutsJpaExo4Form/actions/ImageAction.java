package com.courtalon.springStrutsJpaExo4Form.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.courtalon.springStrutsJpaExo4Form.metier.Illustration;
import com.courtalon.springStrutsJpaExo4Form.repositories.IIllustrationDAO;
import com.courtalon.springStrutsJpaExo4Form.repositories.IllustrationDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ImageAction extends ActionSupport 
{
	private static Logger log = LogManager.getLogger(ImageAction.class); 
	
	
	private IIllustrationDAO illustrationDAO;
	public void setIllustrationDAO(IIllustrationDAO illustrationDAO) {
		this.illustrationDAO = illustrationDAO;
	}

	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}

	private int illustrationID;
	public int getIllustrationID() {
		return illustrationID;
	}
	public void setIllustrationID(int illustrationID) {
		this.illustrationID = illustrationID;
	}

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
	public String getImageContentType() {return imageContentType;}
	public String getImageFileName() {return imageFileName;}

	// l'objet renvoyant les données binaire de l'image
	private InputStream imageStream;
	public InputStream getImageStream() {
		return imageStream;
	}
	
	private List<Illustration> illustrations;
	public List<Illustration> getIllustrations() {return illustrations;}
	
	public String index() {
		illustrations = illustrationDAO.findAll();
		return SUCCESS;
	}
	
	public String affiche() {
		Illustration a = illustrationDAO.findByID(getIllustrationID());
		File f = illustrationDAO.getIllustrationFile(a);
		setImageContentType(a.getContentType());
		setImageFileName(a.getFileName());
		try {
			imageStream = new FileInputStream(f);
			return SUCCESS;
		} catch (FileNotFoundException e) {log.error(e);}
		return "error";
	}
	
	
	public String edit() {
		return SUCCESS;
	}
	
	public String save() {
		log.info("nom image uploadée = " + imageFileName);
		log.info("content type image uploadée = " + imageContentType);
		log.info("chemin du fichier temporaire uplaodé = " + image.getAbsolutePath()
		+ " " + image.getName());
		illustrationDAO.save(new Illustration(0, getDescription(), imageFileName, imageContentType), image);
		return SUCCESS;
	}
	
	
}
