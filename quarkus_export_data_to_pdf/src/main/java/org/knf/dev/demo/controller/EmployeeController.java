package org.knf.dev.demo.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.knf.dev.demo.service.EmployeeService;

@Path("/api/export-employees")
public class EmployeeController {

    @Inject
    EmployeeService employeeService;

    @GET
    public Response exportCSV() throws Exception {

        return Response.ok(employeeService.load(), 
                MediaType.APPLICATION_OCTET_STREAM)
                .header("content-disposition", 
                  "attachment; filename = employees.pdf").
                    build();

    }
}
