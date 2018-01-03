package com.kelevnor.newspicksdemo.Tasks;

import android.app.Activity;
import android.os.AsyncTask;

import com.kelevnor.newspicksdemo.Models.Dummy_Model;
import com.kelevnor.newspicksdemo.R;

import java.util.ArrayList;

/**
 * Created by kelevnor
 */


public class Simulate_Login_Task extends AsyncTask<Void, Void, Void> {

    Activity act;
    boolean completedCall = false;
    OnAsyncResult onAsyncResult;
    ArrayList<Dummy_Model> list;
    String username;
    String password;
    /**
     * @author Marios Sifalakis
     */
    public Simulate_Login_Task(Activity act, ArrayList<Dummy_Model> list, String username, String password){
        this.act = act;
        this.list = list;
        this.username = username;
        this.password = password;
    }

    public void setOnResultListener(OnAsyncResult onAsyncResult) {
        if (onAsyncResult != null) {
            this.onAsyncResult = onAsyncResult;
        }
    }
    /**
     * @author Marios Sifalakis
     * @return "VOID"
     *
     * Async task to Simulate Work for Validating Login Credentials
     */

    @Override
    protected Void doInBackground(Void... params) {

        try {
            completedCall = performCheckOnCredentials();
        } catch (Exception e) {
            e.printStackTrace();
            completedCall=false;

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        if (completedCall) {
            onAsyncResult.onResultSuccess(1, act.getResources().getString(R.string.response_success));
        } else {
            onAsyncResult.onResultFail(0, act.getResources().getString(R.string.response_fail));
        }
    }

    public interface OnAsyncResult {
        void onResultSuccess(int resultCode, String message);
        void onResultFail(int resultCode, String errorMessage);
    }

    private boolean performCheckOnCredentials(){
        boolean exists = false;
        for (int i = 0; i<list.size(); i++){
            if(username.equals(list.get(i).getName())){
                if(password.equals(list.get(i).getPassword())){
                    exists = true;
                }
            }
        }

        return exists;
    }

}
