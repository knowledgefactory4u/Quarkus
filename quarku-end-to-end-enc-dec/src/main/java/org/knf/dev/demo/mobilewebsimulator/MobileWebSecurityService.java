package org.knf.dev.demo.mobilewebsimulator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class MobileWebSecurityService {

    private static SecretKeySpec secretKey;
    private static byte[] key;


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

    // Get RSA keys. Uses key size of 2048.
    public static Map<String, Object> getRSAKeys() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        Map<String, Object> keys = new HashMap<String, Object>();
        keys.put("private", privateKey);
        keys.put("public", publicKey);
        return keys;
    }

    //Public key to String
    public static String publicKeyToString(PublicKey publicKey) {
        byte[] byte_pubkey = publicKey.getEncoded();
        //converting byte to String
        String str_key = Base64.getEncoder().encodeToString(byte_pubkey);
        // String str_key = new String(byte_pubkey,Charset.);
        return str_key;
    }

    //Private key to String
    public static String privateKeyToString(PrivateKey privateKey) {
        byte[] byte_pubkey = privateKey.getEncoded();
        //converting byte to String
        String str_key = Base64.getEncoder().encodeToString(byte_pubkey);
        // String str_key = new String(byte_pubkey,Charset.);
        return str_key;
    }


    // Decrypt encrypted AES Key using RSA Key which already stored
    public static String decryptMessageUsingPrivateKey(String encryptedText,
                                                       String privateKey)
            throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, loadPrivateKey(privateKey));
        return new String(cipher.doFinal(Base64.getDecoder().
                decode(encryptedText)));
    }

    // Convert String private key to privateKey object
    public static PrivateKey loadPrivateKey(String key64)
            throws GeneralSecurityException {
        byte[] clear = Base64.getDecoder().decode((key64.getBytes()));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PrivateKey priv = fact.generatePrivate(keySpec);
        Arrays.fill(clear, (byte) 0);
        return priv;
    }

    //Encrypt Pin/Password/other secrets using AES Key
    public String encryptPin(String pin, String aesKey)
            throws NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, UnsupportedEncodingException,
            IllegalBlockSizeException, BadPaddingException {
        setKey(aesKey);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal
                (pin.getBytes(StandardCharsets.UTF_8)));


    }
}
