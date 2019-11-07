package com.example.hackman;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class myUploadSection {
    private static final String TAG = "myUploadSection";

    public void createEntry(String SEM, String Branch, String Desc){

        Calendar calender = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(calender.getTime());
        Log.d(TAG, "date :" + date);

        Map<String, Object> city = new HashMap<>();
        city.put("Branch", Branch);
        city.put("DateAndTime", date);
        city.put("Description", Desc);
        city.put("Sem", SEM);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Random r = new Random(1000);
        int randNum = r.nextInt();
        String num = Integer.toString(randNum);

        db.collection("uploads").document(num)
                .set(city)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w(TAG, "Success writing document :)");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document :(", e);
                    }
                });

        /*myUploads myUp=new myUploads(Integer.parseInt(SEM),Branch,Desc);
        // ==================================================

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        // ==================================================
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentReference = db.collection("uploads").document();
        documentReference.set(myUp).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "successful!!!!");
                }
                else {
                    Log.d(TAG, "failed :(");
                }
            }
        });*/
    }
}
