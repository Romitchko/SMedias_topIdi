package fr.epsi.Dao;

import java.util.List;

import fr.epsi.entite.Categorie;

public interface CategorieDao {

	List<Categorie> getListCategories();
	
	Categorie getCategorie(Long id);
}
