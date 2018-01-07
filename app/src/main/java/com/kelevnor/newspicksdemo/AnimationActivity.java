package com.kelevnor.newspicksdemo;

/**
 * Created by kelevnor on 1/3/18.
 *
 */

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.github.andreilisun.circular_layout.CircularLayout;
import com.kelevnor.newspicksdemo.Tasks.Download_Bitmaps_Task;
import com.kelevnor.newspicksdemo.Utility.Inherit_BitmapAlphaAnimationListener;
import com.kelevnor.newspicksdemo.Utility.Inherit_ImageViewRotateAnimationListener;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper_Context;

import java.util.ArrayList;

public class AnimationActivity extends AppCompatActivity implements View.OnTouchListener{

    CircularLayout outercl;
    ImageView image1, image2, image3, image4, image5, image6;

    Inherit_ImageViewRotateAnimationListener rotateListener;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setViews();
        Utility_Helper.populateListWithImageUrls();

        outercl.setOnTouchListener(this);

        rotateListener = new Inherit_ImageViewRotateAnimationListener(this, outercl);
        rotateListener.setRotateAnimationAndListener();

        Download_Bitmaps_Task task = new Download_Bitmaps_Task(this);
        task.setOnResultListener(asynResult);
        task.execute();
    }

    private void setViews(){
        outercl = findViewById(R.id.cl_outer);
        image1 = findViewById(R.id.iv_one);
        image2 = findViewById(R.id.iv_two);
        image3 = findViewById(R.id.iv_three);
        image4 = findViewById(R.id.iv_four);
        image5 = findViewById(R.id.iv_five);
        image6 = findViewById(R.id.iv_six);
    }

    private void displayBitmapsOnViews(ArrayList<Bitmap> bitmapList) {
        Inherit_BitmapAlphaAnimationListener bmListener = new Inherit_BitmapAlphaAnimationListener(this);
        bmListener.setAlphaAnimationAndListener(image1, bitmapList.get(0), 1);
        bmListener.setAlphaAnimationAndListener(image2, bitmapList.get(1), 2);
        bmListener.setAlphaAnimationAndListener(image3, bitmapList.get(2), 3);
        bmListener.setAlphaAnimationAndListener(image4, bitmapList.get(3), 4);
        bmListener.setAlphaAnimationAndListener(image5, bitmapList.get(4), 5);
        bmListener.setAlphaAnimationAndListener(image6, bitmapList.get(5), 6);
    }

    private void animateTouch(double fromDegrees, double toDegrees, long durationMillis) {
        final RotateAnimation rotate = new RotateAnimation((float) fromDegrees, (float) toDegrees,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(durationMillis);
        rotate.setFillEnabled(true);
        rotate.setFillAfter(true);
        outercl.startAnimation(rotate);
        System.out.println(mCurrAngle);
    }

    /**
     * From Download_Bitmaps_Task, Result listener interface to get results
     * back from asyncronous task Download_Bitmaps_Task
     */

    Download_Bitmaps_Task.OnAsyncResult asynResult = new Download_Bitmaps_Task.OnAsyncResult() {
        @Override
        public void onResultSuccess(int resultCode, ArrayList<Bitmap> bitmapList) {
            Log.d("SUCCESS", "SUCCESS");
            displayBitmapsOnViews(bitmapList);
        }
        @Override
        public void onResultFail(int resultCode, String errorResult) {
            Log.d("FAIL", errorResult);
            Utility_Helper_Context.showSortPopup(AnimationActivity.this, getResources().getString(R.string.failedtodownload));
        }
    };

    @Override
    public boolean onTouch(final View v, MotionEvent event) {
        final float xc = outercl.getWidth() / 2;
        final float yc = outercl.getHeight() / 2;
        final float x = event.getX();
        final float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                mPrevAngle = mCurrAngle;
                mCurrAngle = Math.toDegrees(Math.atan2(x - xc, yc - y));
                animateTouch(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP : {
                rotateListener.setRotateAnimationAndListenerFromAngle(mPrevAngle);
                break;
            }
        }
        return true;
    }

}
