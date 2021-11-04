package com.example.tourguide.ui.attractions;

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
import com.example.tourguide.databinding.FragmentRestaurantsBinding;
import com.example.tourguide.ui.hotels.HotelsViewModel;
import com.example.tourguide.ui.restaurants.RestaurantsViewModel;

public class AttractionsFragment extends Fragment implements View.OnClickListener{

    private AttractionsViewModel attractViewModel;
    private FragmentAttractionsBinding binding;
    private Button[] mapBtn;
    private TextView[] infoTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        attractViewModel =
                new ViewModelProvider(this).get(com.example.tourguide.ui.attractions.AttractionsViewModel.class);

        binding = FragmentAttractionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        infoTxt = new TextView[4];

        attractViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //initializing the buttons and setting it to the onclick listener
                mapBtn = new Button[4];
                String btnID;
                String txtID;
                for(int b = 0; b < mapBtn.length; b++)
                {
                    btnID = "address_attract" + b;
                    int resourceID = getResources().getIdentifier(btnID, "id",
                            getActivity().getPackageName());
                    mapBtn[b] = (Button) getView().findViewById(resourceID);

                    //what to do when the button is pressed
                    mapBtn[b].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String btnID = v.getResources().getResourceEntryName(v.getId());
                            int btnPointer = Integer.parseInt(btnID.substring(btnID.length() - 1)); //get the last character of the id (this will be a number)
                            if(btnPointer==0) openMap("37.5512", "126.9882", "105 Namsangongwon-gil, Yongsan 2(i)ga-dong, Yongsan-gu, Seoul, South Korea");
                            else if(btnPointer==1) openMap("37.5142", "127.0603", "Bongeunsa-ro, Gangnam-gu, Seoul, South Korea");
                            else if(btnPointer==2) openMap("37.511234", "127.098030", "240 Olympic-ro, Jamsil-dong, Songpa-gu, Seoul, South Korea");
                            else openMap("37.565978", "127.011745", "281 Eulji-ro, Euljiro 7(chil)-ga, Jung-gu, Seoul");
                        }
                    }); // onclick listener

                    //initializing the text views
                    txtID = "attract_txt" + b;
                    //int tResourceID = ;
                    infoTxt[b] = (TextView) getView().findViewById(getResources().getIdentifier(txtID, "id",
                            getActivity().getPackageName()));
                    if(b==0) infoTxt[b].setText("This communications and observation tower, which rises over 500 meters above the city, offers spectacular views of " +
                            "the city from its position on the slope of Mount Namsan. A cable car transports you up the mountainside to " +
                            "the foot of the tower. From here, you may climb the tower and visit any of the four observation decks, " +
                            "one of which is a rotating restaurant.");
                    else if(b==1) infoTxt[b].setText("Bongeunsa is a Buddhist temple in and near Seoul. The temple is located on the " +
                            "slope of a low mountain, just across from the huge COEX conference complex and mall. It's a favorite " +
                            "location for convention-goers to take a break and relax in solitude.");
                    else if(b==2) infoTxt[b].setText("The Lotte World Tower skyscraper is one of Seoul's newest attractions. It stands" +
                            " 500 meters above the earth and is one of the world's highest structures. Offices, luxury homes, " +
                            "and a hotel are housed within the skyscraper. In addition, there is an aquarium and a big retail " +
                            "center. The tower has a music hall as well as a 21-screen MoviePlex.");
                    else infoTxt[b].setText("Dongdaemun is particularly well-known for its shopping, with " +
                                "several department stores and discount shops. There are showrooms, work areas, offices, " +
                                "and design studios throughout the property. The design center comes alive at night, with the " +
                                "highlight being 25,550 white, LED flowers that light up.");
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