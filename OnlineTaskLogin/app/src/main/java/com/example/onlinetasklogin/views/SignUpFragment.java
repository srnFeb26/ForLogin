package com.example.onlinetasklogin.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinetasklogin.R;
import com.example.onlinetasklogin.databinding.FragmentSignUpBinding;
import com.example.onlinetasklogin.viewmodels.LogInSignUpViewModel;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private LogInSignUpViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up,
                container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(LogInSignUpViewModel.class);
        binding.setViewmodel(viewModel);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        viewModel.userPhnValidations.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == false){
                    binding.userPhnLayout.setHelperText(getResources()
                            .getString(R.string.valid_mobile));
                }else {
                    binding.userPhnLayout.setHelperText("");
                }

            }
        });
        viewModel.userPasswordValidations.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer == -1){
                    binding.passwordEditLayout.setHelperText("");
                }else if (integer == 1){
                    binding.passwordEditLayout.setHelperText(getResources()
                            .getString(R.string.pass_length));
                }else if (integer == 2){
                    binding.passwordEditLayout.setHelperText(getResources()
                            .getString(R.string.pass_num));
                }else if (integer == 3){
                    binding.passwordEditLayout.setHelperText(getResources()
                            .getString(R.string.pass_upper));
                }else if (integer == 4){
                    binding.passwordEditLayout.setHelperText(getResources()
                            .getString(R.string.pass_symbol));
                }else {
                    binding.passwordEditLayout.setHelperText(getResources()
                            .getString(R.string.pass_error));
                }
            }
        });

        viewModel.userEmailValidations.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    binding.emailLayout.setHelperText("Enter valid mail");
                }else {
                    binding.emailLayout.setHelperText("");
                }
            }
        });

        viewModel.userFNameValidations.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    binding.emailLayout.setHelperText("Enter Valid First Name");
                }else {
                    binding.emailLayout.setHelperText("");
                }
            }
        });

        viewModel.userLNameValidations.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    binding.emailLayout.setHelperText("Enter Valid Last Name");
                }else {
                    binding.emailLayout.setHelperText("");
                }
            }
        });
    }

    public void callHomePage(){
        ((LoginActivity)getActivity()).callHomePage();
    }
}