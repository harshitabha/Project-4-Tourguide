package com.example.tourguide.ui.hotels;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.tourguide.R;
import com.example.tourguide.databinding.FragmentAttractionsBinding;
import com.example.tourguide.databinding.FragmentHotelsBinding;

public class HotelsFragment extends Fragment implements View.OnClickListener{

    private HotelsViewModel hotelsViewModel;
    private FragmentHotelsBinding binding;
    private Button[] mapBtn;
    private TextView[] infoTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        hotelsViewModel =
                new ViewModelProvider(this).get(com.example.tourguide.ui.hotels.HotelsViewModel.class);

        binding = FragmentHotelsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        infoTxt = new TextView[4];

        hotelsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //initializing the buttons and setting it to the onclick listener
                mapBtn = new Button[4];
                String btnID;
                String txtID;
                for(int b = 0; b < mapBtn.length; b++)
                {
                    btnID = "address_hotel" + b;
                    int resourceID = getResources().getIdentifier(btnID, "id",
                            getActivity().getPackageName());
                    mapBtn[b] = (Button) getView().findViewById(resourceID);

                    //what to do when the button is pressed
                    mapBtn[b].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String btnID = v.getResources().getResourceEntryName(v.getId());
                            int btnPointer = Integer.parseInt(btnID.substring(btnID.length() - 1)); //get the last character of the id (this will be a number)
                            if(btnPointer==0) openMap("37.5577", "127.0076", "249, Dongho-ro, Jung-gu, 04605 Seoul, Korea");
                            else if(btnPointer==1) openMap("37.5039", "127.0047", "JW Marriott Hotel Seoul, 176 Sinbanpo-ro, Banpo-dong, Seocho-gu, Seoul, South Korea");
                            else if(btnPointer==2) openMap("37.508814", "127.060878", "521 Teheran-ro, Samseong 1(il)-dong, Gangnam-gu, Seoul, South Korea");
                            else openMap("37.5653", "126.9810", "30 Eulji-ro, Euljiro 1(il)-ga, Jung-gu, Seoul, South Korea");
                        }
                    }); // onclick listener

                    //initializing the text views
                    txtID = "hotel_txt" + b;
                    //int tResourceID = ;
                    infoTxt[b] = (TextView) getView().findViewById(getResources().getIdentifier(txtID, "id",
                            getActivity().getPackageName()));
                    if(b==0) infoTxt[b].setText("In one of Asia\'s most dynamic and economically powerful cities, " +
                            "the hotel effectively blends comfortable elegance with cutting-edge technology. " +
                            "The famous Peter Remedios designed the interiors which are a perfect combination of " +
                            "East and West, while a large number of world-class masterpieces light the hotel with " +
                            "top-notch elegance.");
                    else if(b==1) infoTxt[b].setText("JW Marriott Hotel Seoul, a renowned location in the Gangnam District, offers 5-star elegance amid some of" +
                            " South Korea\'s most recognized attractions. Explore South Korea\\'s renowned Gangnam District, which is home " +
                            "to some of the best shopping, eating, and cultural activities. In a vibrant event venue enhanced by contemporary " +
                            "technology, personalized food, and professional planners, you can host faultless meetings, weddings, and social gatherings.");
                    else if(b==2) infoTxt[b].setText("Grand InterContinental Seoul Parnas, located in the heart of Gangnam\'s renowned neighborhood, provides " +
                            "an exceptional experience for both leisure and business guests. The hotel is next to the COEX Convention and has " +
                            "easy access to one of Korea's best retail, leisure, and business complexes.");
                    else infoTxt[b].setText("Lotte Hotel Seoul is one of Korea\\'s finest 5-star luxury hotels, ideally located near Myeongdong " +
                            "in the heart of Seoul\\'s major business area. Four world-class interior design firms collaborated " +
                            "to create 1,015 guest rooms in the Main Tower and Executive Tower.");
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override//to not have an error
    public void onClick(View v) {

    }

    private void openMap(String coord1, String coord2, String address)
    {
        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri =
        Uri.parse("geo:" + coord1 +"," + coord2 + "?q=" + Uri.encode(address));

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }
}