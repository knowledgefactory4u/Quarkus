package org.knf.dev.demo.backend;

import org.knf.dev.demo.backend.entity.BackEndTempStorage;
import org.knf.dev.demo.backend.entity.BackEndTempStorageService;
import org.knf.dev.demo.model.User;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/backend/api/v1")
public class BackEndSecretResource {
    @Inject
    BackEndWebSecurityService backEndWebSecurityService;

    @Inject
    BackEndTempStorageService tempStorageService;

    /*
     * @Back end
     * 1. Encrypt the system generate AES Key using public key
     * from Client (Mobile)
     * 2. send encrypted AES key to Client(Mobile App)
     */
    @POST
    @Path("/getAES")
    public Response getAES(User user) throws Exception {

        //Genearte AES Key
        String aesKey = backEndWebSecurityService.secureRandomString();

        //Encrypt AES Key using RSA Public key from Mobile
        String encryptedAESKey = backEndWebSecurityService.
                encryptAESUsingPublicKey(aesKey, user.getRsaPublicKey());
        user.setAesKey(encryptedAESKey);

        //Store Aes key
        tempStorageService.save(new BackEndTempStorage(user.getEmail(), aesKey));
        user.setEmail(null);
        user.setRsaPublicKey(null);
        user.setSecret(null);
        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();

    }

    @POST
    @Path("/verify/decryption")
    /*
     * @Backend
     * Verify decryption
     */
    public Response success(User user) throws Exception {

        BackEndTempStorage storage = tempStorageService.get(user.getEmail());
        String pin = BackEndWebSecurityService.decrypt(user.getSecret(), storage.getValue());
        user.setPin(pin);
        user.setAesKey(null);
        user.setEmail(null);
        user.setSecret(null);
        return Response
                .status(Response.Status.OK)
                .entity(user)
                .build();

    }
}
