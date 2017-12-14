package br.com.alura.schedule.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.schedule.R;
import br.com.alura.schedule.ui.fragments.AssessmentDetailFragment;
import br.com.alura.schedule.ui.fragments.AssessmentFragment;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_assessment );

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace( R.id.assessment_placeholder, new AssessmentFragment() );

        if( isLandscape() ) {
            transaction.replace( R.id.detail_assessment_placeholder, new AssessmentDetailFragment() );
        }

        transaction.commit();
    }

    private boolean isLandscape() {
        return getResources().getBoolean( R.bool.LANDSCAPE );
    }
}
