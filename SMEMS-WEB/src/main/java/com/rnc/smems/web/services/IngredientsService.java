package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Ingredient;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.repositories.IngredientsRepository;

@LocalBean
@Stateless
public class IngredientsService {

	@Inject
	private IngredientsRepository ingredientsRepository;
	
	public void save (Ingredient ingredient) {
		if (ingredient.getId() == 0) {
			ingredient.setDate(LocalDate.now());
			ingredientsRepository.save(ingredient);
		} else {
			update(ingredient);
		}
	}
	
	public void update (Ingredient ingredient) {
		ingredientsRepository.update(ingredient);
	}
	
	public void delete (Ingredient ingredient) {
		Ingredient ist = findByID(ingredient.getId());
		ingredientsRepository.delete(ist);
	}
	
	public Ingredient findByID (long id) {
		return ingredientsRepository.findByID(id);
	}
	
	public List<Ingredient> findByMaterial (Material material) {
		return ingredientsRepository.findByMaterial(material.getId());
	}
	
	public List<Ingredient> findAll () {
		return ingredientsRepository.findAll();
	}
	
	public List<Ingredient> findByProduct (Product product) {
		return ingredientsRepository.findByProduct(product.getId());
	}
	
	
}
