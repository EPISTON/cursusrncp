#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import java.util.List;

import ${package}.metier.*;

public interface ICategorieDAO {
	List<Categorie> findAll();
	Categorie findByID(int id);
	Categorie save(Categorie c);
	void remove(int cid);
}
