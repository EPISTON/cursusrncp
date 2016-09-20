package com.courtalon.gigaGallerie.actions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.repositories.TagRepository;
import com.opensymphony.xwork2.ActionSupport;

public class TagAction extends ActionSupport {

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
	
}
