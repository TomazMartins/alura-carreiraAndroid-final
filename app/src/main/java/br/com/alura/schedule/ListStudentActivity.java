package br.com.alura.schedule;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.alura.schedule.adapter.StudentAdapter;
import br.com.alura.schedule.dao.StudentDAO;
import br.com.alura.schedule.models.Student;

public class ListStudentActivity extends AppCompatActivity {
    private ListView mStudentList;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_alunos );

        mStudentList = (ListView) findViewById( R.id.lista_alunos );

        mStudentList.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick( AdapterView<?> list, View item, int position, long id ) {
                Student student = (Student) mStudentList.getItemAtPosition( position );

                Intent toFormIntent = new Intent( ListStudentActivity.this, FormActivity.class );
                toFormIntent.putExtra( "student", student );
                startActivity( toFormIntent );
            }
        } );

        Button buttonNewStudent = (Button) findViewById( R.id.novo_aluno );
        buttonNewStudent.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Intent toFormIntent = new Intent( ListStudentActivity.this, FormActivity.class );
                startActivity( toFormIntent );
            }
        } );

        registerForContextMenu( mStudentList );
    }

    private void loadList() {
        StudentDAO dao = new StudentDAO( this );
        List<Student> students = dao.getAllStudents();
        dao.close();

        StudentAdapter adapter = new StudentAdapter( this, students );
        mStudentList.setAdapter( adapter );
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadList();
    }

    @Override
    public boolean onCreateOptionsMenu( Menu menu ) {
        getMenuInflater().inflate( R.menu.menu_lista_alunos, menu );

        return true;
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item ) {
        switch( item.getItemId() ) {
            case R.id.menu_send_grades:
                new SendStudentTask( this ).execute();
                break;

            case R.id.menu_request_assessment:
                Intent toAssessmentActivity = new Intent( this, AssessmentActivity.class );
                startActivity( toAssessmentActivity );
                break;
        }
        return super.onOptionsItemSelected( item );
    }

    @Override
    public void onCreateContextMenu( ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo ) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Student student = (Student) mStudentList.getItemAtPosition( info.position );

        MenuItem itemCall = menu.add( "Ligar" );
        itemCall.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
                if( ActivityCompat.checkSelfPermission( ListStudentActivity.this, Manifest.permission.CALL_PHONE )
                        != PackageManager.PERMISSION_GRANTED ) {
                    ActivityCompat.requestPermissions( ListStudentActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 123 );
                } else {
                    Intent callIntent = new Intent( Intent.ACTION_CALL );
                    callIntent.setData( Uri.parse( "tel:" + student.getPhone() ) );
                    startActivity( callIntent );
                }
                return false;
            }
        } );

        MenuItem itemSMS = menu.add( "Enviar SMS" );
        Intent SMSItent = new Intent( Intent.ACTION_VIEW );
        SMSItent.setData( Uri.parse( "sms:" + student.getPhone() ) );
        itemSMS.setIntent( SMSItent );

        MenuItem itemAddress = menu.add( "Visualizar no mapa" );
        Intent mapItent = new Intent( Intent.ACTION_VIEW );
        mapItent.setData( Uri.parse( "geo:0,0?q=" + student.getAddress() ) );
        itemAddress.setIntent( mapItent );

        MenuItem itemWebsite = menu.add( "Visitar site" );
        Intent toWebsiteIntent = new Intent( Intent.ACTION_VIEW );

        String site = student.getWebsite();
        if( !site.startsWith( "http://" ) ) {
            site = "http://" + site;
        }

        toWebsiteIntent.setData( Uri.parse( site ) );
        itemWebsite.setIntent( toWebsiteIntent );

        MenuItem itemRemove = menu.add( "Deletar" );
        itemRemove.setOnMenuItemClickListener( new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick( MenuItem item ) {
                StudentDAO dao = new StudentDAO( ListStudentActivity.this );
                dao.remove( student );
                dao.close();

                loadList();
                return false;
            }
        } );
    }

}
