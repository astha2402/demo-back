package com.paypilot.repository;

import com.paypilot.model.Bill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BillRepository extends JpaRepository<Bill, Long> {
	@Query("SELECT b FROM Bill b WHERE TRIM(UPPER(b.category)) = TRIM(UPPER(:category))")
	List<Bill> findByCategoryIgnoreCase(String category);
	

}
