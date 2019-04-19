package org.d3ifcool.service.helpers;

import android.annotation.SuppressLint;
import android.content.Context;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MethodHelper {

    // Requirement Random Karakter -----------------------------------------------------------------
    private char[] chars = "1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String randomChar;
    // ---------------------------------------------------------------------------------------------


    public MethodHelper() {
    }

    // Method Random Character ---------------------------------------------------------------------
    public String getRandomChar() {
        for (int lenght = 0; lenght < 5; lenght++) {
            Character character = chars[random.nextInt(chars.length)];
            stringBuilder.append(character);
        }
        randomChar = stringBuilder.toString();
        stringBuilder.delete(0, 5);
        return randomChar;
    }
    // ---------------------------------------------------------------------------------------------

    public String getDateToday(){
        String FORMAT_DATE = "yyyy-MM-dd";
        @SuppressLint("SimpleDateFormat")
        DateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
        Date date = new Date();
        return dateFormat.format(date);
    }

}