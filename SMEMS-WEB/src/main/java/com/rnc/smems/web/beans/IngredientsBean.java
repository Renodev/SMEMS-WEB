package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Ingredient;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.enums.InStockStatus;
import com.rnc.smems.web.services.InStockService;
import com.rnc.smems.web.services.IngredientsService;
import com.rnc.smems.web.services.MaterialService;
import com.rnc.smems.web.services.ProductService;

@Named
@ViewScoped
public class IngredientsBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Product product;
	private Material material;
	private double TotalCost = 0;
	private Ingredient ingredient;
	
	private double result;
	
	private List<Ingredient> ingredients;
	
	private List<Ingredient> productIn;
	
	private List<Material> materials;
	
	private List<Product> products;
	
	@Inject
	private IngredientsService ingredientsService;
	
	@Inject
	private MaterialService materialService;
	
	@Inject
	private ProductService productService;
	
	@PostConstruct
	public void initialize () {
		product = new Product();
		ingredient = new Ingredient();
		materials = materialService.findAll();
		//products = productService.findAll();
		ingredients = new ArrayList<>();
		products = new ArrayList<>();
		products = productService.findAll();
	}
	
	public void Add() {
		
		if(ingredient.getId() == 0) {
		ingredients.add(ingredient);
		TotalCost += ingredient.getMaterial().getPrice() * ingredient.getQuantity();
		ingredient = new Ingredient();
		}
		else {
			update(ingredient);
		}
				
	}
	
	public void save() {
		
		result = getTotalCost() + product.getProfit();
		product.setCost(getTotalCost());
		product.setPrice(result);
		productService.save(product);
		
		products = productService.findAll();
		System.out.println(products.size());
		//product = products.get(i);
		int i = products.size() - 1;
		product = products.get(i);
		 
		for(Ingredient ingredient: ingredients) {
			
			ingredient.setMaterial(ingredient.getMaterial());
			ingredient.setQuantity(ingredient.getQuantity());
			ingredient.setProduct(product);
			
			ingredientsService.save(ingredient);
			
		}
		
		productIn = ingredientsService.findByProduct(product);
		products = productService.findAll();
		productIn = ingredientsService.findByProduct(product);
		initialize();
		
		
		
		
	}	
	
	public List<Ingredient> getProductIn() {
		return productIn;
	}

	public void setProductIn(List<Ingredient> productIn) {
		this.productIn = productIn;
	}

	public Ingredient cancel() {
		return this.ingredient;
	}
	
	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public void remove(Ingredient ingredient) {
		 
		if(ingredients.size() >= 1 &&  ingredient.getId() == 0) {
			
			ingredients.remove(ingredient);
		}
		else{
			ingredientsService.delete(ingredient);
			ingredients.remove(ingredient);
		}
	}
	
	public void update(Ingredient ingredient) {
		
		this.ingredient = ingredient;
		
		if(ingredient.getId() == 0) {
			
			
			ingredients.set(ingredients.indexOf(ingredient), ingredient);
			remove(ingredient);
			ingredient = new Ingredient();
		}
		else {
			
					ingredientsService.update(ingredient);
				
				
			
		}
		
		
		
		
		
		
		
		
		
		
		
		}
		
		
		
		


	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public double getTotalCost() {
		return TotalCost;
	}

	public void setTotalCost(double totalCost) {
		TotalCost = totalCost;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Material> getMaterials() {
		return materials;
	}

	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}

	public IngredientsService getIngredientsService() {
		return ingredientsService;
	}

	public void setIngredientsService(IngredientsService ingredientsService) {
		this.ingredientsService = ingredientsService;
	}

	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}


	
}