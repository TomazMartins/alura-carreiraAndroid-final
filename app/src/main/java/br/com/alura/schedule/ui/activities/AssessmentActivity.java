package br.com.alura.schedule.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.alura.schedule.R;
import br.com.alura.schedule.models.Assessment;
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

    public void showAssessment( Assessment currentAssessment ) {
        if( !isLandscape() ) {
            AssessmentDetailFragment detailFragment = new AssessmentDetailFragment();
            Bundle params = new Bundle();

            params.putSerializable( "assessment", currentAssessment );
            detailFragment.setArguments( params );

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace( R.id.assessment_placeholder, detailFragment )
                    .commit();
        } else  {
            AssessmentDetailFragment fragment =
                    (AssessmentDetailFragment) getSupportFragmentManager()
                            .findFragmentById( R.id.detail_assessment_placeholder );

            fragment.fillFieldsWith( currentAssessment );
        }
    }

    private boolean isLandscape() {
        return getResources().getBoolean( R.bool.LANDSCAPE );
    }
}
