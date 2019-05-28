package com.fraczekkrzysztof.gymreservation.controller.rest;

import java.util.List;

import com.fraczekkrzysztof.gymreservation.controller.rest.exception.CannotDeleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fraczekkrzysztof.gymreservation.controller.rest.exception.DuplicatedException;
import com.fraczekkrzysztof.gymreservation.entity.Activity;
import com.fraczekkrzysztof.gymreservation.service.ActivityService;

@RestController
@RequestMapping("/api")
public class ActivityRestController {

    @Autowired
    ActivityService activityService;

    @GetMapping("/activity")
    public List<Activity> getAllActivities() {
        return activityService.findAll();
    }

    @GetMapping("activity/{id}")
    public Activity getActivityById(@PathVariable("id") int id) {
        return activityService.findById(id);
    }

    @PostMapping("activity")
    public Activity addActivity(@RequestBody Activity theActivity) {
        //becouse it should add an activity the id need to be 0
        Activity tempActivity = activityService.findBySymbol(theActivity.getSymbol());
        if (!(tempActivity == null)) {
            throw new DuplicatedException("Already exists!");
        }
        theActivity.setId(0);
        return activityService.saveOrUpdate(theActivity);

    }

    @PutMapping("activity")
    public Activity updateActivity(@RequestBody Activity theActivity) {
        return activityService.saveOrUpdate(theActivity);
    }

    @DeleteMapping("activity/{id}")
    public void deleteActivity(@PathVariable("id") int id) {
        Activity toDeleteActivity = activityService.findById(id);
        if (toDeleteActivity.getLessons() != null) {
            throw new CannotDeleteException("Activity is used!");
        }
        activityService.delete(id);
    }
}
