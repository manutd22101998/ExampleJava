/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examc1608l;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author Nahut Family
 */
public class JavaSecurity {

    public static String encrypt(String key, String initVector, String password) {
        try {
            IvParameterSpec ivp = new IvParameterSpec(initVector.getBytes());

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, skey, ivp);

            byte[] encryptByte = cipher.doFinal(password.getBytes());
            return Base64.encodeBase64String(encryptByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static void main(String[] args) {
        String password = "Hello World";
        String key = "HelloWorld123456";
        String ivParameterSpec = "HelloWorld123456";
        String ens = encrypt(key, ivParameterSpec, password);
        System.out.println(ens);
        System.out.println(decrypt(key, ivParameterSpec, ens));
    }

    public static String decrypt(String key, String initVector, String encrypted) {
        try {
            IvParameterSpec ivp = new IvParameterSpec(initVector.getBytes());

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            cipher.init(Cipher.DECRYPT_MODE, skey, ivp);

            byte[] decryptByte = cipher.doFinal(Base64.decodeBase64(encrypted));
            return new String(decryptByte);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
