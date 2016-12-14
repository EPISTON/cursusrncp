package com.courtalon.gigaMvcGalerie.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import com.courtalon.gigaMvcGalerie.metier.Asset.AssetAndTags;
import com.courtalon.gigaMvcGalerie.metier.Asset.AssetOnly;
import com.courtalon.gigaMvcGalerie.metier.AssetSource;
import com.courtalon.gigaMvcGalerie.metier.Image;
import com.courtalon.gigaMvcGalerie.metier.LicenseType;
import com.courtalon.gigaMvcGalerie.repositories.AssetSourceRepository;
import com.courtalon.gigaMvcGalerie.repositories.ImageRepository;
import com.courtalon.gigaMvcGalerie.repositories.LicenseTypeRepository;
import com.courtalon.gigaMvcGalerie.repositories.TagRepository;
import com.courtalon.gigaMvcGalerie.utils.JsonPageable;
import com.fasterxml.jackson.annotation.JsonView;


@Controller
@RequestMapping("/rest")
public class ImageController {
	private static Logger log = LogManager.getLogger(ImageController.class);


	@Autowired
	private ImageRepository imageRepository;
	public ImageRepository getImageRepository() {return imageRepository;}
	public void setImageRepository(ImageRepository imageRepository) {this.imageRepository = imageRepository;}

	
	@Autowired
	private TagRepository tagRepository;
	public TagRepository getTagRepository() {return tagRepository;}
	public void setTagRepository(TagRepository tagRepository) {this.tagRepository = tagRepository;}

	@Autowired
	private LicenseTypeRepository licenseTypeRepository;
	public LicenseTypeRepository getLicenseTypeRepository() {return licenseTypeRepository;}
	public void setLicenseTypeRepository(LicenseTypeRepository licenseTypeRepository) {this.licenseTypeRepository = licenseTypeRepository;}

	@Autowired
	private AssetSourceRepository assetSourceRepository;
	public AssetSourceRepository getAssetSourceRepository() {return assetSourceRepository;}
	public void setAssetSourceRepository(AssetSourceRepository assetSourceRepository) {this.assetSourceRepository = assetSourceRepository;}

	
	@RequestMapping(value="/images", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Page<Image> liste(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		return JsonPageable.fromPage(getImageRepository().findAll(pageRequest));
	}
	
	@RequestMapping(value="/images/full", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetAndTags.class)
	public Page<Image> listeFull(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		return JsonPageable.fromPage(getImageRepository().findByTagList(new ArrayList<>(), pageRequest, true));
	}
	
	
	@RequestMapping(value="/images/staged", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Page<Image> listeStaged(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(tagRepository.findByLibelle(TagRepository.UPLOADED).getId());
		return JsonPageable.fromPage(getImageRepository().findByTagList(ids, pageRequest, false));
	}

	@RequestMapping(value="/images/stagedfull", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetAndTags.class)
	public Page<Image> listeStagedIncludingTags(@PageableDefault(page=0, size=10) Pageable pageRequest) {
		List<Integer> ids = new ArrayList<Integer>();
		ids.add(tagRepository.findByLibelle(TagRepository.UPLOADED).getId());
		return JsonPageable.fromPage(getImageRepository().findByTagList(ids, pageRequest, true));
	}

	
	@RequestMapping(value="/images/staged/tagSearchFull/{ids:[0-9,]+}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetAndTags.class)
	public Page<Image> findStagedByTagSearchFull(@PageableDefault(page=0, size=10) Pageable pageRequest,
									 @PathVariable("ids") List<Integer> ids) {
		log.info("recherche par tags " + ids + " demandée");
		ids.add(tagRepository.findByLibelle(TagRepository.UPLOADED).getId());
		return JsonPageable.fromPage(getImageRepository().findByTagList(ids, pageRequest, true));
	}

	
	@RequestMapping(value="/images/{id:[0-9]+}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Image findById(@PathVariable("id") int id) {
		log.info("image no " + id + " demandée");
		Image img = getImageRepository().findOne(id);
		log.info("image " + img.getId() + " name " + img.getFileName());
		return img;
	}

	@RequestMapping(value="/images/delete/{ids:[0-9,]+}", method=RequestMethod.DELETE, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public HashMap<String, Object> removeById(@PathVariable("ids") List<Integer> ids) {
		Image img = null;
		int deletedCount = 0;
		int deletedAsked = 0;
		for (Integer id : ids) {
			log.info("effacement image no " + id + " demandée");
			deletedAsked++;
			img = getImageRepository().findOne(id);
			if (img != null) {
				getImageRepository().delete(img);
				getImageRepository().removeImageFile(img.getId());
				getImageRepository().removeImageThumbFile(img.getId());
				log.info("image " + img.getId() + " deleted");
				deletedCount++;
			}
			else {
				log.info("image " + id + " not found");
			}
		}
		HashMap<String, Object> result = new HashMap<>();
		result.put("deletedCount", deletedCount);
		result.put("deletedAsked", deletedAsked);
		return result;
	}

	@RequestMapping(value="/images/tagSearch/{ids:[0-9,]+}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Page<Image> findByTagSearch(@PageableDefault(page=0, size=10) Pageable pageRequest,
									 @PathVariable("ids") List<Integer> ids) {
		log.info("recherche par tags " + ids + " demandée");
		return JsonPageable.fromPage(getImageRepository().findByTagList(ids, pageRequest, false));
	}
	
	@RequestMapping(value="/images/tagSearchFull/{ids:[0-9,]+}", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	@JsonView(AssetAndTags.class)
	public Page<Image> findByTagSearchFull(@PageableDefault(page=0, size=10) Pageable pageRequest,
									 @PathVariable("ids") List<Integer> ids) {
		log.info("recherche par tags " + ids + " demandée");
		return JsonPageable.fromPage(getImageRepository().findByTagList(ids, pageRequest, true));
	}
	
	
	
	@RequestMapping(value="/images/save/{licenseId:[0-9]+}/{sourceId:[0-9]+}", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Image save(@RequestBody Image image, @PathVariable("licenseId") int licenseId, @PathVariable("sourceId") int sourceId) {
		Image oldImage = getImageRepository().findOne(image.getId());
		if (oldImage == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "pas d'image existante correspondant a l'edition");
		}
		if (oldImage.getLicense().getId() != licenseId) {
			oldImage.setLicense(getLicenseTypeRepository().findOne(licenseId));
		}
		if (oldImage.getSource().getId() != sourceId) {
			oldImage.setSource(getAssetSourceRepository().findOne(sourceId));
		}
		oldImage.setName(image.getName());
		oldImage.setDescription(image.getDescription());
		oldImage.setFileName(image.getFileName());
		return getImageRepository().save(oldImage);
	}
	
	@RequestMapping(value="/images/saveunstage/{licenseId:[0-9]+}/{sourceId:[0-9]+}", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Image saveUnStage(@RequestBody Image image, @PathVariable("licenseId") int licenseId, @PathVariable("sourceId") int sourceId) {
		Image oldImage = getImageRepository().findOneIncludingTags(image.getId());
		if (oldImage == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "pas d'image existante correspondant a l'edition");
		}
		if (oldImage.getLicense().getId() != licenseId) {
			oldImage.setLicense(getLicenseTypeRepository().findOne(licenseId));
		}
		if (oldImage.getSource().getId() != sourceId) {
			oldImage.setSource(getAssetSourceRepository().findOne(sourceId));
		}
		oldImage.setName(image.getName());
		oldImage.setDescription(image.getDescription());
		oldImage.setFileName(image.getFileName());
		oldImage.removeTag(getTagRepository().findByLibelle(TagRepository.UPLOADED));
		return getImageRepository().save(oldImage);
	}
	
	
	
	@RequestMapping(value="/images/data", method=RequestMethod.POST, produces="application/json")
	@ResponseBody
	@JsonView(AssetOnly.class)
	public Image upload(@RequestParam("file") MultipartFile file, @RequestParam("licenseId") Optional<Integer> licenseId, @RequestParam("sourceId") Optional<Integer> sourceId, @RequestParam("tagsId") Optional<List<Integer>> tagsId) {
		Image img = null;
		try {
			img = getImageRepository().save(new Image(0,
											file.getOriginalFilename(),
											"",
											new Date(),
											file.getOriginalFilename(),
											file.getContentType(),
											file.getSize(),
											DigestUtils.md5Hex(file.getInputStream())));
			
			getImageRepository().saveImageFile(img.getId(), file.getInputStream ());
			img.addTag(getTagRepository().findByLibelleAndSystemTag(TagRepository.UPLOADED, true));
			img.setLicense(getLicenseTypeRepository().findOne(licenseId.orElse(LicenseType.NO_LICENSE_ID)));
			img.setSource(getAssetSourceRepository().findOne(sourceId.orElse(AssetSource.UNKOWN_SOURCE_ID)));
			final Image image = img;
			if (tagsId.isPresent()) {
				tagsId.get().forEach(id -> image.addTag(getTagRepository().findByIdAndSystemTag(id, false)));
			}
			getImageRepository().save(img);
			
		} catch (IOException e) {
			log.error(e);
			throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "could not save uploaded image");
		}
		return img;
	}
	
	@RequestMapping(value="/images/data/{id:[0-9]+}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> downloadImage(@PathVariable("id") int id) {
		Image img = imageRepository.findOne(id);
		if (img == null)
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "image not found");
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.parseMediaType(img.getContentType()));
		respHeaders.setContentLength(img.getFileSize());
		respHeaders.setContentDispositionFormData("attachment", img.getFileName());
		
		Optional<File> f =  getImageRepository().getImageFile(img.getId());
		if (f.isPresent()) {
			log.info("fichier pour image no " + id + " trouvé");
			FileSystemResource fsr = new FileSystemResource(f.get());
			return new ResponseEntity<FileSystemResource>(fsr, respHeaders, HttpStatus.OK);
		}
		else {
			log.info("fichier pour image no " + id + " introuvable");
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "image file not found");
		}
	}
	
	@RequestMapping(value="/images/thumbdata/{id:[0-9]+}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<FileSystemResource> downloadThumbImage(@PathVariable("id") int id) {
		Image img = imageRepository.findOne(id);
		HttpHeaders respHeaders = new HttpHeaders();
		respHeaders.setContentType(MediaType.parseMediaType("image/jpeg"));
		respHeaders.setContentDispositionFormData("attachment", "thumb_" + id + ".jpeg");
		
		Optional<File> f =  getImageRepository().getImageThumbFile(img.getId());
		if (f.isPresent()) {
			log.info("fichier pour thumbnail image no " + id + " trouvé");
			FileSystemResource fsr = new FileSystemResource(f.get());
			return new ResponseEntity<FileSystemResource>(fsr, respHeaders, HttpStatus.OK);
		}
		else {
			log.info("fichier pour thumbnail image no " + id + " introuvable");
			return new ResponseEntity<FileSystemResource>(HttpStatus.NOT_FOUND);
		}
	}
	
}
