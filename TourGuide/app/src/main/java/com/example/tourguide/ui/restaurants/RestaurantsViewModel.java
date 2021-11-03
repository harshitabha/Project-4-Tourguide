package com.example.tourguide.ui.restaurants;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RestaurantsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public RestaurantsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is restaurant fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}