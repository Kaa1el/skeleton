package controllers;

import api.CreateReceiptRequest;
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
        // TODO implement a response
    }

    @GET
    @Path("/tags/{tag}")
    public List<ReceiptResponse> getReceiptsFromTag(@PathParam("tag") String tagName) {
        List<ReceiptsRecord> receiptsRecords = tags.getAllReceiptsFromTag(tagName);
        return receiptsRecords.stream().map(ReceiptResponse::new).collect(toList());
    }

    @GET
    @Path("/alltags")
    public List<TagResponse> getTagsFromId() {
        List<TagsRecord> tagsRecords = tags.getAllTags();
        return tagsRecords.stream().map(TagResponse::new).collect(toList());
    }
}
