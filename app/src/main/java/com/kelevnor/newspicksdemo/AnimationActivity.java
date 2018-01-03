package com.kelevnor.newspicksdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.kelevnor.newspicksdemo.Adapter.Adapter_CircularView;
import com.kelevnor.newspicksdemo.Utility.PublicStaticVariables;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper;
import com.sababado.circularview.CircularView;
import com.sababado.circularview.Marker;
import com.sababado.circularview.SimpleCircularViewAdapter;

public class AnimationActivity extends AppCompatActivity {
    CircularView circularView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        setViews();
        calibrateCircularView();
        Utility_Helper.populateListWithImageUrls();

        Adapter_CircularView mAdapter = new Adapter_CircularView(this);
        circularView.setAdapter(mAdapter);

    }

    private void calibrateCircularView(){

        // Allow markers to continuously animate on their own when the highlight animation isn't running.
        // The flag can also be set in XML
        circularView.setAnimateMarkerOnStillHighlight(true);
        // Combine the above line with the following so that the marker at it's position will animate at the start.
        // The highlighted Degree can also be defined in XML
        circularView.setHighlightedDegree(CircularView.BOTTOM);
    }

    private void setViews(){
        circularView = (CircularView) findViewById(R.id.circular_view);
    }



}
