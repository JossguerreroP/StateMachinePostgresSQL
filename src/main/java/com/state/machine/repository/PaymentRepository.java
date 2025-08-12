package com.state.machine.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.state.machine.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment ,Long>{

}
