package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.repositories.InStockRepository;

@LocalBean
@Stateless
public class InStockService {

	@Inject
	private InStockRepository inStockRepository;
	
	public void save (InStock inStock) {
		if (inStock.getId() == 0) {
			inStock.setDate(LocalDate.now());
			inStockRepository.save(inStock);
		} else {
			update(inStock);
		}
	}
	
	public void update (InStock inStock) {
		inStockRepository.update(inStock);
	}
	
	public void delete (InStock inStock) {
		InStock ist = findByID(inStock.getId());
		inStockRepository.delete(ist);
	}
	
	public InStock findByID (long id) {
		return inStockRepository.findByID(id);
	}
	
	public List<InStock> findByMaterial (Material material) {
		return inStockRepository.findByMaterial(material.getId());
	}
	
	public List<InStock> findAll () {
		return inStockRepository.findAll();
	}
	
}
