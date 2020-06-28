package io.github.paulooorg.api.resources;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.Logger;

import io.github.paulooorg.api.model.dto.ClientDTO;
import io.github.paulooorg.api.model.dto.mapper.ClientMapper;
import io.github.paulooorg.api.service.ClientService;

@Path("client")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {
	@Inject
	private ClientService clientService;
	
	@Inject
	private Logger logger;
	
    @GET
    public List<ClientDTO> getAll() {
        return clientService.getAll().stream().map(c -> ClientMapper.INSTANCE.clientToClientDTO(c)).collect(Collectors.toList());
    }
}
