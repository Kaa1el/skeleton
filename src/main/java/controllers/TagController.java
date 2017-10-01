package controllers;

import api.ReceiptResponse;
import api.TagResponse;
import dao.TagDao;
import generated.tables.records.ReceiptsRecord;
import generated.tables.records.TagsRecord;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tagName, int id) {
        tags.toggle(tagName, id);
        // TODO implement a response
    }

    @GET
    @Path("/{tag}")
    public List<ReceiptResponse> getReceiptsFromTag(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptsRecords = tags.getReceiptsFromTag(tagName);
        return receiptsRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    public List<TagResponse> getTags() {
        List<TagsRecord> tagsRecords = tags.getTags();
        return tagsRecords.stream().map(TagResponse::new).collect(toList());
    }
}
