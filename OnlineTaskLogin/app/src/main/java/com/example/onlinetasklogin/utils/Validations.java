package com.example.onlinetasklogin.utils;

import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;

import androidx.lifecycle.MutableLiveData;

import com.example.onlinetasklogin.R;
import com.example.onlinetasklogin.viewmodels.LogInSignUpViewModel;

import java.util.regex.Pattern;

public class Validations {
    private static Validations validations = null;

    private Validations() {

    }

    public static synchronized Validations getValidations() {

        if (validations == null) {
            validations = new Validations();
        }
        return validations;
    }

  /*  private boolean loginCheck(String phone, String password){
        boolean isValid = isValidMobile(phone);
        int passwordValid = passwordValidate(password);
    }*/

    public boolean isValidMobile(String phone, MutableLiveData<Boolean> userPhnValidation) {
        boolean isValid = false;
        if (phone != null) {
            if (!Pattern.matches("[a-zA-Z]+", phone)) {
                isValid = phone.length() > 6 && phone.length() <= 13;
            }
        }
        userPhnValidation.postValue(isValid);
        return isValid;
    }

    public int passwordValidate(String password, MutableLiveData<Integer> userPassValidation) {
        if (password != null) {
            if (!(password.length() >= 8)) {
                userPassValidation.postValue(1);
                return 1;
            } else if (!(password.matches("(.*[0-9].*)"))) {
                userPassValidation.postValue(2);
                return 2;
            } else if (!(password.matches("(.*[A-Z].*)"))) {
                userPassValidation.postValue(3);
                return 3;
            } else if (!(password.matches("^(?=.*[_.()$&@]).*$"))) {
                userPassValidation.postValue(4);
                return 4;
            } else {
                userPassValidation.postValue(-1);
                return -1;
            }
        } else {
            userPassValidation.postValue(0);
            return 0;
        }
    }

    public boolean signUpValidations(String fName, String lName, String password,
                                  String email, String phn, LogInSignUpViewModel viewModel) {
        boolean fn = false;
        boolean ln = false;
        boolean em = false;
        boolean pn =false;
        boolean isValid = false;
        if (fName != null && fName.length()>1){
                fn = true;
                viewModel.userFNameValidations.postValue(true);
        }else {
            viewModel.userFNameValidations.postValue(false);
        }
        if (lName != null && lName.length()>1 ){
                ln = true;
                viewModel.userLNameValidations.postValue(false);
        }else {
            viewModel.userLNameValidations.postValue(true);
        }
        em = isValidEmail(email);
        if (em == true){
            viewModel.userEmailValidations.postValue(false);
        }else {
            viewModel.userEmailValidations.postValue(true);
        }
        pn = isValidMobile(phn, viewModel.userPhnValidations);
        int value = passwordValidate(password, viewModel.userPasswordValidations);

        if (fn == true && ln == true && em == true && pn == true && value == -1){
            isValid = true;
        }

return isValid;
    }

    private boolean isValidEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

}
