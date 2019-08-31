package com.example.zeeshan.testclass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AfterLogin1 extends AppCompatActivity implements View.OnClickListener{
   private FirebaseAuth firebaseAuth;
   private TextView email;
   private Button Logout;
    private GoogleSignInClient mGoogleSignInClient;
    private ProgressDialog progress;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login1);
        email=findViewById(R.id.textviewafterlogin);
        Logout=findViewById(R.id.logoutbutton);
        mDrawerLayout=findViewById(R.id.drawer);
        Logout.setOnClickListener(this);


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);


        //Drawer Layout
        mToggle=new ActionBarDrawerToggle(this,mDrawerLayout,R.string.open,R.string.close);
        mToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        //Navigation Drawer has ended

        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        FirebaseUser user =firebaseAuth.getCurrentUser();
        email.setText("Welcome: "+user.getEmail());
        progress = new ProgressDialog(this);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void signOut() {

        mGoogleSignInClient.signOut()

                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v==Logout){

            firebaseAuth.signOut();
            signOut();

            finish();


            startActivity(new Intent(this,Login.class));
        }


    }
}
