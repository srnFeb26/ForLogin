package com.example.onlinetasklogin.viewmodels;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.onlinetasklogin.R;
import com.example.onlinetasklogin.utils.Validations;
import com.example.onlinetasklogin.views.LoginActivity;
import com.example.onlinetasklogin.views.LoginFragment;

public class LogInSignUpViewModel extends AndroidViewModel {
    public ObservableField<String> userPhn = new ObservableField<>();
    public ObservableField<String> userPassword = new ObservableField<>();
    public MutableLiveData<Boolean> userPhnValidations;
    public MutableLiveData<Boolean> userFNameValidations,
            userLNameValidations,userEmailValidations;
    public MutableLiveData<Integer> userPasswordValidations;
    public MutableLiveData<Boolean> callSignUp;
    public MutableLiveData<Boolean> callSignIn;
    public ObservableField<String> suFName = new ObservableField<>();
    public ObservableField<String> suLName = new ObservableField<>();
    public ObservableField<String> suPass = new ObservableField<>();
    public ObservableField<String> suPhone = new ObservableField<>();
    public ObservableField<String> suEmail = new ObservableField<>();



    public LogInSignUpViewModel(@NonNull Application application) {
        super(application);
        callSignIn = new MutableLiveData<>();
        callSignUp = new MutableLiveData<>();
        userPhnValidations = new MutableLiveData<>();
        userPasswordValidations = new MutableLiveData<>();
        userFNameValidations = new MutableLiveData<>();
        userLNameValidations = new MutableLiveData<>();
        userEmailValidations = new MutableLiveData<>();
    }

    public void callLogin(View view){
        String userName = userPhn.get();
        String password = userPassword.get();
        boolean isValid = Validations.getValidations().isValidMobile(userName, userPhnValidations);
        int passwordValid = Validations.getValidations().passwordValidate(password, userPasswordValidations);
        if (isValid == true && passwordValid == -1){
            Toast.makeText(view.getContext(),"Login Success",Toast.LENGTH_LONG).show();
            ((LoginActivity)view.getContext()).callHomePage();
        }
    }

    public void signUpCalling(View view){
        boolean isValid = Validations.getValidations().signUpValidations(suFName.get(),suLName.get(),
                suPass.get(), suEmail.get(),suPhone.get(),this);
        if (isValid == true){
            Toast.makeText(view.getContext(),"SignUp Success",Toast.LENGTH_LONG).show();
            ((LoginActivity)view.getContext()).callHomePage();
        }
    }

    public void callSignUpScreen(){
      callSignUp.postValue(true);
    }
}
