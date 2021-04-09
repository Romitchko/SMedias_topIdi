package fr.epsi.service;

import java.util.List;

import fr.epsi.entite.Categorie;

public interface CategorieService {
	
	List<Categorie> getListCategories();
	
	Categorie getCategorie(Long id);
}
