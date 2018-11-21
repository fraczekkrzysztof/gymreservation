package com.fraczekkrzysztof.gymreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fraczekkrzysztof.gymreservation.service.ActivityService;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	
	@GetMapping("/list")
	public String findAll(Model theModel) {
		theModel.addAttribute("activityList", activityService.findAll());
		return "activity-list";
	}

}
