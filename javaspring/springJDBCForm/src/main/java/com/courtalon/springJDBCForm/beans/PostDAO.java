package com.courtalon.springJDBCForm.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.courtalon.springJDBCForm.metier.Post;

public class PostDAO implements IPostDAO, RowMapper<Post>
{
	public static final String FIND_ALL = "select id, titre,corps from Post";
	public static final String FIND_BY_ID = "select id, titre,corps from Post where id=?";
	public static final String INSERT_ONE = "insert into Post (titre,corps) values(?,?)";
	public static final String UPDATE_ONE = "update Post set titre=?,corps=? where id=?";

	private JdbcTemplate jdbcTemplate;
	public JdbcTemplate getJdbcTemplate() {return jdbcTemplate;}
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}
	
	public PostDAO() {}

	@Override
	public List<Post> findAll() {
		return getJdbcTemplate().query(FIND_ALL, this);
	}

	@Override
	public Post findByID(int id) {
		// queryForObject est l'equivalent de query, mais qui ne reverra qu'un seul objet
		// autrement dit, pour une requette qui ne renvoie qu'une ligne
		// le deuxieme parametre est un tableau des valeurs de parametres de la requette a executer
		return getJdbcTemplate().queryForObject(FIND_BY_ID, new Object[]{id}, this);
	}

	@Override
	public void save(Post p) {
		if (p.getId() > 0) {
			// correspond aux parametres de la requete UPDATE
			getJdbcTemplate().update(UPDATE_ONE, p.getTitre(), p.getCorps(), p.getId());
		}
		else {
			// correspond a la requete INSERT
			getJdbcTemplate().update(INSERT_ONE, p.getTitre(), p.getCorps());
		}

	}
	
	// cette méthode sert a faire la correspondance entre une ligne du ResultSet
	// et un objet, ici un Post
	// pos contient le numero de la ligne en cours
	@Override
	public Post mapRow(ResultSet rs, int pos) throws SQLException {
		return new Post(rs.getInt("id"),
						rs.getString("titre"),
						rs.getString("corps"));
	}

}
