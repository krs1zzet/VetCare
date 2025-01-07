package com.cmdotenter.VetCare.service;

import com.cmdotenter.VetCare.dto.request.BaseScheduleRequest;
import com.cmdotenter.VetCare.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    void save(BaseScheduleRequest request);
    void deleteById(Long id);
    Schedule findById(Long id);
    List<Schedule> findAll();
}
