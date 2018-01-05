package com.kelevnor.newspicksdemo.Utility;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.kelevnor.newspicksdemo.R;

/**
 * Created by kelevnor on 1/3/18.
 * Includes all methods that require Context to
 * achieve a UI change. (Popups, ProgressBars etc)
 */

public class Utility_Helper_Context {

    //Pop up view that changes its context depending on case
    public static void showSortPopup(final Activity context, String title)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.alert_indicator_custom, null);

        // Creating the PopupWindow
        final PopupWindow changeSortPopUp = new PopupWindow(context);
        changeSortPopUp.setContentView(layout);
        changeSortPopUp.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        changeSortPopUp.setFocusable(true);

        TextView titletv = (TextView) layout.findViewById(R.id.tv_title);

        titletv.setText(title);
        // Clear the default translucent background
        changeSortPopUp.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        changeSortPopUp.showAtLocation(layout, Gravity.CENTER, 0,0);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.btn_dismiss);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                changeSortPopUp.dismiss();
            }
        });
    }

    //Method to request permissions on LoginActivity
    public static boolean requestPemissions(Activity act, int PERMISSION_REQUEST){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && act.getApplicationContext() != null && PublicStaticVariables.REQUIRED_PERMISSIONS != null) {
            for (String permission : PublicStaticVariables.REQUIRED_PERMISSIONS) {
                if (ActivityCompat.checkSelfPermission(act.getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                    act.requestPermissions(new String[]{permission}, PERMISSION_REQUEST);
                }
            }
        }
        return true;
    }

    //Method to check for internet connectivity
    public static boolean hasInternet(Activity act) {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        } else
            connected = false;
        return connected;
    }

}
