package com.courtalon.springCacheForm.beans;

public class TextUtils implements ITextUtils {

	@Override
	public String censure(String texte) {
		System.out.println("------------appel de censure------------------");
		return texte.replaceAll("[aeiouy]", "*");
	}

	@Override
	public int compteVoyelle(String texte) {
		System.out.println("------------appel de compteVoyelle------------------");
		int nbVoyelles = 0;
		for (int i = 0; i < texte.length(); i++) {
			char c = texte.charAt(i);
			if (c == 'a' || c =='e' || c== 'i' || c == 'o' || c=='u' || c=='y')
				nbVoyelles++;
		}
		return nbVoyelles;
	}

}
