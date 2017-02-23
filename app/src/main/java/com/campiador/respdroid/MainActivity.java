package com.campiador.respdroid;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    private Spinner spinner_percentage;
    private Spinner spinner_file;
    private Button button;


    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<Integer> percentList = new ArrayList<>();
    private int mSelectedPercentage;
    private String mSelectedImgName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        spinner_percentage = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);
        spinner_file = (Spinner) findViewById(R.id.spinner_file);


        verifyStoragePermissions(this);

        imgList.add("a");
        imgList.add("b");
        imgList.add("c");
        imgList.add("b1");

        percentList.add(1);
        percentList.add(5);
        percentList.add(10);
        percentList.add(20);
        percentList.add(25);
        percentList.add(30);
        percentList.add(40);
        percentList.add(60);
        percentList.add(80);
        percentList.add(100);



//        for (String name: imgList
//             ) {
//            for (int percentage: percentList
//                 ) {
//                loadImage(name, percentage);
//            }
//        }

        ArrayAdapter<Integer> percentAdapter = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, percentList);
        percentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_percentage.setAdapter(percentAdapter);

        spinner_percentage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedPercentage = percentList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });


        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, imgList);
        percentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_file.setAdapter(nameAdapter);

        spinner_file.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSelectedImgName = imgList.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage(mSelectedImgName, mSelectedPercentage);
            }
        });


    }

    private void loadImage(String imgName, int percent) {

        File img = new File("/sdcard/Pictures/" + imgName +"." + percent + ".jpg");

        long startnow = android.os.SystemClock.uptimeMillis();


        Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
        long endnow = android.os.SystemClock.uptimeMillis();
        long duration = endnow - startnow;



        imageView.setImageBitmap(bitmap);

        String responsiveness = "responsive execution: ";



        if (duration > 100 && duration < 200) {
            responsiveness = "soft unresponsive execution: ";
        } else if (duration >= 200) {
            responsiveness = "hard unresponsive execution: ";

        }

        textView.setText(responsiveness + duration + " ms\n");


        Log.d("MYTAG", responsiveness + duration + " ms\n");
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     *
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int permission2 = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.READ_EXTERNAL_STORAGE);


        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

        if (permission2 != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }

    }



}
