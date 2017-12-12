package br.com.alura.schedule;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import br.com.alura.schedule.dao.StudentDAO;
import br.com.alura.schedule.models.Student;

public class FormActivity extends AppCompatActivity {
    public static final int CAMERA_CODE = 567;

    private FormHelper mFormHelper;
    private String mPathPhoto;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario );

        mFormHelper = new FormHelper( this );

        Intent intent = getIntent();
        Student student = (Student) intent.getSerializableExtra( "student" );
        if( student != null ) {
            mFormHelper.fillFormWith( student );
        }

        Button buttonTakePhoto = (Button) findViewById( R.id.formulario_botao_foto );
        buttonTakePhoto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                mPathPhoto = getExternalFilesDir( null ) + "/" + System.currentTimeMillis() + ".jpg";

                File filePhoto = new File( mPathPhoto );
                Intent intentCamera = new Intent( MediaStore.ACTION_IMAGE_CAPTURE );
                intentCamera.putExtra( MediaStore.EXTRA_OUTPUT, Uri.fromFile( filePhoto ) );

                startActivityForResult( intentCamera, CAMERA_CODE );
            }
        } );
    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        if( resultCode == Activity.RESULT_OK ) {
            if( requestCode == CAMERA_CODE ) {
                mFormHelper.loadImage( mPathPhoto );
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_form, menu );

        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch( item.getItemId() ) {
            case R.id.menu_form_ok:
                Student student = mFormHelper.getStudent();

                StudentDAO dao = new StudentDAO( this );

                if( student.getId() != null ) {
                    dao.update( student );
                } else {
                    dao.insert( student );
                }
                dao.close();

                Toast.makeText( FormActivity.this, "Aluno " + student.getName() + " salvo!", Toast.LENGTH_SHORT ).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected( item );
    }
}
