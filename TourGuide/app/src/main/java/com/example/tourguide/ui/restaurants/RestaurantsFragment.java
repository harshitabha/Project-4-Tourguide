package com.example.tourguide.ui.restaurants;

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

public class RestaurantsFragment extends Fragment implements View.OnClickListener{

    private RestaurantsViewModel restViewModel;
    private FragmentRestaurantsBinding binding;
    private Button[] mapBtn;
    private TextView[] infoTxt;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        restViewModel =
                new ViewModelProvider(this).get(com.example.tourguide.ui.restaurants.RestaurantsViewModel.class);

        binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        infoTxt = new TextView[4];

        restViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //initializing the buttons and setting it to the onclick listener
                mapBtn = new Button[4];
                String btnID;
                String txtID;
                for(int b = 0; b < mapBtn.length; b++)
                {
                    btnID = "address_rest" + b;
                    int resourceID = getResources().getIdentifier(btnID, "id",
                            getActivity().getPackageName());
                    mapBtn[b] = (Button) getView().findViewById(resourceID);

                    //what to do when the button is pressed
                    mapBtn[b].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String btnID = v.getResources().getResourceEntryName(v.getId());
                            int btnPointer = Integer.parseInt(btnID.substring(btnID.length() - 1)); //get the last character of the id (this will be a number)
                            if(btnPointer==0) openMap("37.582649,", "126.984904", "65 Bukchon-ro, Gahoe-dong, Jongno-gu, Seoul, South Korea");
                            else if(btnPointer==1) openMap("37.577512", "126.988432", "South Korea, Seoul, Jongno-gu, Gahoe-dong, Yulgok-ro, 83 공간사옥 5F");
                            else if(btnPointer==2) openMap("37.715210", "126.495520", "71 Toegye-ro 20-gil, Namsandong 2(i)-ga, Jung-gu, Seoul, South Korea");
                            else openMap("37.570151", "126.999330", "88 Changgyeonggung-ro, Jongno 4(sa)-ga, Jongno-gu, Seoul, South Korea\n");
                        }
                    }); // onclick listener

                    //initializing the text views
                    txtID = "rest_txt" + b;
                    //int tResourceID = ;
                    infoTxt[b] = (TextView) getView().findViewById(getResources().getIdentifier(txtID, "id",
                            getActivity().getPackageName()));
                    if(b==0) infoTxt[b].setText("Tony Yoo, a Michelin-starred chef, has relocated to the center of Bukchon Hanok " +
                            "Village, an area packed with traditional Korean homes.Yoo mixes temple cuisine, a vegetable-centered " +
                            "meal that originated in Korea's Buddhist temples, and traditional Korean dishes in a peaceful Hanok " +
                            "environment to create his own beautiful modern Korean cuisine.");
                    else if(b==1) infoTxt[b].setText("Diners enjoy a constantly changing tasting menu with meals such as seared " +
                            "scallops and Iberico pork steaks while viewing Changdeokgung Castle, a majestic Joseon Dynasty " +
                            "palace. Three sides of the restaurant are constructed of glass, allowing for a panoramic view from " +
                            "everywhere within. This is the place to go if you want a restaurant with a distinctive perspective of Seoul. ");
                    else if(b==2) infoTxt[b].setText("The majority of tourists that visit Seoul intend to ride the cable cars to " +
                            "the summit of Namsan to take in the scenery. A lovely traditional Korean home that doubles as a restaurant " +
                            "is not far from the cable car entrance. Mokmeoksanbang, named after Namsan's previous name, Mokmeoksan, " +
                            "provides one of the greatest varieties of bibimbap in town, a rice bowl topped with meat, eggs, and fresh " +
                            "veggies obtained from farmers all over the nation.");
                    else infoTxt[b].setText("If you want to get lost in Korean street-food heaven, go to Gwangjang Market. " +
                                "Gwangjang is Korea's largest hanbok and textile market, but it is also the country's oldest " +
                                "street-food market, with food stalls offering a diverse range of street-food options. Your " +
                                "hunger will be whet by the scents, sights, and bright energy.");
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