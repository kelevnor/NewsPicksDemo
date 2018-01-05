package com.kelevnor.newspicksdemo.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kelevnor on 1/3/18.
 */

public class Dummy_Model_List {
    @SerializedName("id")
    @Expose
    private ArrayList<Dummy_Model> list;

    public ArrayList<Dummy_Model> getList() {
        return list;
    }

    public void setList(ArrayList<Dummy_Model> list) {
        this.list = list;
    }



}
