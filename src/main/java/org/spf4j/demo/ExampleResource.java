package org.spf4j.demo;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.spf4j.demo.avro.DemoRecordInfo;

/**
 * A example resource.
 * @author Zoltan Farkas
 */
public interface ExampleResource {

  @GET
  @Produces(value = {"application/avro", "application/avro-x+json", "application/octet-stream",
    "application/json", "text/csv"})
  List<DemoRecordInfo> getRecords();

  @GET
  @Path("projection")
  @Produces(value = {"application/avro", "application/avro-x+json", "application/octet-stream",
    "application/json", "text/csv"})
  <T> List<GenericRecord> getRecordsProjection(@QueryParam("projection") Schema elementSchema);

  @POST
  @Consumes(value = {"application/avro", "application/avro-x+json", "application/octet-stream",
    "application/json", "text/csv"})
  void saveRecords(List<DemoRecordInfo> records);

}
