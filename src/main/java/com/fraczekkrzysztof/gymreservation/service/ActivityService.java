package com.fraczekkrzysztof.gymreservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fraczekkrzysztof.gymreservation.dao.ActivityDao;
import com.fraczekkrzysztof.gymreservation.entity.Activity;

@Service
public class ActivityService {
	
	@Autowired
	private ActivityDao activityDao;
	
	public List<Activity> findAll(){
		List<Activity> activityList = new ArrayList<Activity>();
		for (Activity ac : activityDao.findAll()) {
			activityList.add(ac);
		}
		return activityList;
	}
	
	public Activity findById(int id) {
		return activityDao.findById(id).orElse(null);
	}
	
	public Activity saveOrUpdate(Activity theActivity) {
		return activityDao.save(theActivity);
	}
	
	public void delete(int id) {
		activityDao.deleteById(id);
	}
	
	public void delete(Activity theActivity) {
		activityDao.delete(theActivity);
	}
	
	public Activity findBySymbol(String symbol) {
		return activityDao.findBySymbol(symbol);
	}

}
