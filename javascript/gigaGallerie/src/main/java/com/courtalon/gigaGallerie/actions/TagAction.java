package com.courtalon.gigaGallerie.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.repositories.TagRepository;
import com.opensymphony.xwork2.ActionSupport;

public class TagAction extends ActionSupport {

	private int tagID;
	private String tagLibelle;
	
	private Tag tag;
	public Tag getTag() {return tag;}
	
	public int getTagID() {return tagID;}
	public void setTagID(int tagID) {this.tagID = tagID;}
	public String getTagLibelle() {return tagLibelle;}
	public void setTagLibelle(String tagLibelle) {this.tagLibelle = tagLibelle;}
	
	@Autowired
	private TagRepository tagRepository;
	public TagRepository getTagRepository() {
		return tagRepository;
	}
	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}
	private Iterable<Tag> tags;
	public Iterable<Tag> getTags() {
		return tags;
	}

	public String liste() {
		tags = tagRepository.findAll();
		return SUCCESS;
	}
	
	public String findOne() {
		tag = tagRepository.findOne(getTagID());
		return SUCCESS;
	}
	
	public String save() {
		Tag t = new Tag(getTagID(), getTagLibelle());
		tag = tagRepository.save(t);
		return SUCCESS;
	}
	
	
	
}
