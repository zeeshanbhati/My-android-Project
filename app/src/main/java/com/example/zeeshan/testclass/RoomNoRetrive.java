package com.example.zeeshan.testclass;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RoomNoRetrive extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    RoomNoDatabaseRetrive room_no_get;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_no_retrive);
        room_no_get=new RoomNoDatabaseRetrive();

        listView=findViewById(R.id.roomlist);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Room");
        list = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this,R.layout.room_info,R.id.room_information,list);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    //Toast.makeText(RoomNoRetrive.this, "Getting Values", Toast.LENGTH_SHORT).show();

                    room_no_get= ds.getValue(RoomNoDatabaseRetrive.class);
                    //        Toast.makeText(RoomNoRetrive.this, "Succesfull added", Toast.LENGTH_SHORT).show();
                    list.add(room_no_get.getFacultyId()+"                             "+room_no_get.getRoom_no());
                }
                listView.setAdapter(adapter);
                Toast.makeText(RoomNoRetrive.this, "Displaying Values", Toast.LENGTH_SHORT).show();

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(RoomNoRetrive.this, "There is an error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}