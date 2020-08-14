package io.github.paulooorg.api.resources;

import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.api.infrastructure.exception.ApiExceptions;
import io.github.paulooorg.api.model.dto.mapper.PaymentMapper;
import io.github.paulooorg.api.model.entities.Payment;
import io.github.paulooorg.api.service.PaymentService;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("loan/{loanId}/payment")
public class PaymentResource {
	@Inject
	private PaymentService paymentService;
	
	@GET
	@Path("{id}")
	public Response findById(@PathParam("loanId") Long loanId, @PathParam("id") Long id) {
		Optional<Payment> payment = paymentService.findPaymentById(loanId, id);
		if (payment.isPresent()) {
			return Response.ok().entity(PaymentMapper.INSTANCE.entityToDTO(payment.get())).build();
		}
		throw ApiExceptions.entityIdNotFound(id);
	}
}
