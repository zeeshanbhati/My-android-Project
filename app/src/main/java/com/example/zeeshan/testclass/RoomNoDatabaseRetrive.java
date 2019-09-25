package com.example.zeeshan.testclass;

public class RoomNoDatabaseRetrive {

    private String facultyId;
    private String id;
    private String room_no;


    public RoomNoDatabaseRetrive(){}

    public RoomNoDatabaseRetrive(String facultyId, String id, String room_no) {
        this.facultyId = facultyId;
        this.id = id;
        this.room_no = room_no;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }
}
