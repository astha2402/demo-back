package com.paypilot.scheduler;

import com.paypilot.model.Bill;
import com.paypilot.model.ReminderSetting;
import com.paypilot.repository.BillRepository;
import com.paypilot.repository.ReminderSettingRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import jakarta.annotation.PostConstruct; // 

import java.time.LocalDate;
import java.util.List;
@Component
public class ReminderScheduler {

    private final ReminderSettingRepository reminderRepo;
    private final BillRepository billRepo;

    public ReminderScheduler(ReminderSettingRepository reminderRepo, BillRepository billRepo) {
        this.reminderRepo = reminderRepo;
        this.billRepo = billRepo;
    }

    @PostConstruct
    public void init() {
        System.out.println("ReminderScheduler bean loaded.");
        runOnceAtStart(); //  run it once on app start
    }

    public void runOnceAtStart() {
        sendReminders(); // manually trigger the scheduler logic
    }

    @Scheduled(fixedRate = 300000) // Every 30 sec=30000
    public void sendReminders() {
        System.out.println("Running reminder check at: " + java.time.LocalDateTime.now());

        LocalDate today = LocalDate.now();
        List<ReminderSetting> allSettings = reminderRepo.findAll();

        for (ReminderSetting setting : allSettings) {
            Long billId = setting.getBillId();
            Bill bill = billRepo.findById(billId).orElse(null);

            if (bill == null) {
                System.out.println(" Bill not found for ID: " + billId);
                continue;
            }

            LocalDate dueDate = bill.getDueDate();
            LocalDate reminderDate = dueDate.minusDays(setting.getReminderDaysBefore());

            if (today.equals(reminderDate) && Boolean.TRUE.equals(setting.getEnabled())) {
                System.out.println("Reminder: Bill ID " + bill.getId() +
                        " is due in " + setting.getReminderDaysBefore() +
                        " days for User ID: " + setting.getUserId());
            }
        }
    }
}
