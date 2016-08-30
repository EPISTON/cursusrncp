package com.courtalon.jpa_supermapform.beans;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

//cette classe servira a hibernate pour convertir les objets Localisation
// en chaine de caractere pour les stocker dans une colonne
// et inversement
@Converter
public class LocalisationConverter implements AttributeConverter<Localisation, String> {

	// fonction de conversion localisation <-> String(BDD)
	@Override
	public String convertToDatabaseColumn(Localisation loc) {
		if (loc == null)
			return null;
		return loc.getLongitude() + ";" + loc.getLatitude();
	}

	// fonction de conversion (BDD)String <-> Localisation
	@Override
	public Localisation convertToEntityAttribute(String chaine) {
		if (chaine == null || chaine.isEmpty())
			return null;
		String[] champs = chaine.split(";");
		if (champs.length != 2)
			throw new IllegalArgumentException("format de ce localisation inccorect");
		return new Localisation(Double.parseDouble(champs[0]),
								Double.parseDouble(champs[1]));
	}

}
