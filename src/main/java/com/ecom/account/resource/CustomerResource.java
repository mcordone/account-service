/*
 * Copyright (c) 2016
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>http://www.apache.org/licenses/LICENSE-2.0<p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ecom.account.resource;

import com.ecom.account.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by jcordones13 on 3/31/16.
 */
@Component
@Path("customer")
public class CustomerResource {
    @Autowired
    private CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    private Response getCustomer(@PathParam("id") final int id){
        return buildResponse(customerService.getCustomer(id), Response.Status.OK);
    }

    private Response buildResponse(Object o, Response.Status status) {
        if (o == null) {
            return Response.status(Response.Status.NOT_FOUND).type(MediaType.APPLICATION_JSON).build();
        }
        return Response.status(status).entity(o).type(MediaType.APPLICATION_JSON).build();
    }
}
