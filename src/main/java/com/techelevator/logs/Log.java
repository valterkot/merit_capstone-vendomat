package com.techelevator.logs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalTime;


public class Log {
    static String logFile = "logs/vending.log";

    //METHOD
    public static void log(String message){
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(logFile, true))) {
            logWriter.println(LocalTime.now() + " " + message);
        } catch (FileNotFoundException e) {
            System.err.println("Cannot open the file for writing.");
        }

    }

}
