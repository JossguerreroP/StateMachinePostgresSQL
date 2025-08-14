package com.state.machine.services;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.statemachine.StateMachine;
import org.springframework.transaction.annotation.Transactional;

import com.state.machine.domain.Payment;
import com.state.machine.domain.PaymentEvent;
import com.state.machine.domain.PaymentState;
import com.state.machine.repository.PaymentRepository;

@SpringBootTest

class PaymentServiceImplTest {

	
	@Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;
	
	
	@BeforeEach
	void setUp() throws Exception {
		 this.payment = Payment.builder().amount(new BigDecimal("12.99")).build();
		 
	}

	
	
    //@Transactional
	@Test
	void testPreAuth() {
    	  Payment savedPayment = paymentService.newPayment(payment);

          System.out.println("Should be NEW");
          System.out.println(savedPayment.getState());
          
          StateMachine<PaymentState, PaymentEvent> sm = paymentService.preAuth(savedPayment.getId());
          
          Payment preAuthedPayment = paymentRepository.getReferenceById(savedPayment.getId());

          System.out.println("Should be PRE_AUTH");
          System.out.println(sm.getState().getId());

          System.out.println(preAuthedPayment);
	}
    
    
    

}
