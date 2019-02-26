package org.d3ifcool.finpro.views.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    String username, password;
    final String dummyDosen = "dosen";
    final String dummyMahasiswa = "mahasiswa";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button b = findViewById(R.id.act_main_button_login);
        editTextUsername = findViewById(R.id.act_main_edittext_username);
        editTextPassword = findViewById(R.id.act_main_edittext_password);

        TextView lupaPas = findViewById(R.id.act_main_textview_klikdisini);

        lupaPas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, DosenMainActivity.class);
                startActivity(i);
            }
        });

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                login();

//                Intent i = new Intent(LoginActivity.this, DosenMainActivity.class);
//                startActivity(i);

                Intent i = new Intent(LoginActivity.this, MahasiswaMainActivity.class);
                startActivity(i);
            }
        });
    }

    public void login(){
        username = editTextUsername.getText().toString();
        password = editTextPassword.getText().toString();
        if (username.equalsIgnoreCase(dummyDosen) || password.equalsIgnoreCase(dummyDosen)){
            Intent i = new Intent(LoginActivity.this, DosenMainActivity.class);
            startActivity(i);
        } else if (username.equalsIgnoreCase(dummyMahasiswa) && password.equalsIgnoreCase(dummyMahasiswa)) {
            Intent j = new Intent(LoginActivity.this, MahasiswaMainActivity.class);
            startActivity(j);
        } else {
            Toast.makeText(getApplicationContext(), "Harus Di isi", Toast.LENGTH_SHORT).show();
        }
    }

}
