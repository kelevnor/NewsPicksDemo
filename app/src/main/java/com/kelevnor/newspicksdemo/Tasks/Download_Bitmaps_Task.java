package com.kelevnor.newspicksdemo.Tasks;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.kelevnor.newspicksdemo.Models.Dummy_Model;
import com.kelevnor.newspicksdemo.R;
import com.kelevnor.newspicksdemo.Utility.ImageLoader;
import com.kelevnor.newspicksdemo.Utility.PublicStaticVariables;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper_Context;

import java.util.ArrayList;

/**
 * Created by kelevnor on 1/3/18.
 */


public class Download_Bitmaps_Task extends AsyncTask<Void, Void, Void> {

    Activity act;
    boolean completedCall = false;
    ImageLoader imageLoader;
    OnAsyncResult onAsyncResult;
    ArrayList<Bitmap> bitmapList;
    /**
     * @author Marios Sifalakis
     */
    public Download_Bitmaps_Task(Activity act){
        this.act = act;
        imageLoader = new ImageLoader(act);
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
     * Async task to Download Bitmaps from Urls
     */

    @Override
    protected Void doInBackground(Void... params) {

        try {
            if(Utility_Helper_Context.hasInternet(act)){
                completedCall = downloadImages();
            }
            else{
                completedCall = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            completedCall=false;

        }
        return null;
    }

    @Override
    protected void onPostExecute(Void v) {
        if (completedCall) {
            onAsyncResult.onResultSuccess(1, bitmapList);
        } else {
            onAsyncResult.onResultFail(0, act.getResources().getString(R.string.response_fail));
        }
    }

    public interface OnAsyncResult {
        void onResultSuccess(int resultCode, ArrayList<Bitmap> bitmapList);
        void onResultFail(int resultCode, String errorMessage);
    }

    //WorkLoad of AsyncTask
    private boolean downloadImages(){
        boolean imagesDownloaded = false;
        bitmapList = new ArrayList<>();
        for(int i = 0; i< PublicStaticVariables.imgUrls.size();i++){
            Bitmap temp = imageLoader.getBitmap(PublicStaticVariables.imgUrls.get(i));
            bitmapList.add(temp);
        }
        if(bitmapList.size()==6){
            imagesDownloaded = true;
        }
        return imagesDownloaded;
    }

}
