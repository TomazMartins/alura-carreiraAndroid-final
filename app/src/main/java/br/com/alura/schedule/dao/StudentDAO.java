package br.com.alura.schedule.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.schedule.models.Student;

public class StudentDAO extends SQLiteOpenHelper {
    public StudentDAO( Context context ) {
        super( context, "Agenda", null, 2 );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        String sql = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, " +
                "endereco TEXT, " +
                "telefone TEXT, " +
                "site TEXT, " +
                "nota REAL, " +
                "caminhoFoto TEXT);";
        db.execSQL( sql );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        String sql = "";
        switch( oldVersion ) {
            case 1:
                sql = "ALTER TABLE Alunos ADD COLUMN caminhoFoto TEXT";
                db.execSQL( sql ); // indo para versao 2
        }

    }

    public void insert( Student student ) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = getDataOf( student );

        db.insert( "Alunos", null, dados );
    }

    @NonNull
    private ContentValues getDataOf( Student student ) {
        ContentValues data = new ContentValues();
        data.put( "nome", student.getName() );
        data.put( "endereco", student.getAddress() );
        data.put( "telefone", student.getPhone() );
        data.put( "site", student.getWebsite() );
        data.put( "nota", student.getGrade() );
        data.put( "caminhoFoto", student.getPathPhoto() );
        return data;
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery( sql, null );

        List<Student> students = new ArrayList<Student>();
        while( cursor.moveToNext() ) {
            Student student = new Student();
            student.setId( cursor.getLong( cursor.getColumnIndex( "id" ) ) );
            student.setName( cursor.getString( cursor.getColumnIndex( "nome" ) ) );
            student.setEndereço( cursor.getString( cursor.getColumnIndex( "endereco" ) ) );
            student.sesetPhone( cursor.getString( cursor.getColumnIndex( "telefone" ) ) );
            student.setWebsite( cursor.getString( cursor.getColumnIndex( "site" ) ) );
            student.setGrade( cursor.getDouble( cursor.getColumnIndex( "nota" ) ) );
            student.setPathPhoto( cursor.getString( cursor.getColumnIndex( "caminhoFoto" ) ) );

            students.add( student );
        }
        cursor.close();

        return students;
    }

    public void remove( Student student ) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {student.getId().toString()};
        db.delete( "Alunos", "id = ?", params );
    }

    public void update( Student student ) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues data = getDataOf( student );

        String[] params = {student.getId().toString()};
        db.update( "Alunos", data, "id = ?", params );
    }

    public boolean isStudent( String telefone ) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery( "SELECT * FROM Alunos WHERE telefone = ?", new String[]{telefone} );
        int result = c.getCount();
        c.close();
        return result > 0;
    }
}
