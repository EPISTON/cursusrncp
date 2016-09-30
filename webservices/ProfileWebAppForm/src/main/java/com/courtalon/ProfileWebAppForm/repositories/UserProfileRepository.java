package com.courtalon.ProfileWebAppForm.repositories;

import org.springframework.data.repository.CrudRepository;

import com.courtalon.ProfileWebAppForm.metier.UserProfile;

public interface UserProfileRepository extends CrudRepository<UserProfile, Integer>
{

}
