package com.kelevnor.newspicksdemo.Adapter;

import android.app.Activity;

import com.kelevnor.newspicksdemo.R;
import com.kelevnor.newspicksdemo.Utility.ImageLoader;
import com.kelevnor.newspicksdemo.Utility.PublicStaticVariables;
import com.sababado.circularview.Marker;
import com.sababado.circularview.SimpleCircularViewAdapter;

/**
 * Created by kelevnor on 1/3/18.
 */

public class Adapter_CircularView extends SimpleCircularViewAdapter {
    int count = 6;
    ImageLoader imageLoader;
    Activity act;
    public Adapter_CircularView(Activity act){
        this.act = act;
        imageLoader = new ImageLoader(act);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setupMarker(final int position, final Marker marker) {
//        marker.setSrc(R.drawable.bg_circle_center_drawable);
        marker.setFitToCircle(false);
        marker.setSrc(imageLoader.getBitmap(PublicStaticVariables.imgUrls.get(position)));
//            marker.setRadius(10 + 2 * position);
    }
}
