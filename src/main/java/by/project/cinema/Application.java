package by.project.cinema;

import by.project.cinema.controller.MainController;
import by.project.cinema.util.PasswordEncrypt;

import java.io.UnsupportedEncodingException;

public class Application {
    public static void main(String[] args) {
        MainController.mainMenu();

/*
        String password = "`1Qazxsw2";
        byte[] salt = "secret".getBytes();

        byte[] encryptedPassword = PasswordEncrypt.getEncryptedPassword(password, salt);

        String str = null;
        try {
            str = new String(encryptedPassword, "windows-1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        byte[] b = null;
        try {
            b = str.getBytes("windows-1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



        String newPassword = "`1Qazxsw2";
        if (PasswordEncrypt.authenticate(newPassword, b, salt)) {
            System.out.println("passwords equal");
        } else System.out.println("passwords not equal");
*/

    }
}
