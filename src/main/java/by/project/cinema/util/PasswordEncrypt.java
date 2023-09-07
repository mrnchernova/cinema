package by.project.cinema.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;


public class PasswordEncrypt {

    public static boolean authenticate(String InputPassword, byte[] encryptedPassword, byte[] salt) {
        byte[] encryptedInputPassword = getEncryptedPassword(InputPassword, salt);
        return Arrays.equals(encryptedPassword, encryptedInputPassword);
    }

    public static byte[] getEncryptedPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 4096, 128);
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (f != null) {
            try {
                return f.generateSecret(spec).getEncoded();
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static byte[] generateSalt(String username) {
        String salt = new StringBuilder(username).reverse().toString();
        return salt.getBytes();
    }

    public static String EncryptPassword(String username, String newPassword) {
        byte[] salt = PasswordEncrypt.generateSalt(username);
        byte[] encryptedPassword = PasswordEncrypt.getEncryptedPassword(newPassword, salt);
        try {
            return (new String(encryptedPassword, "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

}


