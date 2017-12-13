package br.com.alura.schedule.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.alura.schedule.R;
import br.com.alura.schedule.models.Assessment;

public class AssessmentDetailActivity extends AppCompatActivity {
    private TextView mTvDisciplineAssessment;
    private ListView mLvTopicsAssessment;
    private TextView mTvDateAssessment;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assessment_detail );

        Intent intent = getIntent();
        Assessment assessment = (Assessment) intent.getSerializableExtra( "assessment" );

        mTvDisciplineAssessment = (TextView) findViewById( R.id.detail_assessment_discipline );
        mLvTopicsAssessment = (ListView) findViewById( R.id.detail_assessment_topics );
        mTvDateAssessment = (TextView) findViewById( R.id.detail_assessment_date );

        mTvDisciplineAssessment.setText( assessment.getDiscipline() );
        mTvDateAssessment.setText( assessment.getDate() );

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                assessment.getTopics() );

        mLvTopicsAssessment.setAdapter( adapter );
    }
}
