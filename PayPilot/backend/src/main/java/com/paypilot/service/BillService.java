package com.paypilot.service;

import com.paypilot.model.Bill;
import com.paypilot.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill addBill(Bill bill) {
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
  
    public List<Bill> getBillsByCategory(String category) {
        System.out.println("🔍 Category in service: '" + category + "'");
        List<Bill> results = billRepository.findByCategoryIgnoreCase(category);
        System.out.println("📦 Results from DB: " + results.size());
        for (Bill b : results) {
            System.out.println("➡️ Found: " + b.getTitle() + " | " + b.getCategory());
        }
        return results;
    }
}

