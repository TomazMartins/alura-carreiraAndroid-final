package br.com.alura.schedule.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.alura.schedule.modelo.Student;

public class StudentConverter {
    public String convertToJSON( List<Student> students ) {
        JSONStringer jsonStringer = new JSONStringer();

        try {
            jsonStringer.object().key( "list" ).array().object().key( "aluno" ).array();
            for( Student student : students ) {
                jsonStringer.object();
                jsonStringer.key( "nome" ).value( student.getName() );
                jsonStringer.key( "nota" ).value( student.getGrade() );
                jsonStringer.endObject();
            }
            jsonStringer.endArray().endObject().endArray().endObject();
        } catch( JSONException e ) {
            e.printStackTrace();
        }

        return jsonStringer.toString();
    }
}
