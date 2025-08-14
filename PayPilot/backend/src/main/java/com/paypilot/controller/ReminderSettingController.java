package com.paypilot.controller;

import com.paypilot.model.ReminderSetting;
import com.paypilot.service.ReminderSettingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderSettingController {

    private final ReminderSettingService service;

    public ReminderSettingController(ReminderSettingService service) {
        this.service = service;
    }

    @PostMapping
    public ReminderSetting add(@RequestBody ReminderSetting setting) {
        return service.add(setting);
    }

    @GetMapping
    public List<ReminderSetting> getAll() {
        return service.getAll();
    }

    @GetMapping("/user/{userId}")
    public List<ReminderSetting> getByUser(@PathVariable Long userId) {
        return service.getByUserId(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
