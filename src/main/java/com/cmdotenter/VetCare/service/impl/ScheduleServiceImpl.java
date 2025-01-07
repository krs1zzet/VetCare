package com.cmdotenter.VetCare.service.impl;

import com.cmdotenter.VetCare.dto.request.BaseScheduleRequest;
import com.cmdotenter.VetCare.entity.Schedule;
import com.cmdotenter.VetCare.repository.ScheduleRepository;
import com.cmdotenter.VetCare.service.ClinicService;
import com.cmdotenter.VetCare.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ClinicService clinicService;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ClinicService clinicService) {
        this.scheduleRepository = scheduleRepository;
        this.clinicService = clinicService;
    }


    @Override
    public void save(BaseScheduleRequest request) {
        Schedule schedule = new Schedule();
        schedule.setClinic(clinicService.findById(request.getClinicId()));
        schedule.setIsAvailable(request.getIsAvailable());
        schedule.setDate(request.getDate());
    }

    @Override
    public void deleteById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        Schedule theSchedule = schedule.orElseThrow(() -> new RuntimeException("Did not find schedule id - " + id));
        scheduleRepository.deleteById(theSchedule.getId());
    }

    @Override
    public Schedule findById(Long id) {
        Optional<Schedule> schedule = scheduleRepository.findById(id);
        return schedule.orElseThrow(() -> new RuntimeException("Did not find schedule id - " + id));
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }
}
