package com.kelevnor.newspicksdemo.Utility;


import android.app.Activity;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.github.andreilisun.circular_layout.CircularLayout;

/**
 * Created by kelevnor on 1/6/18.
 */

public class Inherit_ImageViewRotateAnimationListener implements Animation.AnimationListener{
    RotateAnimation rotateAnimation;
    int INTERVAL_TWELVE_SECONDS = 12000;

    Activity act;
    CircularLayout cl;

    public Inherit_ImageViewRotateAnimationListener(Activity act, CircularLayout cl){
        this.act = act;
        this.cl = cl;
    }

    public void setRotateAnimationAndListener(){
        rotateAnimation = new RotateAnimation(0, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(INTERVAL_TWELVE_SECONDS);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);
        cl.startAnimation(rotateAnimation);
    }
    public void setRotateAnimationAndListenerFromAngle(double angle){
        rotateAnimation = new RotateAnimation((float)angle, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);

        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(INTERVAL_TWELVE_SECONDS);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setFillAfter(true);
        cl.startAnimation(rotateAnimation);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

//        rotateAnimation.cancel();

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private void postRotate(ImageView view, double angle){
        float width = ((ImageView)view).getDrawable().getBounds().width();
        float height = ((ImageView)view).getDrawable().getBounds().height();
        Matrix matrix=new Matrix();
        view.setScaleType(ImageView.ScaleType.MATRIX);   //required
        matrix.postRotate((float) angle, width/2, height/2);
        view.setImageMatrix(matrix);
    }
}
