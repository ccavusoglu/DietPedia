package com.dietpedia.app.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by Çağatay Çavuşoğlu on 26.06.2016.
 */
public class Utils {
    public static String REGULAR_FONT = "fonts/Roboto-Regular.ttf";
    public static String BOLD_FONT = "fonts/Roboto-Bold.ttf";
    public static String THIN_FONT = "fonts/Roboto-Thin.ttf";
    public static String LIGHT_FONT = "fonts/Roboto-Light.ttf";
    public static Typeface FONTTYPE_REGULAR;
    public static Typeface FONTTYPE_BOLD;
    public static Typeface FONTTYPE_LIGHT;
    private static int screenWidth = 0;
    private static int screenHeight = 0;

    public static void setTypefaces(Context context) {
        FONTTYPE_BOLD = Typeface.createFromAsset(context.getAssets(), Utils.BOLD_FONT);
        FONTTYPE_REGULAR = Typeface.createFromAsset(context.getAssets(), Utils.REGULAR_FONT);
        FONTTYPE_LIGHT = Typeface.createFromAsset(context.getAssets(), Utils.LIGHT_FONT);
    }

    public static int dpToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int getScreenHeight(Context c) {
        if (screenHeight == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenHeight = size.y;
        }

        return screenHeight;
    }

    public static int getScreenWidth(Context c) {
        if (screenWidth == 0) {
            WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            screenWidth = size.x;
        }

        return screenWidth;
    }
}