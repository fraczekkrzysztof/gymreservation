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
import com.fraczekkrzysztof.gymreservation.controller.rest.exception.NotFoundException;
import com.fraczekkrzysztof.gymreservation.entity.Trainer;
import com.fraczekkrzysztof.gymreservation.service.TrainerService;

@RestController
@RequestMapping("/api")
public class TrainerRestController {

    @Autowired
    TrainerService trainerService;

    @GetMapping("/trainer")
    public List<Trainer> getAllTrainers() {
        return trainerService.findAll();
    }

    @GetMapping("/trainer/{id}")
    public Trainer getTrainerById(@PathVariable("id") int id) {
        Trainer tempTrainer = trainerService.findById(id);
        if (tempTrainer == null) {
            throw new NotFoundException("Trainer dosn't exist!");
        }
        return tempTrainer;
    }

    @PostMapping("/trainer")
    public Trainer addTrainer(@RequestBody Trainer theTrainer) {
        Trainer tempTrainer = trainerService.findBySymbol(theTrainer.getSymbol());
        if (!(tempTrainer == null)) {
            throw new DuplicatedException("Trainer already exists!");
        }
        theTrainer.setId(0);
        return trainerService.saveOrUpdate(theTrainer);
    }

    @PutMapping("/trainer")
    public Trainer updateTrainer(@RequestBody Trainer theTrainer) {
        return trainerService.saveOrUpdate(theTrainer);
    }

    @DeleteMapping("/trainer/{id}")
    public void deleteTrainer(@PathVariable("id") int id) {
        Trainer toDeleteTrainer = trainerService.findById(id);
        if (toDeleteTrainer.getLessons().size() != 0) {
            throw new CannotDeleteException("Trainer is used!");
        }
        trainerService.delete(id);
    }
}
