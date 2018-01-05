package com.kelevnor.newspicksdemo;

/**
 * Created by kelevnor on 1/3/18.
 *
 */


import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.github.andreilisun.circular_layout.CircularLayout;
import com.kelevnor.newspicksdemo.Tasks.Download_Bitmaps_Task;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper_Context;

import java.util.ArrayList;

public class AnimationActivity extends AppCompatActivity implements View.OnTouchListener{
    CircularLayout outercl;
    ImageView image1, image2, image3, image4, image5, image6;
    private double mCurrAngle = 0;
    private double mPrevAngle = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        setViews();
        Utility_Helper.populateListWithImageUrls();

        outercl.setOnTouchListener(this);

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


    private void displayBitmapsOnViews(ArrayList<Bitmap> bitmapList){
        image1.setImageBitmap(bitmapList.get(0));
        image2.setImageBitmap(bitmapList.get(1));
        image3.setImageBitmap(bitmapList.get(2));
        image4.setImageBitmap(bitmapList.get(3));
        image5.setImageBitmap(bitmapList.get(4));
        image6.setImageBitmap(bitmapList.get(5));
    }

    private void animateCircleFromAngle(double angle){
        RotateAnimation rotateAnimation = new RotateAnimation((float)angle, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(12000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        outercl.startAnimation(rotateAnimation);
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
                //Adding this method to all imageviews rotates the views depending on angle

                //  postRotate(image1, 360f-mCurrAngle);
                //  postRotate(image2, 360f-mCurrAngle);
                //  postRotate(image3, 360f-mCurrAngle);
                //  postRotate(image4, 360f-mCurrAngle);
                //  postRotate(image5, 360f-mCurrAngle);
                //  postRotate(image6, 360f-mCurrAngle);
                animateTouch(mPrevAngle, mCurrAngle, 0);
                System.out.println(mCurrAngle);
                break;
            }
            case MotionEvent.ACTION_UP : {
                animateCircleFromAngle(mPrevAngle);
                break;
            }
        }
        return true;
    }

    private void postRotate(ImageView view, double angle){

        float width = ((ImageView)view).getDrawable().getBounds().width();
        float height = ((ImageView)view).getDrawable().getBounds().height();
        Matrix matrix=new Matrix();
        view.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) angle, width/2, height/2);
        view.setImageMatrix(matrix);
    }

//Testing methods that have different results
//    private void animateView(ImageView view){
//        AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
//        animation1.setDuration(1000);
//        animation1.setStartOffset(5000);
//        animation1.setFillAfter(true);
//        view.startAnimation(animation1);
//    }

//    private void animateCircle(){
//        RotateAnimation rotateAnimation = new RotateAnimation(0, 360f,
//                Animation.RELATIVE_TO_SELF, 0.5f,
//                Animation.RELATIVE_TO_SELF, 0.5f);
//
//        rotateAnimation.setInterpolator(new LinearInterpolator());
//        rotateAnimation.setDuration(12000);
//        rotateAnimation.setRepeatCount(Animation.INFINITE);
//        outercl.startAnimation(rotateAnimation);
//    }

}
