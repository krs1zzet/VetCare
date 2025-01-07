package com.cmdotenter.VetCare.Repository;

import com.cmdotenter.VetCare.entity.Appointment;
import com.cmdotenter.VetCare.entity.Pet;
import com.cmdotenter.VetCare.entity.User;
import com.cmdotenter.VetCare.repository.AppointmentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@DataJpaTest
@ActiveProfiles("test")
public class AppointmentRepositoryTest {

        private final AppointmentRepository appointmentRepository;
        private final User user;
        private final Pet pet;

        public AppointmentRepositoryTest(AppointmentRepository appointmentRepository, User user, Pet pet) {
            this.appointmentRepository = appointmentRepository;
            this.user = user;
            this.pet = pet;
        }

        @Test
        void TestSaveAppointment(){


        }



}
