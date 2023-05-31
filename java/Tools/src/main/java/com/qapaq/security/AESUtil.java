package com.qapaq.security;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

import com.qapaq.ConstantesTools;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase para encriptar y desencriptar cadenas de texto.
 * 
 * @author o.velez@qapaq.io
 * @since 2019-10-11
 * @see https://stackoverflow.com/questions/72601586/encryption-in-flutter-and-decryption-in-java-aes-256
 * 
 */
@Slf4j
public class AESUtil {    
    
    /**
     * Constructor de la clase.
     */
    private AESUtil() {
        super();
    }

    /**
     * Metodo para encriptar una cadena de texto.
     * 
     * @param item
     * @return
     */
    public static String encriptar(String item) {
        try {
            return encrypt(item);
        } catch (Exception e) {            
            log.error(e.toString());
            return "";
        }
    }

    /**
     * Metodo para encriptar una cadena de texto.
     * 
     * @param item
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws InvalidParameterSpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    private static String encrypt(String item)
            throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException {
        byte[] ivBytes;
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        byte[] saltBytes = bytes;

        SecretKeyFactory factory = SecretKeyFactory.getInstance(ConstantesTools.ALGORITMO_KEY);
        PBEKeySpec spec = new PBEKeySpec(ConstantesTools.getPasswordChar(), saltBytes, ConstantesTools.ITERACIONES, ConstantesTools.KEY_LENGTH);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), ConstantesTools.SPEC);
        Cipher cipher = Cipher.getInstance(ConstantesTools.TRANSFORMATION);
        cipher.init(Cipher.ENCRYPT_MODE, secret);

        AlgorithmParameters params = cipher.getParameters();
        ivBytes = params.getParameterSpec(IvParameterSpec.class).getIV();
        byte[] encryptedTextBytes = cipher.doFinal(item.getBytes(StandardCharsets.UTF_8));

        byte[] buffer = new byte[saltBytes.length + ivBytes.length + encryptedTextBytes.length];
        System.arraycopy(saltBytes, 0, buffer, 0, saltBytes.length);
        System.arraycopy(ivBytes, 0, buffer, saltBytes.length, ivBytes.length);
        System.arraycopy(encryptedTextBytes, 0, buffer, saltBytes.length + ivBytes.length, encryptedTextBytes.length);
        return new Base64().encodeToString(buffer);
    }

    /**
     * Metodo para desencriptar una cadena de texto.
     * 
     * @param item
     * @return
     */
    public static String desencriptar(String item) {
        try {
            return decrypt(item);
        } catch (Exception e) {            
            log.error(e.toString());
            return "";
        }
    }

    /**
     * Metodo para desencriptar una cadena de texto.
     * 
     * @param item
     * @param semilla
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws InvalidAlgorithmParameterException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws Exception
     */
    private static String decrypt(String encryptedText)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(ConstantesTools.TRANSFORMATION);
        ByteBuffer buffer = ByteBuffer.wrap(new Base64().decode(encryptedText));
        byte[] saltBytes = new byte[20];
        buffer.get(saltBytes, 0, saltBytes.length);
        byte[] ivBytes1 = new byte[cipher.getBlockSize()];
        buffer.get(ivBytes1, 0, ivBytes1.length);
        byte[] encryptedTextBytes = new byte[buffer.capacity() - saltBytes.length - ivBytes1.length];
        buffer.get(encryptedTextBytes);

        SecretKeyFactory factory = SecretKeyFactory.getInstance(ConstantesTools.ALGORITMO_KEY);
        PBEKeySpec spec = new PBEKeySpec(ConstantesTools.getPasswordChar(), saltBytes, ConstantesTools.ITERACIONES, ConstantesTools.KEY_LENGTH);
        SecretKey secretKey = factory.generateSecret(spec);
        SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), ConstantesTools.SPEC);
        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(ivBytes1));
        byte[] decryptedTextBytes = null;
        decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
        return new String(decryptedTextBytes);
    }
}
