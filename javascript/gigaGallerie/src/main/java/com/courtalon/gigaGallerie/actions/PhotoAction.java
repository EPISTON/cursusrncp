package com.courtalon.gigaGallerie.actions;

import java.io.File;
import java.util.Date;

import com.courtalon.gigaGallerie.metier.Photo;
import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport {

	private int photoID;
	private String photoNom;
	private String photoDescription;
	private Date photoDateCreation;
	private String photoFileName;
	private String photoContentType;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private Iterable<Photo> photos;
	
	
	public Iterable<Photo> getPhotos() {
		return photos;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getPhotoID() {
		return photoID;
	}
	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}
	public String getPhotoNom() {
		return photoNom;
	}
	public void setPhotoNom(String photoNom) {
		this.photoNom = photoNom;
	}
	public String getPhotoDescription() {
		return photoDescription;
	}
	public void setPhotoDescription(String photoDescription) {
		this.photoDescription = photoDescription;
	}
	public Date getPhotoDateCreation() {
		return photoDateCreation;
	}
	public void setPhotoDateCreation(Date photoDateCreation) {
		this.photoDateCreation = photoDateCreation;
	}
	public String getPhotoFileName() {
		return photoFileName;
	}
	public void setPhotoFileName(String photoFileName) {
		this.photoFileName = photoFileName;
	}
	public String getPhotoContentType() {
		return photoContentType;
	}
	public void setPhotoContentType(String photoContentType) {
		this.photoContentType = photoContentType;
	}
	
	
	
}
