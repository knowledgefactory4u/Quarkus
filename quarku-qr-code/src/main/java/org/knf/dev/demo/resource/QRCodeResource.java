package org.knf.dev.demo.resource;

import com.google.zxing.WriterException;
import org.knf.dev.demo.qrcodeservice.QRCodeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Path("")
public class QRCodeResource {
    private final String text = "www.knowledgefactory.net";
    private final int width = 400;
    private final int height = 400;
    @Inject
    QRCodeService qRCodeService;

    @Path("/generateQRCode")
    @GET
    @Produces("image/png")
    public Response generateQRCode() throws
            IOException, WriterException {

        // uncomment line below to send non-streamed
        // return Response.ok(QRCodeService.
        //                        getQRCode(text, width, height)).build();

        // to send streamed
        return Response.
                ok(new ByteArrayInputStream(QRCodeService.
                        getQRCode(text, width, height))).
                build();
    }
}
