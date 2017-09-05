package controllers;

import api.CreateReceiptRequest;
import api.ReceiptResponse;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/tags/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, int id) {
        tags.toggle(tagName, id);
        // <your code here
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getReceiptsFromTag(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptRecords = tags.getAllReceiptsFromTag(tagName);
        return receiptRecords.stream().map(ReceiptResponse::new).collect(toList());
    }
}
