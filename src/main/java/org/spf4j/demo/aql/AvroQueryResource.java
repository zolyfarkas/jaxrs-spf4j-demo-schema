/*
 * Copyright 2019 SPF4J.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.spf4j.demo.aql;

import java.util.Map;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.spf4j.jaxrs.IterableArrayContent;

/**
 * REST avro sql endpoint.
 * @author Zoltan Farkas
 */
public interface AvroQueryResource {

  @GET
  IterableArrayContent<GenericRecord> query(@QueryParam("query") String query);

  @GET
  @Path("schema")
  Map<String, Schema> schema();

  @GET
  @Path("schema/{entityName}")
  Schema entitySchema(@PathParam("entityName") String enttityName);

}
