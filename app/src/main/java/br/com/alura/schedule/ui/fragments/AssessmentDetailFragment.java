package br.com.alura.schedule.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.alura.schedule.R;
import br.com.alura.schedule.models.Assessment;


public class AssessmentDetailFragment extends Fragment {
    private TextView mFieldDiscipline;
    private TextView mFieldDate;
    private ListView mFieldTopics;

    private Bundle mArgs;

    @Override
    public View onCreateView( LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState ) {
        View view =  inflater.inflate( R.layout.fragment_assessment_detail, container,false );

        mFieldDiscipline = (TextView) view.findViewById( R.id.detail_assessment_discipline );
        mFieldDate = (TextView) view.findViewById( R.id.detail_assessment_date );
        mFieldTopics = (ListView) view.findViewById( R.id.detail_assessment_topics );

        mArgs = getArguments();

        if( mArgs != null ) {
            Assessment detailedAssessment = (Assessment) mArgs.getSerializable( "assessment" );
            fillFieldsWith( detailedAssessment );
        }

        return view;
    }

    public void fillFieldsWith( Assessment assessment ) {
        mFieldDiscipline.setText( assessment.getDiscipline() );
        mFieldDate.setText( assessment.getDate() );

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getContext(),
                android.R.layout.simple_list_item_1,
                assessment.getTopics() );

        mFieldTopics.setAdapter( adapter );
    }
}
