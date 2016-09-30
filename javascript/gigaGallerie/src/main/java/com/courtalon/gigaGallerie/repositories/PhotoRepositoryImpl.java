package com.courtalon.gigaGallerie.repositories;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.courtalon.gigaGallerie.metier.Photo;
import com.courtalon.gigaGallerie.metier.Tag;
import com.courtalon.gigaGallerie.utils.FileStorageManager;

public class PhotoRepositoryImpl implements PhotoRepositoryCustom
{
	Logger log = LogManager.getLogger(PhotoRepositoryCustom.class);
	private static final int thumbwidth = 164;
	private static final int thumbheight = 164;
	
	@Autowired
	private FileStorageManager fileStorageManager;
	public FileStorageManager getFileStorageManager() {
		return fileStorageManager;
	}
	
	@PersistenceContext
	private EntityManager em;
	
	public void setFileStorageManager(FileStorageManager fileStorageManager) {
		this.fileStorageManager = fileStorageManager;
	}

	@Override
	public boolean saveImageFile(Photo p, File f) {
		
	
		java.awt.Image imgin;
		try
		{
			if (p.getContentType().equals("image/gif"))
			{
				imgin = ImageIO.read(f);
			}
			else
			{
				ImageIcon img = new ImageIcon(f.getAbsolutePath());
				imgin = img.getImage();
			}
			
			int width = imgin.getWidth(null);
			int height = imgin.getHeight(null);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			GraphicsDevice gs = ge.getDefaultScreenDevice();  
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			
			BufferedImage bin = gc.createCompatibleImage(width, height);
			Graphics g = bin.createGraphics();
			g.drawImage(imgin, 0, 0, null);
			g.dispose();
			double scale;
			if (height <= thumbheight && width <= thumbwidth)
				scale = 1;
			else
				scale = Math.min((double)thumbwidth / width, (double)thumbheight / height);
			double x = (thumbwidth - width * scale) / 2;  
			double y = (thumbheight - height * scale) / 2;
			AffineTransform at = AffineTransform.getTranslateInstance(x, y);
			at.scale(scale, scale);
			BufferedImage imgout = new BufferedImage(thumbwidth, thumbheight, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2 = imgout.createGraphics();
			g2.fillRect(0, 0, thumbwidth, thumbheight);
			g2.drawRenderedImage(bin, at);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	
			File thumb = File.createTempFile("thumb", "img");
			ImageIO.write(imgout, "jpeg", thumb);
			g2.dispose();
			fileStorageManager.saveFile("PhotoThumb", p.getId(), thumb);
			return getFileStorageManager().saveFile("Photo", p.getId(), f);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Optional<File> getImageFile(Photo p) {
		return getFileStorageManager().getFile("Photo", p.getId());
	}
	@Override
	public Optional<File> getImageThumbFile(Photo p) {
		return getFileStorageManager().getFile("PhotoThumb", p.getId());
	}

	@Override
	public boolean removeImageFile(int id) {
		boolean returnvalue = getFileStorageManager().removeFile("photoThumb", id);
		return returnvalue && getFileStorageManager().removeFile("Photo", id);
	}

	@Transactional
	@Override
	public Tag addTagToPhoto(int photoId, int tagId) {
		Tag t = em.find(Tag.class, tagId);
		Photo p = em.find(Photo.class, photoId);
		if (t == null || p == null)
			return null;
		p.getTags().add(t);
		return t;
	}

	@Transactional
	@Override
	public Tag removeTagFromPhoto(int photoId, int tagId) {
		Tag t = em.find(Tag.class, tagId);
		Photo p = em.find(Photo.class, photoId);
		if (t == null || p == null)
			return null;
		p.getTags().remove(t);
		return t;
	}

	@Transactional
	@Override
	public Iterable<Photo> findPhotoByTags(Iterable<Integer> tagids) {
		
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1
		//	         join  p.tags as tinc2
		//			 where tinc1.id=:tincid1 and tinc2.id=:tincid2"
		//
		//	Photo   Tag(tinc1)
		//	1		1
		//	1		2
		//	2		2
		//  2		3
		// 
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1 where tinc1.id=1 -> photo1
		//
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1 where tinc1.id=1 AND tinc1.id=2 ->  rien
		//	Photo   Tag(tinc1) Tag(tinc2)
		//	1		1			2
		//	1		2			1
		//	1		1			1
		//	1		2			2
		// 
		// select DISTINCT p from Photo as p 
		// 			 join  p.tags as tinc1  join p.tags as tinc2 where tinc1.id=1 AND tinc2.id=2
		//	1		1			2 -> oui, photo1 
		int nbTags = 0;
		String ejbRequete = "select DISTINCT p from Photo as p";
		StringBuilder sbjoin = new StringBuilder();
		StringBuilder sbwhere = new StringBuilder();
		for (Integer i : tagids) {
			nbTags++;
			sbjoin.append(", IN(p.tags) tinc" + nbTags);
			if (nbTags > 1)
				sbwhere.append(" AND");
			sbwhere.append(" tinc" + nbTags +".id=:tincid" + nbTags);
		}
		if (nbTags > 0)
			ejbRequete += sbjoin.toString() + " WHERE " + sbwhere.toString();
		
		log.info("requette crée = " + ejbRequete);
		// on creer la requette
		TypedQuery<Photo> request = em.createQuery(ejbRequete, Photo.class);
		int pos = 1;
		for (Integer i : tagids) {
			request.setParameter("tincid" + pos, i);
			pos++;
		}
		// la requette est prete
		return request.getResultList();
	}
	
	

}
