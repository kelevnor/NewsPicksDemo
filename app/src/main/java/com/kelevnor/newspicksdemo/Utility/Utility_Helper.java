package com.kelevnor.newspicksdemo.Utility;

import android.content.Context;
import com.kelevnor.newspicksdemo.Models.Dummy_Model;
import com.kelevnor.newspicksdemo.R;

import java.util.ArrayList;

/**
 * Created by kelevnor on 1/2/18.
 * Includes all methods that not require Context
 * and do not affect the UI directly
 */

public class Utility_Helper {

    public static ArrayList<Dummy_Model> populateListWithDummies(Context con){
        ArrayList<Dummy_Model> myDummyList = new ArrayList<>();
        for (int i =1; i<11; i++){
            Dummy_Model temp = new Dummy_Model();
            temp.setId(i);
            temp.setName(con.getResources().getString(R.string.demouser)+i+con.getResources().getString(R.string.dummytemp));
            temp.setPassword(con.getResources().getString(R.string.dummypass)+i);
            myDummyList.add(temp);
        }
        return myDummyList;
    }

    public static boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    public static boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

}
