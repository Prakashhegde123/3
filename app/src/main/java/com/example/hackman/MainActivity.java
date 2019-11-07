package com.example.hackman;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private ImageView image;
    private EditText descriptionId;
    private Button btnid, btn_submit;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    private final int REQUEST_IMAGE_GALLERY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



      /*  final String strValue = simpleEditText.getText().toString();
        if (simpleEditText.hasFocus()){
            simpleEditText.setCursorVisible(true);
        }*/


        btn_submit = (Button) findViewById(R.id.submit_btn);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner_sem = (Spinner) findViewById(R.id.spinnerid);
                String sem_id = spinner_sem.getSelectedItem().toString();
                Spinner spinner_branch = (Spinner) findViewById(R.id.spinnerbranchid);
                String branch_id = spinner_branch.getSelectedItem().toString();
                descriptionId = (EditText) findViewById(R.id.description_id);
                String description_id = descriptionId.getText().toString();

                myUploadSection uploadSection = new myUploadSection();
                uploadSection.createEntry(sem_id, branch_id, description_id);

               Intent intent=new Intent(MainActivity.this,Second_activity.class);
               startActivity(intent);
            }
        });



        image = (ImageView) findViewById(R.id.image);
        btnid = (Button) findViewById(R.id.btnid);
        btnid.setOnClickListener(this);
        // btngallery=(Button) findViewById(R.id.btngallery);
        // btngallery.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //case R.id.btngallery:
            //Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            // if(intent.resolveActivity(getPackageManager())!=null){
            ///  startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
            //}
            // break;
            case R.id.btnid:
                Intent igal = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                igal.setType("image/*");
                startActivityForResult(igal, REQUEST_IMAGE_GALLERY);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                image.setImageBitmap(bitmap);
            } else if (requestCode == REQUEST_IMAGE_GALLERY) {
                Uri uri = data.getData();
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    image.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
