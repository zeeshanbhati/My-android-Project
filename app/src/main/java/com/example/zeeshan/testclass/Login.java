package com.example.zeeshan.testclass;

import
        android.app.ProgressDialog;
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

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText edittextsigninemail;
    private EditText edittextsigninpassword;
    private Button signinbutton;
    private TextView accounttext;
    private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;
    private SignInButton gsignbtn;
    private final static int RC_SIGN_IN = 2;
    //
    // private String client_id="97205237494-e118cgaj0dotddpbtvsj0kl107nvbaed.apps.googleusercontent.com";

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), AfterLogin1.class));


        }

        edittextsigninemail = findViewById(R.id.edittextmailSignin);
        edittextsigninpassword = findViewById(R.id.edittextSigninpass);
        signinbutton = findViewById(R.id.buttonsignin);
        progress = new ProgressDialog(this);
        accounttext = findViewById(R.id.textviewsignin);
        gsignbtn = findViewById(R.id.googlebtn);

        // Build a GoogleSignInClient with the options specified by gso.


        signinbutton.setOnClickListener(this);
        accounttext.setOnClickListener(this);
        gsignbtn.setOnClickListener(this);


    }

    private void userLogin() {
        String email = edittextsigninemail.getText().toString().trim();
        String password = edittextsigninpassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Enter Valid Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Logging in");
        progress.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progress.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(), AfterLogin1.class));

                        }

                    }
                });

    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    @Override
    public void onClick(View v) {
        if (v == signinbutton) {
            userLogin();
        }
        if (v == accounttext) {
            finish();
            startActivity(new Intent(this, SignUp.class));

        }
        if (v == gsignbtn) {
            signIn();

        }


    }
    // Configure Google Sign In

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                progress.setMessage("Signing In");
                progress.show();
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {

                // Google Sign In failed, update UI appropriately
                //  Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this, "Failed Sign in", Toast.LENGTH_SHORT);
                // ...
            }
        }
    }



    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {


        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            Toast.makeText(Login.this, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
                           // Toast.makeText(Login.this,""+user.getEmail(),Toast.LENGTH_SHORT).show();
                           // startActivity(new Intent(Login.this,AfterLogin1.class));
                           // finish();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login.this,"Failed to Sign in",Toast.LENGTH_SHORT).show();

                           // updateUI(null);
                        }

                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login.this,"Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI(FirebaseUser user){
        if (user != null){
            startActivity(new Intent(this,AfterLogin1.class));
            finish();
        }
    }





}
