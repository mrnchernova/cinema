package by.project.cinema.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class PasswordEncrypt {

     public static boolean authenticate(String InputPassword, byte[] encryptedPassword, byte[] salt) {
         byte[] encryptedInputPassword = new byte[0];
         try {
             encryptedInputPassword = getEncryptedPassword(InputPassword, salt);
         } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
             e.printStackTrace();
         }
         return Arrays.equals(encryptedPassword, encryptedInputPassword);
     }

     public static byte[] getEncryptedPassword(String password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
     KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 20000, 128);
     SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
     return f.generateSecret(spec).getEncoded();
     }

//     public static byte[] generateSalt() throws NoSuchAlgorithmException {
//     SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
//     byte[] salt = new byte[8];
//     random.nextBytes(salt);
//     return salt;
//     }


}
