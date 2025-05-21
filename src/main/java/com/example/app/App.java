package com.example.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        if (args.length == 0) {
            System.out.println("Usage: java App <command>");
            return;
        }

        String userInput = args[0];

        try {
            // Vulnerable: unsanitized user input passed to shell
            Process process = Runtime.getRuntime().exec("sh -c \"" + userInput + "\"");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}