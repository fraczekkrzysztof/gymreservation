package com.fraczekkrzysztof.gymreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraczekkrzysztof.gymreservation.dao.TrainerDao;
import com.fraczekkrzysztof.gymreservation.entity.Trainer;

@Service
public class TrainerService {

	@Autowired
	TrainerDao trainerDao;
	
	public List<Trainer> findAll(){
		List<Trainer> trainerList = new ArrayList<Trainer>();
		for(Trainer trainer : trainerDao.findAll()) {
			trainerList.add(trainer);
		}
		return trainerList;
	}
	
	public Trainer findById(int id) {
		return trainerDao.findById(id).orElse(null);
	}
	
	public Trainer saveOrUpdate(Trainer trainer) {
		return trainerDao.save(trainer);
	}
	
	public void delete(Trainer trainer) {
		trainerDao.delete(trainer);
	}
	
	public void delete(int id) {
		trainerDao.deleteById(id);
	}

	public Trainer findBySymbol(String symbol) {
		return trainerDao.finbBySymbol(symbol);
	}
	
}
