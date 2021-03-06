package org.crypto.cache;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.crypto.model.Threshold;
import org.jboss.resteasy.annotations.jaxrs.PathParam;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/threshold/api")
public class ThresholdResource {
    @Inject
    ThresholdService thresholdService;

    @POST
    public Response create(@Valid Threshold item) {
        thresholdService.save(item);
        return Response.status(Status.CREATED).entity(item).build();
    }

    @GET
    @Path("/{id}")
    public Object getOne(@PathParam("id") String id) {
        Object entity = thresholdService.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Thrshold with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        return entity;
    }

    @GET
    public List<Threshold> getAll() {
        return thresholdService.getAll();
    }

    @PATCH
    @Path("/{id}")
    public Response update(@Valid Threshold threshold, @PathParam("id") Long id) {
        thresholdService.save(threshold);
        return Response.status(Status.CREATED).entity(threshold).build();

    }

    @OPTIONS
    public Response opt() {
        return Response.ok().build();
    }

    @GET
    @Path("/hello")
    public String hello() {
        return "hello";
    }

    @DELETE
    @Transactional
    public Response delete() {
        return Response.noContent().build();
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public Response deleteOne(@PathParam("id") Long id) {
        return Response.noContent().build();
    }
}
