package com.example.onlinetasklogin.views;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.onlinetasklogin.R;
import com.example.onlinetasklogin.databinding.FragmentLoginBinding;
import com.example.onlinetasklogin.viewmodels.LogInSignUpViewModel;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    private LogInSignUpViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,
                container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(LogInSignUpViewModel.class);
        binding.setViewmodel(viewModel);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.callSignUp.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.add(R.id.container, new SignUpFragment());
                fragmentTransaction.addToBackStack("signin");
                fragmentTransaction.commit();
            }
        });

        viewModel.userPhnValidations.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean == false){
                    binding.userNameLayout.setHelperText(getResources()
                            .getString(R.string.valid_mobile));
                }else {
                    binding.userNameLayout.setHelperText("");
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
    }

}