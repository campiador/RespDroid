package com.campiador.respdroid;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by behnam on 10/20/17.
 */

public class Utils {

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }
}
