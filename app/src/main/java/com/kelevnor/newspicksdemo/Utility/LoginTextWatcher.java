package com.kelevnor.newspicksdemo.Utility;


import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.kelevnor.newspicksdemo.LoginActivity;
import com.kelevnor.newspicksdemo.R;

/**
 * Created by kelevnor on 1/2/18.
 */

public class LoginTextWatcher implements TextWatcher{


    Button btn;
    Activity act;
    EditText email;
    EditText password;

    public LoginTextWatcher(Activity act, Button btn, EditText email, EditText password){
        this.act = act;
        this.btn = btn;
        this.email = email;
        this.password = password;
    }

    private void setOrangeDarkBackGround(){
        btn.setBackgroundResource(R.drawable.btn_orange_dark_corners_selector);
        btn.setEnabled(true);

    }
    private void setOrangeLightBackGround(){
        btn.setBackgroundResource(R.drawable.btn_orange_light_corners_selector);
        btn.setEnabled(false);
    }

    public void detectButtonColor(EditText email, EditText password){
        if(email.getText().length()>0&&password.getText().length()>0){
            setOrangeDarkBackGround();

        }
        else{
            setOrangeLightBackGround();

        }


    }



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        detectButtonColor(email, password);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
