/**
 * UtilSecurity.java
 */
package com.ftm.iamcore.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 * Class containing encryption methods to enhance credentials security of the application
 * @author Favio
 *
 */
public class UtilSecurity {

	/**
	 * Encrypt a string using encode 64 bits
	 * @param text
	 * @return encrypted text
	 */
    public static String encrypt(String text) {
        String secretKey = "57VD8HZ12F4S8A5K9U8R7A10T7E6A.MhO"; //key to encrypt data
        String base64EncryptedString = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] plainTextBytes = text.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    /**
     * Decrypt a text using decode base 64
     * @param encryptedText
     * @return decrypted text 
     * @throws Exception
     */
    public static String decrypt(String encryptedText) throws Exception {
        String secretKey = "57VD8HZ12F4S8A5K9U8R7A10T7E6A.MhO"; // key to decrypt data
        String base64EncryptedString = "";
        try {
            byte[] message = Base64.decodeBase64(encryptedText.getBytes("utf-8"));
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher decipher = Cipher.getInstance("DESede");
            decipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plainText = decipher.doFinal(message);
            base64EncryptedString = new String(plainText, "UTF-8");
        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }
}