package com.example.whatsapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.model.User;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText emailArea, passwordArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailArea = findViewById(R.id.editEmailLogin);
        passwordArea = findViewById(R.id.editPasswordLogin);

    }

    public void userLogin(View view){
        String emailText = emailArea.getText().toString();
        String passwordText = passwordArea.getText().toString();

        if(!emailText.isEmpty()){ //verify email
            if(!passwordText.isEmpty()){ //verify password

            }else{
                Toast.makeText(LoginActivity.this,
                        "Type your password!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(LoginActivity.this,
                    "Type your email!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void openCadastro(View view){
        Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
        startActivity(intent);
    }
}