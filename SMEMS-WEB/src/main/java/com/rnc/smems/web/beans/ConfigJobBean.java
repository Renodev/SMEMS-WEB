package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Customer;
import com.rnc.smems.web.entities.Job;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.services.CustomerService;
import com.rnc.smems.web.services.JobService;
import com.rnc.smems.web.services.ProductService;

@Named
@ViewScoped
public class ConfigJobBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Job job;
	
	private Customer customer;
	
	private Product product;
	
	private List<Job> jobs;
	
	private List<Customer> customers;
	
	private List<Product> products;
		
	@Inject
	private JobService jobService;
	
	@Inject
	private CustomerService customerService;
	
	@Inject
	private ProductService productService;
	
	@PostConstruct
	public void initialize() {
		job = new Job();
		jobs = jobService.findAll();
		customer = new Customer();
		customers = customerService.findAll();
		product = new Product();
		products = productService.findAll();
	}
	
	public void save() {
		jobService.save(job);
		initialize();
	}

	public void update(Job job) {
		this.job = job;
	}
	public void delete(Job job) {
		jobService.delete(job);
		initialize();
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public List<Job> getJobs() {
		return jobs;
	}

	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public JobService getJobService() {
		return jobService;
	}

	public void setJobService(JobService jobService) {
		this.jobService = jobService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	
}