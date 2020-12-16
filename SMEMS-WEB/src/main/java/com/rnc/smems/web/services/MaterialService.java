package com.rnc.smems.web.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.repositories.MaterialRepository;

@LocalBean
@Stateless
public class MaterialService {
	
	@Inject
	private MaterialRepository materialRepository;

	public void save (Material material) {
		if (material.getId() == 0) {
			materialRepository.save(material);
		} else {
			materialRepository.update(material);
		}
	}
	
	public void delete (Material material) {
		material.setErase(true);
		materialRepository.update(material);
	}
	
	public Material findByID (long id) {
		return materialRepository.findByID(id);
	}
	
	public List<Material> findAll () {
		return materialRepository.findAll();
	}
	
}
