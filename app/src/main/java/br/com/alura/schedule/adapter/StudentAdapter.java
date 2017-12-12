package br.com.alura.schedule.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.schedule.R;
import br.com.alura.schedule.modelo.Student;


public class StudentAdapter extends BaseAdapter {
    private final List<Student> students;
    private final Context context;

    public StudentAdapter( Context context, List<Student> students ) {
        this.context = context;
        this.students = students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem( int position ) {
        return students.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return students.get( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        Student student = students.get( position );
        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from( context );

        if( view == null ) {
            view = inflater.inflate( R.layout.list_item, parent, false );
        }

        TextView fieldName = (TextView) view.findViewById( R.id.item_name );
        fieldName.setText( student.getName() );

        TextView fieldPhone = (TextView) view.findViewById( R.id.item_phone );
        fieldPhone.setText( student.getPhone() );

        ImageView fieldPhoto = (ImageView) view.findViewById( R.id.item_photo );
        String pathPhoto = student.getPathPhoto();

        if( pathPhoto != null ) {
            Bitmap bitmap = BitmapFactory.decodeFile( pathPhoto );
            Bitmap reducedBitmap = Bitmap.createScaledBitmap( bitmap, 100, 100, true );
            fieldPhoto.setImageBitmap( reducedBitmap );
            fieldPhoto.setScaleType( ImageView.ScaleType.FIT_XY );
        }

        return view;
    }
}
