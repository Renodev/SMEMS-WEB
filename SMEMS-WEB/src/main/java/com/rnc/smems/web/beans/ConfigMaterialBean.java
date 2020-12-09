package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.services.MaterialService;

@Named
@ViewScoped
public class ConfigMaterialBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Material material;
	
	private List<Material> materials;
	
	@Inject
	private MaterialService materialService;
	
	@PostConstruct
	public void initialze () {
		material = new Material();
		materials = materialService.findAll();
	}
	
	public void save () {
		materialService.save(material);
		initialze();
	}
	
	public void update (Material material) {
		this.material  = material;
	}
	
	public void delete (Material material) {
		materialService.delete(material);
		initialze();
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
}
