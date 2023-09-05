package by.project.cinema;

import by.project.cinema.controller.MainController;

public class Application {
    public static void main(String[] args) {
        MainController.mainMenu();
        
        /*
        String s = "password";
        byte[] salt = PasswordEncrypt.generateSalt();
        String saltStr = new String(salt, "UTF-8");

        System.out.println(s);
        System.out.println(saltStr);

        byte[] encryptedPassword = PasswordEncrypt.getEncryptedPassword(s, salt);

        
        String newS = "password";
        if (PasswordEncrypt.authenticate(newS, encryptedPassword, salt)){
            System.out.println("passwords equal");
        }else System.out.println("passwords not equal");
*/
    }
    
         
}
