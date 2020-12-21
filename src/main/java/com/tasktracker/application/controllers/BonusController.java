package com.tasktracker.application.controllers;

import java.text.ParseException;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;


import com.tasktracker.application.models.*;
import com.tasktracker.application.security.services.BonusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tasktracker.application.payload.response.MessageResponse;
import com.tasktracker.application.repository.BonusRepository;
import com.tasktracker.application.links.BonusLinks;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@Slf4j
@RequestMapping("/api")
public class BonusController {

    @Autowired
    BonusRepository bonusRepository;

    @Autowired
    BonusService bonusService;


    @PostMapping(path = BonusLinks.ADD_BONUS)
    public ResponseEntity<?> addBonus(@RequestBody Bonus bonus) {
        try {
            bonusRepository.save(new Bonus(bonus.getUserId(), bonus.getComment(), bonus.getMonth(), bonus.getYear(),
                    bonus.getAmount()));
            log.info("Bonus controller:  add bonus");
            return new ResponseEntity<>(new MessageResponse("Comment has been added successfully!"), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(new MessageResponse("Server error!"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update_bonus/{bonus_id}")
    public ResponseEntity<Bonus> updateBonus(@PathVariable("bonus_id") long bonus_id, @RequestBody Bonus bonus) {
        Optional<Bonus> bonusData = bonusRepository.findById(bonus_id);

        if (bonusData.isPresent()) {
            Bonus _bonus = bonusData.get();
            _bonus.setUserId(bonus.getUserId());
            _bonus.setComment(bonus.getComment());
            _bonus.setMonth(bonus.getMonth());
            _bonus.setYear(bonus.getYear());
            _bonus.setAmount(bonus.getAmount());
            return new ResponseEntity<>(bonusRepository.save(_bonus), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/bonus/{user_id}/{month}/{year}")
    public ResponseEntity<?> getBonusByUserMonth(@PathVariable("user_id") String user_id,
            @PathVariable("month") String month, @PathVariable("year") String year) {
        List<Bonus> bonusData = bonusRepository.findByUserIdAndMonthAndYear(user_id, month, year);

        if (bonusData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bonusData, HttpStatus.OK);
    }

    @GetMapping("/bonus-calculation/{month}/{year}")
    public ResponseEntity<?> bonusCalculation(
            @PathVariable("month") int month,
            @PathVariable("year") int year,
            @RequestParam("coef") Float coef
    ) throws ParseException {
        YearMonth yearMonth = YearMonth.of(year, month);
        try {
            return new ResponseEntity<>(bonusService.calculateBonus(yearMonth,coef), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
