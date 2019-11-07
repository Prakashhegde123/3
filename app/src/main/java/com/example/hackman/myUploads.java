package com.example.hackman;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class myUploads {
    private int SEM;
    private String Branch;
    private String Desc;

    myUploads(int SEM, String Branch, String Desc){
        this.SEM = SEM;
        this.Branch = Branch;
        this.Desc = Desc;
    }

}
