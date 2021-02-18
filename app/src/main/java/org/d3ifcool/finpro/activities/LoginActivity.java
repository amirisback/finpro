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
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.LoginView;
import org.d3ifcool.finpro.core.models.User;
import org.d3ifcool.finpro.core.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private String username, password;

    private ProgressDialog progressDialog;
    private LoginPresenter loginPresenter;
    private SessionManager sessionManager;

    private static final String ROLE_DOSEN = "dosen";
    private static final String ROLE_MAHASISWA = "mahasiswa";
    private static final String ROLE_KOOR = "koor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button_login = findViewById(R.id.act_main_button_login);
        editTextUsername = findViewById(R.id.act_main_edittext_username);
        editTextPassword = findViewById(R.id.act_main_edittext_password);

        sessionManager = new SessionManager(this);
        loginPresenter = new LoginPresenter(this);
        loginPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.finpro.R.string.text_progress_dialog));

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editTextUsername.getText().toString();
                password = editTextPassword.getText().toString();

                if (username.isEmpty()){
                    editTextUsername.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (password.isEmpty()){
                    editTextPassword.setError(getString(R.string.text_tidak_boleh_kosong));
                } else {
                    loginPresenter.getLogin(username, password);
                }
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
                Intent k = new Intent(LoginActivity.this, KoorMainActivity.class);
                startActivity(k);
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
    public void onRequestSuccess(User user) {
        sessionManager.createSession(user.getUsername(), user.getPengguna());
        checkUserLogin(user.getPengguna());
    }

    @Override
    public void isEmptyObjectLogin() {
        String failedMessage = "Id Pengguna atau Password Salah";
        Toast.makeText(this, failedMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailed(String message) {
        String failedMessage = "Id Pengguna atau Password Salah";
        Toast.makeText(this, failedMessage, Toast.LENGTH_SHORT).show();
    }
}
