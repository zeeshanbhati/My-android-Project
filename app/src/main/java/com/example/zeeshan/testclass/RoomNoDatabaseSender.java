package com.example.zeeshan.testclass;

public class RoomNoDatabaseSender {

    private String id;
    private String facultyId;
    private String room_no;

    public RoomNoDatabaseSender(){}

    public RoomNoDatabaseSender(String id, String facultyId, String room_no) {
        this.id = id;
        this.facultyId = facultyId;
        this.room_no = room_no;
    }

    public String getId() {
        return id;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public String getRoom_no() {
        return room_no;
    }
}

