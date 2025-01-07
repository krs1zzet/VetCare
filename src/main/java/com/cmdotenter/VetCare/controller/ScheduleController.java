package com.cmdotenter.VetCare.controller;

import com.cmdotenter.VetCare.dto.request.BaseScheduleRequest;
import com.cmdotenter.VetCare.entity.Schedule;
import com.cmdotenter.VetCare.service.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<Schedule>> findAll(){
        List<Schedule> schedules = scheduleService.findAll();
        log.info("Find all schedules");
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/schedules")
    public ResponseEntity<Void> createSchedule(@RequestBody BaseScheduleRequest request){
        scheduleService.save(request);
        log.info("Schedule saved");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id){
        scheduleService.deleteById(id);
        log.info("Schedule deleted");
        return ResponseEntity.ok().build();
    }
} 