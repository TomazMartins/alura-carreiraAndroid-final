package br.com.alura.schedule.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.schedule.R;
import br.com.alura.schedule.models.Assessment;

public class AssessmentFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState ) {
        View view = inflater.inflate( R.layout.fragment_assessment, container, false );

        List<String> portugueseTopics = Arrays.asList( "Sujeito", "Predicado", "Objeto" );
        Assessment portugueseAssessment = new Assessment( portugueseTopics, "Português", "12/12/12" );

        List<String> mathTopics = Arrays.asList( "Equação", "Operações" );
        Assessment mathAssessment = new Assessment( mathTopics, "Matemática", "13/12/12" );

        List<String> englishTopics = Arrays.asList( "To Be", "To Do" );
        Assessment englishAssessment = new Assessment( englishTopics, "Inglês", "14/12/12" );

        List<String> geographTopics = Arrays.asList( "Relevo", "Mapas" );
        Assessment geographAssessment = new Assessment( geographTopics, "Geografia", "15/12/12" );

        List<Assessment> assessmentList = Arrays.asList( portugueseAssessment, mathAssessment, englishAssessment, geographAssessment );
        ArrayAdapter<Assessment> assessmentAdapter = new ArrayAdapter<>( getContext(), android.R.layout.simple_list_item_1, assessmentList );

        ListView listView = (ListView) view.findViewById( R.id.list_assessment );
        listView.setAdapter( assessmentAdapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long l ) {
                Assessment currentAssessment = (Assessment) parent.getItemAtPosition( position );
                Toast.makeText( getContext(), "On: " + currentAssessment.toString(), Toast.LENGTH_SHORT ).show();

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace( R.id.assessment_placeholder, new AssessmentDetailFragment() )
                    .commit();
            }
        } );

        return view;
    }
}
