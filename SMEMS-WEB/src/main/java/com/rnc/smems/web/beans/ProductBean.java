package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Ingredient;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.services.IngredientsService;
import com.rnc.smems.web.services.ProductService;

@Named
@ViewScoped
public class ProductBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//private double result;

	private Product product;
	
	private List<Product> products;
	
	private Ingredient ingredient;
	
	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@Inject
	private ProductService productService;
	
	@Inject
	private IngredientsService ingredientsService;
	
	@PostConstruct
	public void initialize () {
		product = new Product();
		//products = productService.findAll();
		//products = new ArrayList<>();
	}
	
	public IngredientsService getIngredientsService() {
		return ingredientsService;
	}

	public void setIngredientsService(IngredientsService ingredientsService) {
		this.ingredientsService = ingredientsService;
	}

	public void  save () {
		
		//result = ingredientsBean.getTotalCost() + product.getProfit();
		//product.setCost(ingredientsBean.getTotalCost());
		//product.setPrice(result);
		//productService.save(product);	
	}
	
	public void update (Product product) {
		this.product  = product;
		productService.update(this.product);
	}
	
	public void delete (Product product) 
	{
			productService.delete(product);
			initialize();
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
	
}
