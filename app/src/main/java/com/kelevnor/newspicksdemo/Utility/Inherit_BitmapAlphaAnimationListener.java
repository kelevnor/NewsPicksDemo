package com.kelevnor.newspicksdemo.Utility;


import android.app.Activity;
import android.graphics.Bitmap;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

/**
 * Created by kelevnor on 1/3/18.
 */

public class Inherit_BitmapAlphaAnimationListener implements Animation.AnimationListener{

    int INTERVAL_TENTH_OF_SECOND = 100;
    Activity act;
    ImageView imageview;

    public Inherit_BitmapAlphaAnimationListener(Activity act){
        this.act = act;
    }

    public void setAlphaAnimationAndListener(ImageView imageview, Bitmap bm, int image_position){
        imageview.setImageBitmap(bm);

        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(INTERVAL_TENTH_OF_SECOND);
        animation.setStartOffset(image_position*INTERVAL_TENTH_OF_SECOND);
        animation.setFillAfter(true);
        imageview.startAnimation(animation);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        animation.cancel();
        imageview.setAnimation(null);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
