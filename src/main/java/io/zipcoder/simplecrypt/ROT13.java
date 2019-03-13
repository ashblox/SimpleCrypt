package io.zipcoder.simplecrypt;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class ROT13  {

    private static String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
    private String alphabetWhole;
    private String keyWhole;

    ROT13(Character cs, Character cf) {
        cs = Character.toUpperCase(cs);
        cf = Character.toUpperCase(cf);
        String upperRotOnce = rotate(alphabetUpper, cs);
        String lowerRotOnce = rotate(alphabetLower, Character.toLowerCase(cs));
        alphabetWhole = upperRotOnce + " " + lowerRotOnce;
        String upperRotTwice = rotate(upperRotOnce, cf);
        String lowerRotTwice = rotate(lowerRotOnce, Character.toLowerCase(cf));
        keyWhole = upperRotTwice + " " + lowerRotTwice;
    }

    ROT13() {
        keyWhole = "NOPQRSTUVWXYZABCDEFGHIJKLM nopqrstuvwxyzabcdefghijklm";
    }

    public String crypt(String text) throws UnsupportedOperationException {
        String crypted = "";
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            if (isAlphabetical(c)) {
                int index = alphabetWhole.indexOf(c);
                crypted += keyWhole.charAt(index);
            } else {
                crypted += c;
            }
        }
        return crypted;
    }

    public String encrypt(String text) {
        return crypt(text);
    }

    public String decrypt(String text) {
        String decrypted = "";
        for (int i = 0; i < text.length(); i++) {
            Character c = text.charAt(i);
            if (isAlphabetical(c)) {
                int index = keyWhole.indexOf(c);
                decrypted += alphabetWhole.charAt(index);
            } else {
                decrypted += c;
            }
        }
        return decrypted;
    }

    public static String rotate(String s, Character c) {
        int indexOfChar = s.indexOf(c);
        String substr = s.substring(0, indexOfChar);
        String substr2 = s.substring(indexOfChar);
        return substr2.concat(substr);
    }

    public static boolean isAlphabetical(Character c) {
        return c>64 && c<91 || c>96 && c<123;
    }

    public void encryptFile(String fileName, String newFileName) throws IOException {
//        "/Users/ashleybloxom/Dev/SimpleCrypt/sonnet18.txt"
        Scanner in = new Scanner(Paths.get(fileName), "UTF-8");
        PrintWriter out = new PrintWriter(newFileName, "UTF-8");
        while (in.hasNextLine()) {
            String line = in.nextLine();
            out.println(encrypt(line));
        }
        out.close();
    }

    public static void main(String[] args) throws IOException {
        ROT13 rot13 = new ROT13('a', 'n');
        rot13.encryptFile("sonnet18.txt", "sonnet18_encrypted.txt");
        rot13.encryptFile("sonnet18_encrypted.txt", "sonnet18_encrypt_decrypted.txt");
    }

}
