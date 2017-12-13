package br.com.alura.schedule.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.schedule.R;
import br.com.alura.schedule.models.Assessment;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assessment );

        List<String> portugueseTopics = Arrays.asList( "Sujeito", "Predicado", "Objeto" );
        Assessment portugueseAssessment = new Assessment( portugueseTopics, "Português", "12/12/12" );

        List<String> mathTopics = Arrays.asList( "Equação", "Operações" );
        Assessment mathAssessment = new Assessment( mathTopics, "Matemática", "13/12/12" );

        List<String> englishTopics = Arrays.asList( "To Be", "To Do" );
        Assessment englishAssessment = new Assessment( englishTopics, "Inglês", "14/12/12" );

        List<String> geographTopics = Arrays.asList( "Relevo", "Mapas" );
        Assessment geographAssessment = new Assessment( geographTopics, "Geografia", "15/12/12" );

        List<Assessment> assessmentList = Arrays.asList( portugueseAssessment, mathAssessment, englishAssessment, geographAssessment );
        ArrayAdapter<Assessment> assessmentAdapter = new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, assessmentList );

        ListView listView = (ListView) findViewById( R.id.list_assessment );
        listView.setAdapter( assessmentAdapter );

        listView.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> parent, View view, int position, long l ) {
                Assessment currentAssessment = (Assessment) parent.getItemAtPosition( position );
                Toast.makeText( AssessmentActivity.this, "On: " + currentAssessment.toString(), Toast.LENGTH_SHORT ).show();
            }
        } );
    }
}
