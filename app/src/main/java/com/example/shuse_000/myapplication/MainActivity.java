package com.example.shuse_000.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends Activity {
    Button btn;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=(Button) findViewById(R.id.picBtn);
        imageView=(ImageView) findViewById(R.id.imgCap);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file= getFile();
                //camera.putExtra(MediaStore.EXTRA_OUTPUT,Url.fromFil);
            }
        });
    }

    private File getFile(){

        File folder = new File("sdoard/camara_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File img_file=new File(folder,"cam_img.jpg");
        return img_file;
    }

}
