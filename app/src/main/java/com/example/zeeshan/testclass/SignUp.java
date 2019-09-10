package com.example.zeeshan.testclass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private Button b1;
    private EditText u_name, pass;
    private TextView signin;
    private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        progress = new ProgressDialog(this);
        b1 = findViewById(R.id.submit);
        u_name = findViewById(R.id.edittxtmail);
        pass = findViewById(R.id.edittxtpassword);
        signin = findViewById(R.id.view1);



        b1.setOnClickListener(this);
        signin.setOnClickListener(this);
    }

    private void registeruser() {
        String email = u_name.getText().toString().trim();
        String pword = pass.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            //Enter Valid Email
            Toast.makeText(this, "Pleas Enter Valid EMail", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(pword)) {
            //Enter Password
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Registering User");
        progress.show();
        firebaseAuth.createUserWithEmailAndPassword(email, pword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //User Logged in Successfully
                            Toast.makeText(SignUp.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));

                        } else {
                            Toast.makeText(SignUp.this, "Failed To register.Try Again", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
    }


    @Override
    public void onClick(View v) {
        if(v == b1){
            registeruser();
        }
        if(v == signin){

            startActivity(new Intent(this,Login.class));

    }
    }
}