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
import com.example.tourguide.databinding.FragmentRestaurantsBinding;

public class restaurantsFragment extends Fragment implements View.OnClickListener{

    private RestaurantsViewModel restaurantsViewModel;
    private FragmentRestaurantsBinding binding;
    private Button[] mapBtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        restaurantsViewModel =
                new ViewModelProvider(this).get(com.example.tourguide.ui.restaurants.RestaurantsViewModel.class);

        binding = FragmentRestaurantsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.hotelTxt1;




        restaurantsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //initializing the buttons and setting it to the onclick listener
                mapBtn = new Button[4];
                String btnID;
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
                            if(btnPointer==0)
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("geo:37.582700, 126.985098")); // coords for the 1st restaurant

                                startActivity(i);
                            }
                            else if(btnPointer==1)
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("geo:37.551866, 126.988572")); // coords for the 2nd restaurant

                                startActivity(i);
                            }
                            else if(btnPointer==2)
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("geo:37.551866, 126.988082")); // coords for the 3rd restaurant

                                startActivity(i);
                            }
                            else
                            {
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse("geo:37.570151, 126.999330")); // coords for the 4th restaurant

                                startActivity(i);
                            }
                        }
                    }); // onclick listener
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
}