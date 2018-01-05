package com.kelevnor.newspicksdemo;

/**
 * Created by kelevnor on 1/3/18.
 */


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kelevnor.newspicksdemo.Models.Dummy_Model;
import com.kelevnor.newspicksdemo.Tasks.Simulate_Login_Task;
import com.kelevnor.newspicksdemo.Utility.LoginTextWatcher;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper;
import com.kelevnor.newspicksdemo.Utility.Utility_Helper_Context;

import java.util.ArrayList;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements TextView.OnEditorActionListener, OnClickListener{

    /**
     * Id to identity for permissions request.
     */
    private static final int PERMISSION_REQUEST = 112;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */

    private ArrayList<Dummy_Model> dummyList;

    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Removing Soft Keyboard from opening when creating the activity
        // just before setting the view content, so keyboard will be hidden no matter what
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        setViews();
        // Check for permissions for newer and older Phone Architectures
        if (Build.VERSION.SDK_INT >= 23) {
            Utility_Helper_Context.requestPemissions(LoginActivity.this, PERMISSION_REQUEST);
        }
        // Populate list to check against for valid credentials
        dummyList = Utility_Helper.populateListWithDummyCredentials(getApplicationContext());

        // Instantiate constructor to assign the class implementing TextWatcher
        // on email and password views and keeping their constant state to adjust the login button's
        // background based on that
        LoginTextWatcher textWatcher = new LoginTextWatcher(this, mEmailSignInButton, mEmailView, mPasswordView);
        mPasswordView.addTextChangedListener(textWatcher);
        mEmailView.addTextChangedListener(textWatcher);

        mPasswordView.setOnEditorActionListener(this);
        mEmailSignInButton.setOnClickListener(this);
    }

    private void setViews(){
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST) {
            // Do some action after result return of a permission request
        }
    }

    @Override
    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
        switch (id){
            case R.id.password:
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.email_sign_in_button:
                attemptLogin();
                break;
        }
    }

    /**
     * From Simulate_Login_Task, Result listener interface to get results
     * back from asyncronous task Simulate_Login_Task
     */

    Simulate_Login_Task.OnAsyncResult asynResult = new Simulate_Login_Task.OnAsyncResult() {
        @Override
        public void onResultSuccess(int resultCode, String result) {
            Log.d("SUCCESS", result);
            Intent i = new Intent(LoginActivity.this, AnimationActivity.class);
            startActivity(i);
        }
        @Override
        public void onResultFail(int resultCode, String errorResult) {
            Log.d("FAIL", errorResult);
            Utility_Helper_Context.showSortPopup(LoginActivity.this, getResources().getString(R.string.failedtoauth));
        }
    };



    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);


        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !Utility_Helper.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!Utility_Helper.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            // if user flow not cancelled execute Simulate_Login_Task asyncronous task
            // to simulate login
            Simulate_Login_Task task = new Simulate_Login_Task(this, dummyList, email, password);
            task.setOnResultListener(asynResult);
            task.execute();
        }
    }

}

