package br.com.alura.schedule.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.schedule.R;
import br.com.alura.schedule.ui.fragments.AssessmentFragment;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assessment );

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace( R.id.assessment_placeholder, new AssessmentFragment() )
                .commit();
    }
}
