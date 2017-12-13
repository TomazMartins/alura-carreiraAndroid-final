package br.com.alura.schedule.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.alura.schedule.util.converters.StudentConverter;
import br.com.alura.schedule.dao.StudentDAO;
import br.com.alura.schedule.models.Student;


public class SendStudentTask extends AsyncTask<Void, Void, String> {
    private ProgressDialog mDialog;
    private Context mContext;

    public SendStudentTask( Context context ) {
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        mDialog = ProgressDialog.show( mContext, "Aguarde", "Enviando alunos...", true, true );
    }

    @Override
    protected String doInBackground( Void... params ) {
        StudentDAO dao = new StudentDAO( mContext );
        List<Student> students = dao.getAllStudents();
        dao.close();

        StudentConverter studentConverter = new StudentConverter();
        String json = studentConverter.convertToJSON( students );

        WebClient client = new WebClient();
        String response = client.post( json );
        return response;
    }

    @Override
    protected void onPostExecute( String response ) {
        mDialog.dismiss();
        Toast.makeText( mContext, response, Toast.LENGTH_LONG ).show();
    }
}
