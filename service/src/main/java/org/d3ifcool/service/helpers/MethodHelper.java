package org.d3ifcool.service.helpers;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import org.d3ifcool.service.R;
import org.d3ifcool.service.models.KategoriJudul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MethodHelper {

    // Requirement Random Karakter -----------------------------------------------------------------
    private char[] chars = "1234567890".toCharArray();
    private StringBuilder stringBuilder = new StringBuilder();
    private Random random = new Random();
    private String randomChar;
    // ---------------------------------------------------------------------------------------------

    public MethodHelper(Context context) {
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

}