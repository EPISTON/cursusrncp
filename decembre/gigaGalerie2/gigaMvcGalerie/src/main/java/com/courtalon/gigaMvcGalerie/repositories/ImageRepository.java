package com.courtalon.gigaMvcGalerie.repositories;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.courtalon.gigaMvcGalerie.metier.Image;



public interface ImageRepository  extends PagingAndSortingRepository<Image, Integer>, ImageRepositoryCustom
{

}
