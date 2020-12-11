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
public class ConfigInStockBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private InStock inStock;

	private List<InStock> inStocks;
	
	private List<Material> materials;
	
	private InStockStatus [] statuses;
	
	@Inject
	private InStockService inStockService;
	
	@Inject
	private MaterialService materialService;
	
	@PostConstruct
	public void initialize () {
		inStock = new InStock();
		inStocks = inStockService.findAll();
		materials = materialService.findAll();
		statuses = InStockStatus.values();
	}
	
	public void save () {
		inStockService.save(inStock);
		initialize();
	}
	
	public void update (InStock inStock) {
		this.inStock = inStock;
	}
	
	public void delete (InStock inStock) {
		inStockService.delete(inStock);
		initialize();
	}
	
	public String generateStockColor (InStockStatus inStockStatus) {
		if (inStockStatus.equals(InStockStatus.Stock_IN)) {
			return "text-primary";
		} else {
			return "text-danger";
		}
	}
	
	public InStock getInStock() {
		return inStock;
	}

	public void setInStock(InStock inStock) {
		this.inStock = inStock;
	}

	public List<InStock> getInStocks() {
		return inStocks;
	}

	public void setInStocks(List<InStock> inStocks) {
		this.inStocks = inStocks;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public InStockStatus[] getStatuses() {
		return statuses;
	}

	public void setStatuses(InStockStatus[] statuses) {
		this.statuses = statuses;
	}
	
}