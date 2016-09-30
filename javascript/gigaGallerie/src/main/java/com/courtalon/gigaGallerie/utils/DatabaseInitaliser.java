package com.courtalon.gigaGallerie.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.repositories.TagRepository;

public class DatabaseInitaliser implements ApplicationListener<ContextRefreshedEvent> {
	private static final Logger log = LogManager.getLogger(DatabaseInitaliser.class);
	
	private TagRepository tagRepository;
	public TagRepository getTagRepository() {return tagRepository;}


	public void setTagRepository(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent evt) {
		if (getTagRepository().count() == 0) {
			log.info("base seems empty, intialising data");
			getTagRepository().save(new Tag(0, "needReview"));
		}
		else {
			log.info("base seems ready");
		}
	}

}
