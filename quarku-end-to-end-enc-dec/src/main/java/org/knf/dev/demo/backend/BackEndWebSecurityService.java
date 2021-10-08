package org.knf.dev.demo.backend;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

/*
@BackEndWebSecurityService is our backend Resource
End Point
 */
@ApplicationScoped
public class BackEndWebSecurityService {
    private static byte[] key;
    private static SecretKeySpec secretKey;

    // set Key
    public static void setKey(String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes(StandardCharsets.UTF_8);
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 32);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String secureRandomString() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);
        return token;
    }

    /*
     Encrypt AES Key using RSA public key
     */
    public static String encryptAESUsingPublicKey(String AESKey,
                                     String publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, convertToPublicKey(publicKey));
        return Base64.getEncoder().encodeToString(cipher.doFinal
                (AESKey.getBytes()));
    }

    /*
    Convert Public Key String to Public Key Object
     */
    public static PublicKey convertToPublicKey(String pKey)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] publicKeyByteServer = Base64.getDecoder().decode(pKey);
        // generate the publicKey
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.
                generatePublic(new X509EncodedKeySpec(publicKeyByteServer));
        return publicKey;
    }

    // method to encrypt the secret text using key
    public static String decrypt(String strToDecrypt, String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e);
        }
        return null;
    }
}
