package io.github.paulooorg.api.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.infrastructure.hateoas.LinkDTO;
import io.github.paulooorg.api.model.dto.PaymentDTO;
import io.github.paulooorg.api.model.dto.mapper.PaymentMapper;
import io.github.paulooorg.api.model.entities.Payment;
import io.github.paulooorg.api.service.PaymentService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("loan/{loanId}/payment")
public class PaymentResource {
	@Inject
	private PaymentService paymentService;
	
	@Context
	private UriInfo uriInfo;
	
	@GET
	@Path("{id}")
	public PaymentDTO findById(@PathParam("loanId") Long loanId, @PathParam("id") Long id) {
		Optional<Payment> payment = paymentService.findPaymentById(loanId, id);
		if (payment.isPresent()) {
			PaymentDTO paymentDTO = PaymentMapper.INSTANCE.entityToDTO(payment.get());
			paymentDTO.setLinks(createLinks(loanId));
			return paymentDTO;
		}
		throw ApiExceptions.entityIdNotFound(id);
	}
	
	private List<LinkDTO> createLinks(Long loanId) {
		return Arrays.asList(new LinkDTO(Link.fromUriBuilder(uriInfo.getBaseUriBuilder()
				.path(LoanResource.class)
				.path(loanId.toString()))
				.rel("loan").build(), HttpMethod.GET));
	}
}
