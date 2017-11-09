package com.campiador.respdroid;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by behnam on 10/20/17.
 */

public class Utils {

    private static ArrayList<File> mFilePaths = new ArrayList<>();;

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }



    public static ArrayList<File> get_all_files(File root) {

        File[] list = root.listFiles();


        for (File file : list) {
            if (file.isDirectory()) {
                Log.d(MainActivity.MYTAG, "Dir: " + file.getAbsoluteFile());
                get_all_files(file);
            }
            else {
                Log.d(MainActivity.MYTAG, "File: " + file.getAbsoluteFile());
                mFilePaths.add(file.getAbsoluteFile());

            }
        }

        return mFilePaths;
    }

}
