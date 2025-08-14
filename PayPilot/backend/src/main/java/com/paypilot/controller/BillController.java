package com.paypilot.controller;

import com.paypilot.model.Bill;
import com.paypilot.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping
    public ResponseEntity<Bill> addBill(@RequestBody Bill bill) {
        return ResponseEntity.ok(billService.addBill(bill));
    }

    @GetMapping
    public ResponseEntity<List<Bill>> getAllBills() {
        return ResponseEntity.ok(billService.getAllBills());
    }
    
    @GetMapping("/bycategory/{category}")
    public List<Bill> getBillsByCategory(@PathVariable String category) {
        System.out.println("Incoming category param: '" + category + "'");
        return billService.getBillsByCategory(category);
    }
}

