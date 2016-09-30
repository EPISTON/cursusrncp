package com.courtalon.ProfileWebAppForm.services;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;

import com.courtalon.ProfileWebAppForm.metier.UserProfile;
import com.courtalon.ProfileWebAppForm.repositories.UserProfileRepository;

@WebService(endpointInterface="com.courtalon.ProfileWebAppForm.services.ProfileService")
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	public UserProfileRepository getUserProfileRepository() {
		return userProfileRepository;
	}
	public void setUserProfileRepository(UserProfileRepository userProfileRepository) {
		this.userProfileRepository = userProfileRepository;
	}

	@Override
	public List<UserProfile> getProfiles() {
		ArrayList<UserProfile> users = new ArrayList<>();
		for (UserProfile p : getUserProfileRepository().findAll()) {
			users.add(p);
		}
		return users;
				
	}

	@Override
	public UserProfile getProfil(int id) {
		return getUserProfileRepository().findOne(id);
	}

}
