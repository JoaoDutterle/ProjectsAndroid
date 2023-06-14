package com.example.whatsapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.whatsapp.R;
import com.example.whatsapp.config.FirebaseConfig;
import com.example.whatsapp.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CadastroActivity extends AppCompatActivity {
    private TextInputEditText nameArea, emailArea, passwordArea;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        nameArea     = findViewById(R.id.editName);
        emailArea    = findViewById(R.id.editEmail);
        passwordArea = findViewById(R.id.editPassword);

    }

    public void registerUser(User user){
        auth = FirebaseConfig.getFirebaseAuthentication();
        auth.createUserWithEmailAndPassword(
                user.getEmail(), user.getPassword()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CadastroActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    String exception = "";
                    try {
                        throw task.getException();
                    }catch (FirebaseAuthWeakPasswordException e){
                        exception = "Enter a stronger password!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        exception = "Please, type a valid email.";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "This account has already been registered";
                    }catch (Exception e){
                        exception = "Error when registering user: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CadastroActivity.this, exception, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void validUser(View view){
        String nameText = nameArea.getText().toString();
        String emailText = emailArea.getText().toString();
        String passwordText = passwordArea.getText().toString();

        if(!nameText.isEmpty()){ //verify name
            if(!emailText.isEmpty()){ //verify email
                if(!passwordText.isEmpty()){ //verify password

                    User user = new User();
                    user.setName(nameText);
                    user.setEmail(emailText);
                    user.setPassword(passwordText);

                    registerUser(user);

                }else{
                    Toast.makeText(CadastroActivity.this,
                            "Set your password!",
                            Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(CadastroActivity.this,
                        "Set your email!",
                        Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(CadastroActivity.this,
                    "Set your name!",
                    Toast.LENGTH_SHORT).show();
        }

    }
}