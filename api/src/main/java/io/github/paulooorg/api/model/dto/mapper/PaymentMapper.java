package io.github.paulooorg.api.model.dto.mapper;

import java.math.BigDecimal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.api.model.dto.PaymentDTO;
import io.github.paulooorg.api.model.entities.Money;
import io.github.paulooorg.api.model.entities.Payment;

@Mapper
public interface PaymentMapper extends EntityMapper<PaymentDTO, Payment> {
	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);
	
	@Override
	@Mappings({
		@Mapping(target = "payment", source = "payment", qualifiedByName = "moneyToBigDecimal"),
		@Mapping(target = "principal", source = "principal", qualifiedByName = "moneyToBigDecimal"),
		@Mapping(target = "interest", source = "interest", qualifiedByName = "moneyToBigDecimal")
	})
	PaymentDTO entityToDTO(Payment entity);

	@Override
	@Mappings({
		@Mapping(target = "payment", source = "payment", qualifiedByName = "bigDecimalToMoney"),
		@Mapping(target = "principal", source = "principal", qualifiedByName = "bigDecimalToMoney"),
		@Mapping(target = "interest", source = "interest", qualifiedByName = "bigDecimalToMoney")
	})
	Payment DTOToEntity(PaymentDTO dto);
	
	@Named("bigDecimalToMoney")
	public static Money bigDecimalToMoney(BigDecimal value) {
		return new Money(value);
	}
	
	@Named("moneyToBigDecimal")
	public static BigDecimal moneyToBigDecimal(Money value) {
		return value.getValue();
	}
	
	@Override
	default Payment merge(PaymentDTO dto, Payment entity) {
		return null;
	}
}
