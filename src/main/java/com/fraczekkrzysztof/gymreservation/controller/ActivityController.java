package com.fraczekkrzysztof.gymreservation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fraczekkrzysztof.gymreservation.dto.ActivityDto;
import com.fraczekkrzysztof.gymreservation.entity.Activity;
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
	
	@GetMapping("/add")
	public String showFormForAdd(Model theModel) {
		ActivityDto theActivity = new ActivityDto();
		theModel.addAttribute("theActivity", theActivity);
		return "activity-add";
	}
	
	@PostMapping("/saveActivity")
	public String saveActivity(@Valid @ModelAttribute("theActivity") ActivityDto theActivityDto, BindingResult theBindingResult, Model theModel) {
		System.out.println(theBindingResult);
		if (theBindingResult.hasErrors()){
			return "activity-add";
		}
		System.out.println(theActivityDto.getId());
		Activity theActivity = new Activity(theActivityDto.getId(),theActivityDto.getSymbol(),theActivityDto.getName(), null);
		activityService.saveOrUpdate(theActivity);
		return "redirect:/activity/list";
	}
	
	@GetMapping("/deleteSelected")
	public String deleteSelectedActivity(@RequestParam("activityid") int[] ids, Model theModel) {
		for(int id: ids) {
			activityService.delete(id);
		}
		return "redirect:/activity/list";
	}
	
	@GetMapping("/update")
	public String updateActivity(@RequestParam("activityId") int id, Model theModel) {
		Activity theActivity = activityService.findById(id);
		theModel.addAttribute("theActivity", theActivity);
		return "activity-add";
	}

}
