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
    private final List<Student> mStudentList;
    private final Context mContext;

    public StudentAdapter( Context context, List<Student> students ) {
        this.mContext = context;
        this.mStudentList = students;
    }

    @Override
    public int getCount() {
        return mStudentList.size();
    }

    @Override
    public Object getItem( int position ) {
        return mStudentList.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return mStudentList.get( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        Student student = mStudentList.get( position );
        View view = convertView;

        LayoutInflater inflater = LayoutInflater.from( mContext );

        if( view == null ) {
            view = inflater.inflate( R.layout.list_item, parent, false );
        }

        TextView fieldName = (TextView) view.findViewById( R.id.item_name );
        fieldName.setText( student.getName() );

        TextView fieldPhone = (TextView) view.findViewById( R.id.item_phone );
        fieldPhone.setText( student.getPhone() );

        TextView fieldAddress = (TextView) view.findViewById( R.id.item_address );

        if( fieldAddress != null ) {
            fieldAddress.setText( student.getAddress() );
        }

        TextView fieldWebsite = (TextView) view.findViewById( R.id.item_website );

        if( fieldWebsite != null ) {
            fieldWebsite.setText( student.getWebsite() );
        }

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
