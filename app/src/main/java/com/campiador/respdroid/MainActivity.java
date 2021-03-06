package com.campiador.respdroid;

import android.Manifest;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
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

import com.campiador.respdroid.model.Operation;
import com.campiador.respdroid.model.RespNode;

import java.io.File;
import java.util.ArrayList;


//IMPORTANT NOTE: THREAD START RUNS ON ANOTHER THREAD, WHEREAS RUN RUNS ON THE UI THREAD

public class MainActivity extends AppCompatActivity {

    public static final String MYTAG = "RESPDROID_DYNAMIC";
    public static final String SDCARD_PICTURES = "/sdcard/Pictures/";
    public static final String SDCARD_PICTURES_SUN2012 = "/sdcard/Pictures/SUN2012/";
    public static final String SDCARD_PICTURES_SUN2012_A = "/sdcard/Pictures/SUN2012/a/";
    public static final String SDCARD_PICTURES_SUN2012_TEST = "/sdcard/Pictures/SUN2012/test/";
//    public static final String SDCARD_PICTURES_SUN2012_300KB_999KB =
//            "/sdcard/Pictures/SUN2012/sun2012_300k_999k/";
    public static final String SDCARD_PICTURES_SUN2012_1MB_10MB = "/sdcard/Pictures/SUN2012/1mb_10mb/";
    public static final int DEADLINE_HARD = 100;
    public static final int DEADLINE_SOFT = 200;
    // TODO: Eventually set this to 1000
    public static final int SUBLIST_LIMIT = 100;
    private static final boolean STRICT_MODE = false;
    public static final String RESPDROID_END_ITER = "end_iter";
    private ImageView imageView;
    private TextView textView;

    private Spinner spinner_percentage;
    private Spinner spinner_file;
    private Button button;


    ArrayList<String> imgList = new ArrayList<>();
    ArrayList<File> mSUN2012_1MB_10MB_FileList = new ArrayList<>();
    ArrayList<File> mSUN2012_1MB_10MB_FileSubList;
//    ArrayList<File> mSUN2012FileList = new ArrayList<>();
//    ArrayList<File> mSUN2012FileSubList;

//    ArrayList<File> mSUN2012_300K_999K_FileList = new ArrayList<>();
//    ArrayList<File> mSUN2012_300K_999K_FileSubList;

    ArrayList<Integer> percentList = new ArrayList<>();
    private int mSelectedPercentage;
    private String mSelectedImgName;

    ArrayList<Img> mSingleSizeList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (STRICT_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        imageView = (ImageView) findViewById(R.id.imageView);
        textView = (TextView) findViewById(R.id.textView);
        spinner_percentage = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.button);
        spinner_file = (Spinner) findViewById(R.id.spinner_file);


        verifyStoragePermissions(this);
        populate_lists();


//
        mSUN2012_1MB_10MB_FileList = Utils.get_all_files(new File(SDCARD_PICTURES_SUN2012_1MB_10MB));

        mSUN2012_1MB_10MB_FileSubList =
                new ArrayList<File>(mSUN2012_1MB_10MB_FileList.subList(0, SUBLIST_LIMIT));

//        mSUN2012_300K_999K_FileList= Utils.get_all_files(new File(SDCARD_PICTURES_SUN2012_300KB_999KB));
////
//        mSUN2012_300K_999K_FileSubList= new ArrayList<File>(mSUN2012_300K_999K_FileList.subList(0, SUBLIST_LIMIT/2));



//        Log.d(MYTAG, "onCreate: msublist: ");
//        for (File file : mSUN2012_1MB_10MB_FileSubList
//                ) {
//        Log.d(MYTAG, "onCreate: msublistitem: " + file.getName());
//        }
//        System.exit(1);

//        mSUN2012_1MB_10MB_FileList.add(flie)


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


//        ArrayList<String> mSUN2012NameList = new ArrayList<>();
//        for (File file : mSUN2012_1MB_10MB_FileList) {
//            mSUN2012NameList.add(file.getName());
//        }


//
//        ArrayAdapter<String> nameAdapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, mSUN2012NameSubList);
//        percentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner_file.setAdapter(nameAdapter);
//
//        spinner_file.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
////                mSelectedImgName = mSUN2012_1MB_10MB_FileSubList.get(position).getName();
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//
//
//        });
//
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadImage(new File(mSelectedImgName));
//            }
//        });

//    testImages();
//        testOneImage();

    }

    private void populate_lists() {
        //        imgList.add("a");
        imgList.add("b");
//        imgList.add("c");
//        imgList.add("b1");

//        percentList.add(1);
//        percentList.add(5);
//        percentList.add(10);
//        percentList.add(20);
//        percentList.add(25);
//        percentList.add(30);
        percentList.add(40);
        percentList.add(60);
//        percentList.add(80);
//        percentList.add(100);

        mSingleSizeList.add(new Img("a", 20));
        mSingleSizeList.add(new Img("b", 40));
        mSingleSizeList.add(new Img("b", 60));
        mSingleSizeList.add(new Img("c", 80));
        mSingleSizeList.add(new Img("b1", 100));
    }

    public int sizeInKB(File file){
        long size = file.length();

        return (int) (size/1024);
    }

    private Bitmap loadImage(File imgFile) {
        Bitmap bitmap = instrumentDecodeBitmap(imgFile);
        return bitmap;
    }


    private void displayImage(File img, Bitmap bitmap) {

        String operationType = Operation.DISPLAY;


//        BEFORE Instrumentation
        InstrumentDisplayImageBefore instrumentDisplayImageBefore = new InstrumentDisplayImageBefore(img).invoke();
        long startnow = instrumentDisplayImageBefore.getStartnow();
        CharSequence dateTime = instrumentDisplayImageBefore.getDateTime();

        imageView.setImageBitmap(bitmap);


//      After Instrumentation
        instrumentDisplayImageAfter(img, bitmap, operationType, startnow, dateTime);


    }



//    private void loadImage(String imgName, int percent) {
//
//        String fileName = imgName + "." + percent + ".jpg";
//        File img = new File(SDCARD_PICTURES + fileName);
//
//
//        CharSequence dateTime = android.text.format.DateFormat
//                .format("yyyy-MM-dd hh:mm:ss", new java.util.Date());
//
//        //INSTRUMENTATION: insert before (1 line)
//        long startnow = android.os.SystemClock.uptimeMillis();
////        BitmapFactory.Options options = new BitmapFactory.Options();
//
////        This is the function being instrumented
//        Bitmap bitmap = BitmapFactory.decodeFile(img.getAbsolutePath());
//
//
//        //INSTRUMENTATION: insert after (10 lines)
//        long endnow = android.os.SystemClock.uptimeMillis();
//        long duration = endnow - startnow;
//        String responsiveness = "responsive: ";
//        if (duration > DEADLINE_HARD && duration < DEADLINE_SOFT) {
//            responsiveness = "soft unresponsive execution: ";
//        } else if (duration >= DEADLINE_HARD) {
//            responsiveness = "hard unresponsive execution: ";
//        }
//
//        int appVersionCode = 0;
//        try {
//            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
//            String version = pInfo.versionName;
//            appVersionCode = pInfo.versionCode;
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        String activityName = "";
//        PackageManager packageManager = this.getPackageManager();
//        try {
//            ActivityInfo info = packageManager.getActivityInfo(this.getComponentName(), 0);
//            activityName = info.name;
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // Form results
//        RespNode respNode = new RespNode(0, 0, duration, String.valueOf(dateTime),
//                android.os.Build.MODEL, Operation.DECODE, fileName, img.length(), sizeInKB(img),
//                bitmap.getWidth(), bitmap.getHeight(), Utils.getApplicationName(getApplicationContext()),
//                getApplicationContext().getPackageName(), appVersionCode, Build.VERSION.RELEASE,
//                activityName);
//
////        Log.d(MYTAG, "Respnode in client:");
//        Log.d(MYTAG, respNode.serialize_to_Json());
////        Log.d(MYTAG, respNode.toString())
//
//
//
//
////        //LOG RESULTS
////        Log.d(MYTAG, "--" + responsiveness + "--" + duration + "--" + "ms" + "--"
////                + fileName + "--" + img.length() + "--" + android.os.Build.MODEL
////                + "--" + sizeInKB(img) + "--" + bitmap.getWidth() + "--" + bitmap.getHeight()
////                + "--" + dateTime);
//
//
//
//        imageView.setImageBitmap(bitmap);
//        textView.setText(responsiveness + duration + " ms\n");
//    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     * <p>
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

//    private void testOneImage() {
//        Thread tOne = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                loadImage(imgList.get(0), percentList.get(3));
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                //loadImage(imgList.get(2), percentList.get(3));
//
//            }
//        });
//        //tOne.start();
//        tOne.run();
//    }

    private int x = 0;
    private int y  = 0;


//    private Handler mHandler;

    @Override
    protected void onResume() {
        super.onResume();
//        mHandler = new Handler();

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                Log.d(MYTAG, "before sleep");
//                loadImage(imgList.get(x), percentList.get(y));
//                Log.d(MYTAG, "after sleep");
//                loadImage(imgList.get(2), percentList.get(3));
//
//            }
//        };

//        mHandler.postDelayed(runnable, 1000);

        //testOneImage();

        threadAll.run();
//        Note: this helps signal end of simulation, instead of waiting for the timeout
        Log.d(MYTAG, RESPDROID_END_ITER);


    }

//    private void testImages() throws InterruptedException {
//
//        Runnable img_runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                for (final String imgBase : imgList) {
//                    for (final int imgQuality : percentList) {
//                        android.os.SystemClock.sleep(3000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                loadImage(imgBase, imgQuality);
//                            }
//                        });
//                    }
//                }
//
//            }
//        };

//        Thread t = new Thread(img_runnable);
//
//        int img_base_max = imgList.size();
//        int img_percent_max = percentList.size();
//
//        for (int img_base_index = 0; img_base_index < img_base_max; img_base_index++) {
//            for (int img_percent_index = 0;
//                 img_percent_index < img_percent_max; img_percent_index++) {
//                mHandler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        loadImage(imgList.get(img_base_index), percentList.get(img_percent_index));
//                    }
//                }, (5000 * img_percent_max) * img_base_index + (img_percent_index * 5000));
//
//            }
//        }
//    }

    final Thread threadAll = new Thread(new Runnable() {
        @Override
        public void run() {
//            for (final String imgBase : imgList) {
//                for (final int imgQuality : percentList) {
            for (final File file : mSUN2012_1MB_10MB_FileSubList) {

                    sleepFunction();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Bitmap bmp = loadImage(file);
                            displayImage(file, bmp);

                    }
                });
            }
        }
    });



    private void sleepFunction() {
        try {
            threadAll.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private class InstrumentDisplayImageBefore {
        private File img;
        private CharSequence dateTime;
        private long startnow;

        public InstrumentDisplayImageBefore(File img) {
            this.img = img;
        }

        public CharSequence getDateTime() {
            return dateTime;
        }

        public long getStartnow() {
            return startnow;
        }

        public InstrumentDisplayImageBefore invoke() {
            dateTime = android.text.format.DateFormat
                    .format("yyyy-MM-dd hh:mm:ss", new java.util.Date());


            String absolutePath = img.getAbsolutePath();


            //INSTRUMENTATION: insert before (1 line)
            startnow = android.os.SystemClock.uptimeMillis();
            return this;
        }
    }

    private void instrumentDisplayImageAfter(File img, Bitmap bitmap, String operationType, long startnow, CharSequence dateTime) {
        //INSTRUMENTATION: insert after (10 lines)
        long endnow = android.os.SystemClock.uptimeMillis();
        long duration = endnow - startnow;
        String responsiveness = "responsive: ";
        if (duration > DEADLINE_HARD && duration < DEADLINE_SOFT) {
            responsiveness = "soft unresponsive execution: ";
        } else if (duration >= DEADLINE_HARD) {
            responsiveness = "hard unresponsive execution: ";
        }

        int appVersionCode = 0;
        try {
            PackageInfo pInfo = this.getPackageManager().getPackageInfo(getPackageName(), 0);
            appVersionCode = pInfo.versionCode;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String activityName = "";
        PackageManager packageManager = this.getPackageManager();
        try {
            ActivityInfo info = packageManager.getActivityInfo(this.getComponentName(), 0);
            activityName = info.name;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        // Form results
        RespNode respNode = new RespNode(0, 0, duration, String.valueOf(dateTime),
                Build.MODEL, operationType, img.getName(), img.length(), sizeInKB(img),
                bitmap.getWidth(), bitmap.getHeight(), Utils.getApplicationName(getApplicationContext()),
                getApplicationContext().getPackageName(), appVersionCode, Build.VERSION.RELEASE,
                activityName);

        Log.d(MYTAG, respNode.serialize_to_Json());
    }

    @NonNull
    private Bitmap instrumentDecodeBitmap(File imgFile) {
        String operationType = Operation.DECODE;


        CharSequence dateTime = android.text.format.DateFormat
                .format("yyyy-MM-dd hh:mm:ss", new java.util.Date());

        //INSTRUMENTATION: insert before (1 line)
        long startnow = android.os.SystemClock.uptimeMillis();

        String absolutePath = imgFile.getAbsolutePath();

//        This is the function being instrumented
        Bitmap bitmap = BitmapFactory.decodeFile(absolutePath);
//        BitmapFactory.Options options = new BitmapFactory.Options();


        //INSTRUMENTATION: insert after (10 lines)
        instrumentDisplayImageAfter(imgFile, bitmap, operationType, startnow, dateTime);
        return bitmap;
    }

}
