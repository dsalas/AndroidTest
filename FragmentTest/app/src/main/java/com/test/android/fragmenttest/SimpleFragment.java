package com.test.android.fragmenttest;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Android on 16/12/2017.
 */

public class SimpleFragment extends Fragment {
        public final int NO = 0;
        public final int YES = 1;

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            //setObjects();
            final View rootView = inflater.inflate(R.layout.fragment_simple,
                    container, false);
            setObjects(rootView);
            return  rootView;
        }

        private void setObjects(View rootView){
            final RadioGroup radioGroup = (RadioGroup) rootView.findViewById(R.id.radio_group);
            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
                @Override
                public void onCheckedChanged(RadioGroup group, int chechedId) {

                    View radioButton = radioGroup.findViewById(chechedId);
                    int index = radioGroup.indexOfChild(radioButton);
                    TextView textView = getView().findViewById(R.id.fragment_header);
                    switch (index) {
                        case YES:
                            textView.setText("ARTICLE: Like");
                            break;
                        case NO:
                            textView.setText("ARTICLE: Thanks");
                            break;
                        default:
                            break;
                    }
                }
            });
        }
}
