package org.d3ifcool.finpro.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.LoginView;
import org.d3ifcool.service.models.User;
import org.d3ifcool.service.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private String username, password;

    private ProgressDialog progressDialog;
    private LoginPresenter presenter;
    private SessionManager sessionManager;

    private static final String ROLE_DOSEN = "dosen";
    private static final String ROLE_MAHASISWA = "mahasiswa";
    private static final String ROLE_KOOR = "koor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button b = findViewById(R.id.act_main_button_login);
        editTextUsername = findViewById(R.id.act_main_edittext_username);
        editTextPassword = findViewById(R.id.act_main_edittext_password);

        sessionManager = new SessionManager(LoginActivity.this);
        presenter = new LoginPresenter(this);

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getString(org.d3ifcool.dosen.R.string.text_progress_dialog));

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                login();
//                Intent j = new Intent(LoginActivity.this, MahasiswaMainActivity.class);
//                startActivity(j);

                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();
                presenter.getLogin(username, password);
            }
        });

        checkUserLogin(sessionManager.getSessionPengguna());
    }


    public void checkUserLogin(String cekPengguna){

        if (cekPengguna != null) {
            if (cekPengguna.equalsIgnoreCase(ROLE_MAHASISWA)){
                Intent j = new Intent(LoginActivity.this, MahasiswaMainActivity.class);
                startActivity(j);
                finish();
            } else if (cekPengguna.equalsIgnoreCase(ROLE_DOSEN)){
                Intent i = new Intent(LoginActivity.this, DosenMainActivity.class);
                startActivity(i);
                finish();
            } else if (cekPengguna.equalsIgnoreCase(ROLE_KOOR)) {
                Intent i = new Intent(LoginActivity.this, AdminMainActivity.class);
                startActivity(i);
                finish();
            }
        }

    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onRequestSuccess(String message, User user) {
        sessionManager.createSession(user.getUsername(), user.getPengguna());
        checkUserLogin(user.getPengguna());
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
