package com.nha.abdm.wrapper.hip;


import javax.crypto.Cipher;

import org.springframework.stereotype.Component;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RSAEncryption {
	
   public static String publicKeyPEM = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAstWB95C5pHLXiYW59qyO"
            + "4Xb+59KYVm9Hywbo77qETZVAyc6VIsxU+UWhd/k/YtjZibCznB+HaXWX9TVTFs9N"
            + "wgv7LRGq5uLczpZQDrU7dnGkl/urRA8p0Jv/f8T0MZdFWQgks91uFffeBmJOb58u"
            + "68ZRxSYGMPe4hb9XXKDVsgoSJaRNYviH7RgAI2QhTCwLEiMqIaUX3p1SAc178ZlN"
            + "8qHXSSGXvhDR1GKM+y2DIyJqlzfik7lD14mDY/I4lcbftib8cv7llkybtjX1Aayf"
            + "Zp4XpmIXKWv8nRM488/jOAF81Bi13paKgpjQUUuwq9tb5Qd/DChytYgBTBTJFe7i"
            + "rDFCmTIcqPr8+IMB7tXA3YXPp3z605Z6cGoYxezUm2Nz2o6oUmarDUntDhq/PnkN"
            + "ergmSeSvS8gD9DHBuJkJWZweG3xOPXiKQAUBr92mdFhJGm6fitO5jsBxgpmulxpG"
            + "0oKDy9lAOLWSqK92JMcbMNHn4wRikdI9HSiXrrI7fLhJYTbyU3I4v5ESdEsayHXu"
            + "iwO/1C8y56egzKSw44GAtEpbAkTNEEfK5H5R0QnVBIXOvfeF4tzGvmkfOO6nNXU3"
            + "o/WAdOyV3xSQ9dqLY5MEL4sJCGY1iJBIAQ452s8v0ynJG5Yq+8hNhsCVnklCzAls"
            + "IzQpnSVDUVEzv17grVAw078CAwEAAQ==";

    public static void main(String[] args) {
        try {
            // The RSA public key in Base64 encoded format


            // Sample plaintext to encrypt
            String plaintext = "Hello, this is a secret message.";

            // Encrypt the plaintext

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public  String encrypt(String plaintext) throws Exception {
        // Decode the public key from Base64 format
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyPEM);

        // Create a PublicKey object from the byte array
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        // Initialize the cipher for encryption
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);

        // Encrypt the plaintext
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());

        // Encode the encrypted bytes to Base64 to get a string representation
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
