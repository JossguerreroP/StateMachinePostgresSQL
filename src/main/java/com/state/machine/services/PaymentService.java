package com.state.machine.services;

import org.springframework.statemachine.StateMachine;

import com.state.machine.domain.Payment;
import com.state.machine.domain.PaymentEvent;
import com.state.machine.domain.PaymentState;

public interface PaymentService {
	Payment newPayment(Payment payment);
	
	 StateMachine<PaymentState, PaymentEvent> preAuth(Long paymentId);

	 StateMachine<PaymentState, PaymentEvent> authorizePayment(Long paymentId);

	 StateMachine<PaymentState, PaymentEvent> declineAuth(Long paymentId);

}
