package com.example.shana.camerasystemdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    private File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img= (ImageView) findViewById(R.id.img);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,getString(R.string.openPhoto));
        menu.add(0,1,0,getString(R.string.openandsavePhoto));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
//                方式一:
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case 1:
                takePhoto();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void takePhoto() {
        String path= Environment.getExternalStorageDirectory().getPath()+"/";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhhmmss");
        String filename= sdf.format(new Date(System.currentTimeMillis()));
        file=new File(path+filename+".jpeg");
        Uri uri=Uri.fromFile(file);
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(file.exists()){
            img.setImageURI(Uri.fromFile(file));
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
