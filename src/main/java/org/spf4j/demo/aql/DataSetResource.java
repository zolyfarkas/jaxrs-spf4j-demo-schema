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

import com.google.common.reflect.TypeToken;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import org.apache.avro.Schema;
import org.apache.avro.generic.IndexedRecord;
import org.apache.avro.specific.SpecificRecord;
import org.glassfish.jersey.spi.Contract;

/**
 * @author Zoltan Farkas
 */
@Contract
public interface DataSetResource<T extends IndexedRecord> {

  default Schema getSchema() {
    Type[] intfs = this.getClass().getGenericInterfaces();
    for (Type type : intfs) {
      TypeToken<?> tt = TypeToken.of(type);
      if (tt.getRawType() == DataSetResource.class) {
        if (type instanceof ParameterizedType) {
          Class<?> srClasz = TypeToken.of(((ParameterizedType) type).getActualTypeArguments()[0]).getRawType();
          if (SpecificRecord.class.isAssignableFrom(srClasz)) {
            try {
              return ((SpecificRecord) srClasz.getDeclaredConstructor().newInstance()).getSchema();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException
                    | NoSuchMethodException| SecurityException ex) {
              throw new IllegalStateException("Invalid specific record " + srClasz);
            }
          }
        } else {
          throw new IllegalStateException("Resource must overwrite default implementation of getSchema()");
        }
      }
    }
    throw new IllegalStateException("Resource must overwrite default implementation of getSchema()");
  }

  default String getName() {
    return getSchema().getName();
  }

  @GET
  Iterable<T> getData(@QueryParam("_where") String where,
          @QueryParam("_select") String select);


}
