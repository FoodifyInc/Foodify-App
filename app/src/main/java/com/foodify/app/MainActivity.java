package com.foodify.app;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shuse_000.myapplication.R;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity {
    private Button cameraButton;
    private ImageView imageView;
    private static final int CAM_REQUEST = 1001;
    private static final int IMAGE_PERMISSION = 4 ;
    private String mCurrentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        cameraButton = findViewById(R.id.picBtn);
        imageView = findViewById(R.id.imgCap);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Required camera permission
                String[] permissions = {"android.permission.CAMERA"};
                //Intent to startCamera
                Intent startCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //Ask for permissions
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager
                        .PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, IMAGE_PERMISSION);
                }
                //If permission is already granted
                else if(startCameraIntent.resolveActivity(getPackageManager()) != null){
                    File file = createImageFile();
                    startCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(startCameraIntent, CAM_REQUEST);
                }
            }
        });
    }

    private File createImageFile(){
        //Create image filename
        String imageFileName = "JPEG_00";

        //Access storage directory for photos and create temporary image file
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = null;
        try {
            image = File.createTempFile(imageFileName,".jpg",storageDir);
            Log.w("APP", "File created");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Store file path for usage with intents
        assert image != null;
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}

