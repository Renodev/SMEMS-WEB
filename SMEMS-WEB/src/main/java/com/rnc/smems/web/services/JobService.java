package com.rnc.smems.web.services;

import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import com.rnc.smems.web.entities.Job;
import com.rnc.smems.web.entities.Product;
import com.rnc.smems.web.repositories.JobRepository;

@LocalBean
@Stateless
public class JobService {
	
	@Inject
	private JobRepository jobRepository; 
	
	public void save(Job job) {
		if (job.getId() == 0) {
			job.setDate(LocalDate.now());
			jobRepository.save(job);
		} else {
			update(job);
		}
	}
	
	public void update (Job job) {
		jobRepository.update(job);
	}
	
	public void delete (Job job) {
		Job jb = findByID(job.getId());
		jobRepository.delete(jb);
	}
	
	public Job findByID (long id) {
		return jobRepository.findByID(id);
	}
	
	public List<Job> findAll () {
		return jobRepository.findAll();
	}
	
	public List<Job> findByProduct(Product product){
		return jobRepository.findByProduct(product.getId());
	}
	
	
}