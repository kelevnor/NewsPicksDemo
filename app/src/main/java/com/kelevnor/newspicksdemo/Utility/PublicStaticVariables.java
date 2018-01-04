package com.kelevnor.newspicksdemo.Utility;

import android.Manifest;

import java.util.ArrayList;

/**
 * Created by kelevnor on 1/3/18.
 */

public class PublicStaticVariables {

    public static ArrayList<String> imgUrls;

    static String[] REQUIRED_PERMISSIONS = {android.Manifest.permission.INTERNET, android.Manifest.permission.ACCESS_NETWORK_STATE,android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.READ_EXTERNAL_STORAGE};

    static String img_url_1 = "https://contents.newspicks.us/users/100013/cover?circle=true";
    static String img_url_2 = "https://contents.newspicks.us/users/100269/cover?circle=true";
    static String img_url_3 = "https://contents.newspicks.us/users/100094/cover?circle=true";
    static String img_url_4 = "https://contents.newspicks.us/users/100353/cover?circle=true";
    static String img_url_5 = "https://contents.newspicks.us/users/100019/cover?circle=true";
    static String img_url_6 = "https://contents.newspicks.us/users/100529/cover?circle=true";
}
