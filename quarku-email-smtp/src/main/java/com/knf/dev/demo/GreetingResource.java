package com.knf.dev.demo;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.Location;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;

@Path("/api/v1")
public class GreetingResource {
    @Inject
    Mailer mailer;
   

    //Send Simple Text
    @GET
    @Path("/text")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendASimpleEmail() {
        mailer.send(
                Mail.withText("<username@gmail.com>",
                        "Greeting from Quarkus knowledgefactory",
                        "A simple email sent from a Quarkus application."
                )
        );
        return "Email sent successfully";
    }

    //Send Email with inlined attachment
    @GET
    @Path("/attachment")
    public String sendEmailWithAttachment(){
        mailer.send(Mail.withText("<username@gmail.com>"
                        ,"Greeting from Quarkus knowledgefactory",
                        "Greeting from Quarkus knowledgefactory")
                .addAttachment("image.png",
                       new File("/home/user/Downloads/image.png"),
                        "text/plain"));
        return "Email sent successfully";
    }
  }
