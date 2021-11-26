package com.knf.dev.demo;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.knf.dev.demo.helper.WordHelper;

@Path("/api")
public class Resource {

	@GET
	@Path("/word")
	@Produces("application/vnd.openxmlformats-" + 
	"officedocument.wordprocessingml.document")
	public Response word() throws FileNotFoundException,
	InvalidFormatException, IOException {
		ByteArrayInputStream bis = WordHelper.generateWord();
		return Response.ok(bis).header("content-disposition", 
				"attachment; filename = mydoc.docx").build();
	}
}