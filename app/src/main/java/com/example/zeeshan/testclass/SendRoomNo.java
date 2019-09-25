package com.example.zeeshan.testclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SendRoomNo extends AppCompatActivity {
    TextView Facultyinput;
    EditText Room_no;
    FirebaseAuth firebaseAuth;
    Button sendbtn;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_room_no);

        Facultyinput=findViewById(R.id.facultyid);
        Room_no=findViewById(R.id.room_no);
        sendbtn=findViewById(R.id.sendbtn);

        firebaseAuth=FirebaseAuth.getInstance();


        databaseReference= FirebaseDatabase.getInstance().getReference("Room");

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addroomno();

            }
        });
    }

    public void addroomno(){
        FirebaseUser user =firebaseAuth.getCurrentUser();


        String room_no = Room_no.getText().toString();

        if(TextUtils.isEmpty(room_no)){

            Toast.makeText(this, "Please Enter Valid Input", Toast.LENGTH_SHORT).show();
        }

        else{
            String id=databaseReference.push().getKey();
            RoomNoDatabaseSender Rn=new RoomNoDatabaseSender(id,user.getEmail(),room_no);

            databaseReference.child(id).setValue(Rn);
            Facultyinput.setText("");
            Room_no.setText("");
            Toast.makeText(this, "Task Done", Toast.LENGTH_SHORT).show();


        }


    }
}
