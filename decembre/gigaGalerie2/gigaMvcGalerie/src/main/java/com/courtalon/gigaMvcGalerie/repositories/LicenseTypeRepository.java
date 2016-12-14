package com.courtalon.gigaMvcGalerie.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.gigaMvcGalerie.metier.LicenseType;



public interface LicenseTypeRepository extends PagingAndSortingRepository<LicenseType, Integer>
{
	
}
