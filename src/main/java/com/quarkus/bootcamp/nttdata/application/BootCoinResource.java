package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.BootCoin;
import com.quarkus.bootcamp.nttdata.domain.services.BootCoinService;
import com.quarkus.bootcamp.nttdata.infraestructure.response.ResponseDto;
import com.quarkus.bootcamp.nttdata.infraestructure.response.entity.Balance;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/bootcoin")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BootCoinResource {

    @Inject
    BootCoinService service;

    @POST
    public Uni<Response> add(BootCoin bootCoin) {
        return service.add(bootCoin)
                .onItem().transform(uri ->
                        Response.ok(new ResponseDto<>(200, "Se registro correctamente", uri)).status(200).build());
    }

    @GET
    public Uni<List<BootCoin>> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<BootCoin> getById(@PathParam("id") String id) {
        return service.getById(id);
    }

    @PUT
    @Path("/balance/{id}")
    @Transactional
    public Uni<BootCoin> updateBalanceBootCoin(@PathParam("id") String id, Balance card) {
        return service.updateCardId(id, card);
    }

}
