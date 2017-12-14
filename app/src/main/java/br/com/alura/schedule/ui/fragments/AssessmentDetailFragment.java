package br.com.alura.schedule.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.alura.schedule.R;


public class AssessmentDetailFragment extends Fragment {
    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view =  inflater.inflate(
                R.layout.fragment_assessment_detail,
                container,
                false );

        return view;
    }

}
