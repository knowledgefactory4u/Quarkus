package org.knf.dev.demo.mobilewebsimulator;


import org.knf.dev.demo.mobilewebsimulator.entity.MobileTempStorage;
import org.knf.dev.demo.mobilewebsimulator.entity.MobileTempStorageService;
import org.knf.dev.demo.model.User;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

/*
@MobileWebSecretResource will act like Android/ios or Web Apps
End Point
 */
@Path("/simulator/v1")
public class MobileWebSecretResource {

    @Inject
    MobileWebSecurityService mobileWebSecurityService;

    @Inject
    MobileTempStorageService tempStorageService;

    /*
     * @Client: Generate Public and Private Key
     * And Sent public key to the server and store the private key on
     * Client local storage for future use
     */
    @POST
    @Path("/generateKeys/{emailId}")
    public Response generateKeys(@PathParam("emailId") String emailId) throws Exception {

        User user = new User();
        user.setEmail(emailId);

        /*
         * @Client: Generate Public and Private Key
         */
        Map<String, Object> keys = mobileWebSecurityService.getRSAKeys();
        PrivateKey privateKey = (PrivateKey) keys.get("private");
        PublicKey publicKey = (PublicKey) keys.get("public");

        /*
         * @Client: Sent public key to the server
         */
        String publicKeyToString = mobileWebSecurityService.publicKeyToString(publicKey);
        user.setRsaPublicKey(publicKeyToString);


        /*
         *store the private key
         */
        String privateKeyToString = mobileWebSecurityService.privateKeyToString(privateKey);
        tempStorageService.save(new MobileTempStorage(user.getEmail(), privateKeyToString));

        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }

    @POST
    @Path("/verify/encryption")
    /*
     A stimulator to verify encryption.
     */
    public Response encrypt(User user) throws Exception {

        String encryptedAESKey = user.getAesKey();
        MobileTempStorage privateKey = tempStorageService.get(user.getEmail());
        //Set Private RSA Key
        user.setEmail(privateKey.getKey());
        //Convert enc AES Key to Text
        String aesKey = MobileWebSecurityService.
                decryptMessageUsingPrivateKey(encryptedAESKey, privateKey.getValue());
        String secretPin = "8543";
        String encryptedPin = mobileWebSecurityService.encryptPin(secretPin, aesKey);
        user.setPin(encryptedPin);
        user.setRsaPublicKey(null);
        user.setAesKey(null);
        user.setRsaPrivateKey(null);
        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();
    }
}
