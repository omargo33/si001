package com.qapaq.si001001.tools;

import android.content.res.Resources;

/**
 * Object for get information about Screen.
 *
 * @author o.velez@qapaq.io
 */
public class Display {

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
