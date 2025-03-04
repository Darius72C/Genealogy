/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package liten.genealogy.utilitiesDB;

/**
 *
 * @author bayomock.a
 */
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.stream.IntStream;
import liten.genealogy.webUtilities.HttpUtils;

public class AesService {

    public static HashMap<String, byte[]> keyMap = new HashMap<String, byte[]>();
    public static final int CRYPTO_AUTH_TAG_LENGTH = 128;
    public static final int CRYPTO_IV_LENGTH = 12;
    private static final String[] KEY_CHARS = {"A", "B", "C", "D", "E", "F", "G", "H",
        "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z",
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
        "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
    };

    public static String generateSecureAESKey(int keyLength) {
        SecureRandom secureRandom = new SecureRandom();
        return IntStream.range(0, keyLength)
                .mapToObj(i -> KEY_CHARS[secureRandom.nextInt(KEY_CHARS.length)])
                .reduce("", String::concat);
    }

    public static String encryptDataAES(String plainData, byte[] key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key, "AES");

        //build the initialization vector
        byte[] iv = new byte[CRYPTO_IV_LENGTH];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(iv);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv); //128-bit authentication tag length

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, parameterSpec);

        byte[] cipherText = cipher.doFinal(plainData.getBytes());

        ByteBuffer byteBuffer = ByteBuffer.allocate(iv.length + cipherText.length);
        byteBuffer.put(iv);
        byteBuffer.put(cipherText);

        //the first 12 bytes are the IV where others are the cipher message + authentication tag
        byte[] cipherMessage = byteBuffer.array();
        return Base64.getEncoder().encodeToString(cipherMessage);

    }

    public static String decryptDataAES(String encryptedDataInBase64, byte[] key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        byte[] encryptedDataBytes = Base64.getDecoder().decode(encryptedDataInBase64.getBytes());

        //remember we stored the IV as the first 12 bytes while encrypting?
        byte[] iv = Arrays.copyOfRange(encryptedDataBytes, 0, CRYPTO_IV_LENGTH);

        GCMParameterSpec parameterSpec = new GCMParameterSpec(CRYPTO_AUTH_TAG_LENGTH, iv); //128-bit authentication tag length
        cipher.init(Cipher.DECRYPT_MODE, secretKey, parameterSpec);

        //use everything from 12 bytes on as ciphertext
        byte[] cipherBytes = Arrays.copyOfRange(encryptedDataBytes, CRYPTO_IV_LENGTH, encryptedDataBytes.length);

        byte[] plainText = cipher.doFinal(cipherBytes);

        return new String(plainText);
    }

    public static String encryptField(String field) {
        String encryptedField = "";
        try {
            // String aesKey = ConstantValues.AES_KEY;
            //  Assertions.assertEquals(32, aesKey.length());
            byte[] aesKeyBytes = (byte[]) keyMap.get("AES_KEY_BYTE");
            //   System.out.println(aesKeyBytes);

            //encrypt
            encryptedField = AesService.encryptDataAES(field, aesKeyBytes);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedField;
    }

    public static String decryptField(String encryptedField) {
        String decryptedField = "";
        try {
            // String aesKey = ConstantValues.AES_KEY;
            //  Assertions.assertEquals(32, aesKey.length());
            byte[] aesKeyBytes = (byte[]) keyMap.get("AES_KEY_BYTE");
            // System.out.println(aesKeyBytes);
            //decrypt
            decryptedField = AesService.decryptDataAES(encryptedField, aesKeyBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedField;
    }

    public static void main(String[] args) throws Exception {
        String aesKey = ConstantValues.AES_KEY;
        //  Assertions.assertEquals(32, aesKey.length());

        byte[] aesKeyBytes = aesKey.getBytes();
        System.out.println(aesKeyBytes);
        keyMap.put("AES_BYTE_KEY", aesKeyBytes);

        String plainSecretData = "Felicite1!";

        //encrypt
        aesKeyBytes = (byte[]) keyMap.get("AES_BYTE_KEY");
        String encryptedData = encryptDataAES(plainSecretData, aesKeyBytes);
        System.out.println(encryptedData);

        //decrypt
        aesKeyBytes = (byte[]) keyMap.get("AES_BYTE_KEY");
        String decryptedData = decryptDataAES(encryptedData, aesKeyBytes);

        //5rk5Ba6HGVOT8bWibv5bJaDFRHOFNIM0gBef/310TXND01K+M/4=
        //XIjtQra6JeZtmHEzpOC1GS+g2y28AjJinbKDpKW/Q4Vvqw03FnY=
        //assert
        //assertEquals(plainSecretData, decryptedData);
        System.out.println(decryptedData);
    }
}
