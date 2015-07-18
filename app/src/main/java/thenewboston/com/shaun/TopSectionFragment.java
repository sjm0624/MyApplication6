package thenewboston.com.shaun;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;


public class TopSectionFragment extends Fragment {

    private static EditText etSearch, etDistance;
    private static ToggleButton tbCC, tbOpen;

    //creating interface activityCommand is the name of the interface object
    TopSectionListener activityCommander;


    public interface TopSectionListener {
        //implement this activity ensures this method is implemented pass the arguments to the method as well, so it can pass stuff from the text fields
        public void searchParams(String top, String bottom, ToggleButton first, ToggleButton second);
    }


    @Override  //call this method when fragment attaches to main activity
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            //this says the interface i want called once attached fragment is attached to main activity
            //actually creates the interface
            activityCommander = (TopSectionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString());
        }
    }
        //tells the app what layout to fragment to use
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //store it in view.
        //inflater.inflate is a method to
        // say the layout we r using
        //container is a view group object we are passing
        View view = inflater.inflate(R.layout.top_section_fragment, container, false);

        //variables attached to widgets
        //always put in on create view so as soon as fragment starts these start
        etSearch = (EditText) view.findViewById(R.id.etSearch);
        etDistance = (EditText)view.findViewById(R.id.etDistance);
        tbCC = (ToggleButton) view.findViewById(R.id.tbCC);
        tbOpen = (ToggleButton) view.findViewById(R.id.tbOpen);

        //reference to button
        final Button btnSearch = (Button)view.findViewById(R.id.btnSearch);

        //button listener
        btnSearch.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //23 this method is created below, the method below tells what neesd to b done
                        buttonClicked(v);
                    }
                }
        );

        //if  toggle button is on, it means yes and if its off it means no
        tbCC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // tbCC toggled
                if(isChecked){
                    buttonView.setText("Yes");
                }else{
                    buttonView.setText("No");
                }
            }
        });
        //if  toggle button is on, it means yes and if its off it means no
        tbOpen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // tbOpen toggled
                if(isChecked){
                    buttonView.setText("Yes");
                }else{
                    buttonView.setText("No");
                }
            }
        });
        return view;
    }

        //how does it know which button it knows this method because of name buttonClicked(v)
        //calls this when the button is clicked
        //reference 23 or line 63
    //
    //
    //
    public void buttonClicked(View view){
        //tells it the search parameters that need to be passed along to the yelp API
         activityCommander.searchParams(etSearch.getText().toString(), etDistance.getText().toString(), tbCC.getTextOff().toString(), tbOpen.getTextOff().toString());
    }
}
