package com.paypilot.service;

import com.paypilot.model.ScheduledPayment;
import com.paypilot.repository.ScheduledPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledPaymentService {

    @Autowired
    private ScheduledPaymentRepository scheduledPaymentRepository;

    public ScheduledPayment schedulePayment(ScheduledPayment payment) {
        return scheduledPaymentRepository.save(payment);
    }

    public List<ScheduledPayment> getAllScheduledPayments() {
        return scheduledPaymentRepository.findAll();
    }

    public List<ScheduledPayment> getPaymentsByUserId(Long userId) {
        return scheduledPaymentRepository.findByUserId(userId);
    }
    public List<ScheduledPayment> getUpcomingPayments() {
        return scheduledPaymentRepository.getUpcomingPayments();
    }
    public List<ScheduledPayment> getPastPayments() {
        return scheduledPaymentRepository.getPastPayments();
    }


}
