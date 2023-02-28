package com.quarkus.bootcamp.nttdata.application;

import com.quarkus.bootcamp.nttdata.domain.entity.Rate;
import com.quarkus.bootcamp.nttdata.domain.services.RateService;
import com.quarkus.bootcamp.nttdata.infraestructure.response.ResponseDto;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/api/rate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RateResource {
    @Inject
    RateService service;

    @POST
    public Uni<Response> add(Rate rate) {
        return service.add(rate)
                .onItem().transform(uri ->
                        Response.ok(new ResponseDto<>(200, "Se registro correctamente", uri)).status(200).build());
    }

    @GET
    public Uni<List<Rate>> getAll() {
        return service.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Rate> getById(@PathParam("id") String id) {
        return service.getById(id);
    }

}
