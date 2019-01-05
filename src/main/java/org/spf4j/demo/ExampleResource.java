package org.spf4j.demo;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import org.spf4j.demo.avro.DemoRecordInfo;

/**
 * A example resource.
 * @author Zoltan Farkas
 */
public interface ExampleResource {

  @GET
  @Produces(value = {"application/octet-stream", "application/json"})
  List<DemoRecordInfo> getRecords();

  @POST
  @Consumes(value = {"application/octet-stream", "application/json"})
  void saveRecords(List<DemoRecordInfo> records);

}
