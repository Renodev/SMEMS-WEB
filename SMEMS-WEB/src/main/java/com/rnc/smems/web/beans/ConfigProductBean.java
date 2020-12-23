package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Ingredient;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.services.IngredientsService;
import com.rnc.smems.web.services.MaterialService;
import com.rnc.smems.web.services.ProductService;

@Named
@ViewScoped
public class ConfigProductBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private double total;
	
	private Product product;
	
	private List<Product> products;
	
	private List<Ingredient> ingredients;
	
	private List<Material> materials;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private IngredientsService ingredientsService;
	
	@Inject
	private MaterialService materialService;

	@PostConstruct
	public void initialize () {
		materials = materialService.findAll();
		product = new Product();
		products = productService.findAll();
		ingredients = new ArrayList<>();
		Ingredient ingredient = new Ingredient();
		ingredients.add(ingredient);
	}
	
	public void add () {
		Ingredient ingredient = new Ingredient();
		ingredients.add(ingredient);
	}
	
	public void remove (Ingredient ingredient) {
		if (ingredients.size() > 1) {
			ingredients.remove(ingredient);
		}
	}
	
	public void calculateTotal () {
		total = 0;
		for (Ingredient ingredient : ingredients) {
			total += ingredient.getMaterial().getPrice() * ingredient.getQuantity();
		}
	}

	public void save () {
		double cost = 0;
		productService.save(product);
		List<Product> products = productService.findAll();
		for (Ingredient ingredient : ingredients) {
			cost += ingredient.getMaterial().getPrice() * ingredient.getQuantity();
			ingredient.setProduct(products.get(products.size() - 1));
			ingredientsService.save(ingredient);
		}
		products.get(products.size() - 1).setCost(cost);
		products.get(products.size() - 1).setPrice(cost + products.get(products.size() - 1).getProfit());
		productService.save(products.get(products.size() - 1));
		initialize();
	}
	
	public List<Ingredient> generateIngredients (Product product) {
		return ingredientsService.findByProduct(product);
	}
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
}
