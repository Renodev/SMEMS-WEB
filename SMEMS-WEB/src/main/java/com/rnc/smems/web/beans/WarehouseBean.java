package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.enums.InStockStatus;
import com.rnc.smems.web.services.InStockService;
import com.rnc.smems.web.services.MaterialService;

@Named
@ViewScoped
public class WarehouseBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private List<Material> materials;
	
	@Inject
	private MaterialService materialService;
	
	@Inject
	private InStockService inStockService;
	
	@PostConstruct
	public void initialize () {
		materials = materialService.findAll();
	}
	
	public int generateQuantity (Material material) {
		int quantity = 0;
		List<InStock> inStocks = inStockService.findByMaterial(material);
		for (InStock inStock : inStocks) {
			if (inStock.getInStockStatus().equals(InStockStatus.Stock_IN)) {
				quantity += inStock.getQuantity();
			} else {
				quantity -= inStock.getQuantity();
			}
		}
		return quantity;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}
	
}
