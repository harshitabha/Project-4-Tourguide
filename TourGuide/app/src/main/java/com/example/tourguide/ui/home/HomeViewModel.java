package com.example.tourguide.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tourguide.R;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Seoul is a city filled with vibrancy, consisting of both traditional Korean culture and " +
                "modern culture, making it a popular destination among tourists. Seoul is riding a creative wave. " +
                "The city has become a creative sanctuary for people wanting to make their mark on everything from fashion " +
                "to food to architecture to art.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}