package com.highgo.restaurentapp.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.highgo.restaurentapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Utils {


    public static int MY_CAMERA_REQUEST = 1001;
    public static int MY_STORAGE_REQUEST = 1002;

    /**
     * CHECK STRING IS EMPTY OR NULL
     **/

    public static String checkString(String value) {
        String str;
        if (value == null || value.equals("") || value.equals("null")
                || value.trim().length() == 0) {
            str = "";
        } else
            str = value;
        return str;
    }

    /**
     * SNACK BAR FOR ALERTS
     **/

    public static void showToast(String message) {
        Toast.makeText(BaseApplication.getInstance(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * STRING CAPITALIZE
     **/
    public static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    /**
     * ASSIGN THE COLOR
     **/
    @SuppressWarnings("deprecation")
    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23)
            return ContextCompat.getColor(context, id);
        else
            return context.getResources().getColor(id);
    }

    /**
     * ASSIGN THE DRAWABLE
     **/
    @SuppressWarnings("deprecation")
    public static Drawable getDrawable(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 21) {
            return ContextCompat.getDrawable(context, id);
        } else {
            return context.getResources().getDrawable(id);
        }
    }

    /**
     * ASSIGN THE DIMENS
     **/
    public static int getDimen(Context context, int id) {
        return (int) context.getResources().getDimension(id);
    }

    /**
     * ASSIGN THE STRINGS
     **/
    public static String getStrings(Context context, int id) {
        String value = null;
        if (context != null && id != -1) {
            value = context.getResources().getString(id);
        }
        return value;
    }


    public static void fullScreenActivity(Activity activity) {
        try {
            activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            activity.getWindow().setGravity(Gravity.CENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showImage(ImageView imageView, String url) {
        try {
            Glide.with(imageView.getContext()).load(url)
                    .apply(RequestOptions.placeholderOf(R.mipmap.ic_launcher).transform(new CircleCrop()).dontAnimate()).into(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * CAMERA PERMISSIONS
     **/
    public static boolean CameraPermission(final Context context) {
        int currentAPIVersion = Build.VERSION.SDK_INT;
        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context,
                        Manifest.permission.CAMERA)) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_REQUEST);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.CAMERA
                    }, MY_CAMERA_REQUEST);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    /**
     * method to detect if device is connected by any network
     **/

    public static boolean isNetworkAvailable(Context ctx) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) ctx
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || mConnectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }

    public static void changeStatusBarColor(Integer color, Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            window.setStatusBarColor(color);
        }
    }



    public static String splitTextWithSpace(String data) {
        String result;
        if (data != null&&!data.isEmpty()) {
            String[] split_text = data.split(" ");
            result = split_text[1];
        } else {
            result = "";
        }
        return result;
    }

    public static JSONObject getHeaders() {
        JSONObject headers = new JSONObject();
        try {
            headers.put("Content-Type", "x-www-form-urlencoded");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return headers;
    }



    public static int StatusBarLightMode(Activity activity, boolean show) {
        int result = 0;
        if (MIUISetStatusBarLightMode(activity, show)) {
            result = 1;
        } else if (FlymeSetStatusBarLightMode(activity.getWindow(), show)) {
            result = 2;
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            setStatusBarIconColor(activity, show);
            result = 3;
        }
        return result;
    }


    public static boolean MIUISetStatusBarLightMode(Activity activity, boolean dark) {
        boolean result = false;
        Window window = activity.getWindow();
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag;
                @SuppressLint("PrivateApi")
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (dark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);
                }
                result = true;

                setStatusBarIconColor(activity, dark);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static boolean FlymeSetStatusBarLightMode(Window window, boolean dark) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class
                        .getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class
                        .getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (dark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }


    public static void setStatusBarIconColor(Activity activity, boolean show) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = activity.getWindow().getDecorView();
            if (show) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }

    }


    public static boolean checkExternalStoragePermission(final Context context) {

        int currentAPIVersion = Build.VERSION.SDK_INT;

        if (currentAPIVersion >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) +
                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                        ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                ) {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_STORAGE_REQUEST);
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, MY_STORAGE_REQUEST);
                }
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    public static void hideKeyboard(Context mContext) {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        HashMap<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return map;
    }

    private static List<Object> toList(JSONArray array) throws JSONException {
        List<Object> list = new ArrayList<Object>();
        for (int i = 0; i < array.length(); i++) {
            Object value = array.get(i);
            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            } else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            list.add(value);
        }
        return list;
    }

    public static boolean compareDates(String date) {
        String currentDate;
        Date strDate = null;
        Date todayDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        try {
            strDate = sdf.parse(date);
            currentDate = sdf.format(new Date());
            todayDate = sdf.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (todayDate != null)
            return todayDate.after(strDate);
        else
            return false;
    }

    public static String getTimeFromMilliseconds(String milliseconds) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.ENGLISH);
            long milliSeconds = Long.parseLong(milliseconds);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(milliSeconds);
            return sdf.format(calendar.getTime());
        } catch (NumberFormatException e) {
            e.getLocalizedMessage();
        }
        return "";
    }

    public static String getApmPmTime(String time) {
        String newTime = "";
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            final Date dateObj = sdf.parse(time);
            assert dateObj != null;
            newTime = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH).format(dateObj);
        } catch (final ParseException e) {
            e.printStackTrace();
        }
        return !TextUtils.isEmpty(newTime) ? newTime : time;
    }

    public static String getDayFormatDate(String date) {
        String parsedDate = "";
        long days = getDateDiff(date);
        if (days == 0) {
            return "Today";
        } else if (days == 1) {
            return "Yesterday";
        } else {
            SimpleDateFormat fromSdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            SimpleDateFormat toSdf = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
            try {
                Date dateObj = fromSdf.parse(date);
                assert dateObj != null;
                parsedDate = toSdf.format(dateObj);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return !TextUtils.isEmpty(parsedDate) ? parsedDate : date;
        }

    }

    public static String changeDateFormat(String date) {
        String converted = "";

        SimpleDateFormat fromSdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        SimpleDateFormat toSdf = new SimpleDateFormat("dd, MMM yyyy", Locale.ENGLISH);
        try {
            Date dateObj = fromSdf.parse(date);
            assert dateObj != null;
            converted = toSdf.format(dateObj);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return converted;

    }

    private static long getDateDiff(String oldDate) {
        SimpleDateFormat fromSdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            return TimeUnit.DAYS.convert(new Date().getTime() - Objects.requireNonNull(fromSdf.parse(oldDate)).getTime(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
