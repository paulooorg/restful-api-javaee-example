package io.github.paulooorg.api.model.dto.mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.LoanDTO;
import io.github.paulooorg.api.model.dto.PaymentDTO;
import io.github.paulooorg.api.model.entities.Loan;
import io.github.paulooorg.api.model.entities.Money;
import io.github.paulooorg.api.model.entities.Payment;

@Mapper
public interface LoanMapper extends EntityMapper<LoanDTO, Loan> {
	LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

	@Override
	@Mappings({
		@Mapping(target = "amount", source = "amount", qualifiedByName = "moneyToBigDecimal"),
		@Mapping(target = "clientId", source = "client.id"),
		@Mapping(target = "modalityId", source = "modality.id"),
		@Mapping(target = "balance", source = "balance", qualifiedByName = "moneyToBigDecimal"),
		@Mapping(target = "totalInterest", source = "totalInterest", qualifiedByName = "moneyToBigDecimal"),
		@Mapping(target = "payments", source = "payments", qualifiedByName = "paymentsToPaymentsDTO")
	})
	LoanDTO entityToDTO(Loan entity);

	@Override
	@Mappings({
		@Mapping(target = "amount", source = "amount", qualifiedByName = "bigDecimalToMoney"),
		@Mapping(target = "client.id", source = "clientId"),
		@Mapping(target = "modality.id", source = "modalityId"),
		@Mapping(target = "payments", source = "payments", ignore = true)
	})
	Loan DTOToEntity(LoanDTO dto);
	
	@Named("bigDecimalToMoney")
	public static Money bigDecimalToMoney(BigDecimal value) {
		return new Money(value);
	}
	
	@Named("moneyToBigDecimal")
	public static BigDecimal moneyToBigDecimal(Money value) {
		return value.getValue();
	}
	
	@Named("paymentsToPaymentsDTO")
	public static List<PaymentDTO> paymentsToPaymentsDTO(List<Payment> payments) {
		List<PaymentDTO> paymentsDTO = new ArrayList<>();
		for (Payment payment : payments) {
			PaymentDTO paymentDTO = new PaymentDTO();
			paymentDTO.setPrincipal(payment.getPrincipal().getValue());
			paymentDTO.setInterest(payment.getInterest().getValue());
			paymentDTO.setPaymentDate(payment.getPaymentDate());
			paymentDTO.setPaymentNumber(payment.getPaymentNumber());
			paymentDTO.setPayment(payment.getPayment().getValue());
			paymentsDTO.add(paymentDTO);
		}
		return paymentsDTO;
	}
	
	@Override
	default Loan merge(LoanDTO dto, Loan entity) {
		return null;
	}
}
