package io.github.paulooorg.api.service;

import java.util.Optional;

import javax.inject.Inject;

import io.github.paulooorg.api.model.dto.PaymentDTO;
import io.github.paulooorg.api.model.dto.mapper.EntityMapper;
import io.github.paulooorg.api.model.dto.mapper.PaymentMapper;
import io.github.paulooorg.api.model.entities.Payment;
import io.github.paulooorg.api.repository.EntityRepository;
import io.github.paulooorg.api.repository.PaymentRepository;

public class PaymentService extends AbstractEntityService<PaymentDTO, Payment> {
	@Inject
	private PaymentRepository paymentRepository;
	
	@Override
	public EntityRepository<Payment, Long> getRepository() {
		return paymentRepository;
	}

	@Override
	public EntityMapper<PaymentDTO, Payment> getMapper() {
		return PaymentMapper.INSTANCE;
	}
	
	public Optional<Payment> findPaymentById(Long loanId, Long id) {
		return paymentRepository.findPaymentById(loanId, id);
	}
}
