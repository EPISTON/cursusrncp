package com.courtalon.gigaGallerie.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import com.courtalon.gigaGallerie.metier.Photo;
import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.repositories.PhotoRepository;
import com.courtalon.gigaGallerie.repositories.TagRepository;
import com.opensymphony.xwork2.ActionSupport;

public class PhotoAction extends ActionSupport {

	private int photoID;
	private String photoNom;
	private String photoDescription;
	private Date photoDateCreation;
	private String photoFileName;
	private String photoContentType;
	
	private InputStream photoStream;
	
	private File file;
	private String fileFileName;
	private String fileContentType;
	
	private Iterable<Photo> photos;
	private Photo photo;

	private Tag tag;
	private int tagID;
	

	private PhotoRepository photoRepository;
	public PhotoRepository getPhotoRepository() {return photoRepository;}
	public void setPhotoRepository(PhotoRepository photoRepository) {this.photoRepository = photoRepository;}
	private TagRepository tagRepository;
	public TagRepository getTagRepository() {return tagRepository;}
	public void setTagRepository(TagRepository tagRepository) {this.tagRepository = tagRepository;}

	
	public InputStream getPhotoStream() {
		return photoStream;
	}
	public Tag getTag() {
		return tag;
	}
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public Photo getPhoto() {
		return photo;
	}
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
	
	public String liste() {
		photos = photoRepository.findAll();
		return SUCCESS;
	}
	
	public String findOne() {
		photo = photoRepository.findOne(getPhotoID());
		return SUCCESS;		
	}

	public String save() {
		photo = photoRepository.save(new Photo(getPhotoID(),
								getPhotoNom(),
								getPhotoDescription(),
								getPhotoDateCreation(),
								getPhotoFileName(),
								getPhotoContentType()));
		return SUCCESS;		
	}
	
	public String saveWithUpload() {
		photo = photoRepository.save(new Photo(getPhotoID(),
				getPhotoNom(),
				getPhotoDescription(),
				new Date(),
				getFileFileName(),
				getFileContentType()));
		photoRepository.saveImageFile(photo.getId(), file);
		return SUCCESS;
	}
	
	public String uploadNewOnly() {
		photo = photoRepository.save(
				new Photo(0,
						getFileFileName(),
						"description non fournie",
						new Date(),
						getFileFileName(),
						getFileContentType()));
		photoRepository.saveImageFile(photo.getId(), file);
		return SUCCESS;
	}
	
	public String delete() {
		photoRepository.delete(getPhotoID());
		// TODO removing file
		//photoRepository.saveImageFile(photo.getId(), file);
		return SUCCESS;
	}
	
	public String addTag() {
		tag = photoRepository.addTagToPhoto(getPhotoID(), getTagID());
		return SUCCESS;
	}
	
	public String removeTag() {
		tag = photoRepository.removeTagFromPhoto(getPhotoID(), getTagID());
		return SUCCESS;
	}
	
	public String affiche() {
		Photo p = photoRepository.findOne(getPhotoID());
		if (p != null) {
			try {
				setPhotoContentType(p.getContentType());
				setPhotoFileName(p.getFileName());
				photoStream = new FileInputStream(photoRepository.getImageFile(p.getId()).get());
				return SUCCESS;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return ERROR;
	}
	

}
