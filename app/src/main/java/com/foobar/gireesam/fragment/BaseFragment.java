package com.foobar.gireesam.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.foobar.gireesam.activity.BaseActivity;

public abstract class BaseFragment<DB extends ViewDataBinding, VM extends ViewModel> extends Fragment {

    private Bundle bundle;
    private DB binding;
    private VM viewModel;

    protected abstract int fragmentId();

    public DB getBinding() {
        return binding;
    }

    public VM getViewModel() {
        return viewModel;
    }

    public void setViewModel(VM viewModel) {
        this.viewModel = viewModel;
    }

    protected abstract Class<VM> viewModelClass();

    protected abstract Factory factory();

    public Bundle getBundle() {
        return bundle;
    }

    public void setBundle(Bundle bundle) {
        this.bundle = bundle;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup parent, Bundle savedInstanseState) {
        binding = DataBindingUtil.inflate(inflater, fragmentId(), parent, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        binding.setVariable(BR.viewModel, viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        viewModel = ViewModelProviders.of(this, factory()).get(viewModelClass());
    }

    public void replaceFragment(Class fragmentClass, String extraTag, Bundle bundle) {
        if (getActivity() != null) {
            ((BaseActivity) getActivity()).replaceFragment(fragmentClass, extraTag, bundle);
        }
    }

    public abstract static class Factory extends ViewModelProvider.NewInstanceFactory {
        public abstract ViewModel getClassInstance();

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) getClassInstance();
        }
    }

}
