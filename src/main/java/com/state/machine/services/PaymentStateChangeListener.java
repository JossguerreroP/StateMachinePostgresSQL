package com.state.machine.services;

import java.awt.event.PaintEvent;
import java.util.Optional;

import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import com.state.machine.domain.Payment;
import com.state.machine.domain.PaymentEvent;
import com.state.machine.domain.PaymentState;
import com.state.machine.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component

public class PaymentStateChangeListener extends StateMachineInterceptorAdapter<PaymentState,PaymentEvent>{

	 private final PaymentRepository paymentRepository;
	
	
	 @Override
	    public void preStateChange(State<PaymentState, PaymentEvent> state, Message<PaymentEvent> message,
	                               Transition<PaymentState, PaymentEvent> transition, StateMachine<PaymentState, PaymentEvent> stateMachine) {

	        Optional.ofNullable(message).ifPresent(msg -> {
	            Optional.ofNullable(Long.class.cast(msg.getHeaders().getOrDefault(PaymentServiceImpl.PAYMENT_ID_HEADER, -1L)))
	                    .ifPresent(paymentId -> {
	                        Payment payment = paymentRepository.getReferenceById(paymentId);
	                        payment.setState(state.getId());
	                        paymentRepository.save(payment);
	                    });
	        });
	    }

	
	
	
}
