#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.repositories;

import java.util.List;

import ${package}.metier.*;

public interface IProduitDAO {
	List<Produit> findAll();
	List<Produit> findByCategorie(int cid);
	Produit findByID(int id);
	Produit save(Produit p);
	Produit save(Produit p, int cid);
	void remove(int pid);

}
