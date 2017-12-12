package br.com.alura.schedule;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.alura.schedule.modelo.Student;

public class FormHelper {

    private final EditText mFieldName;
    private final EditText mFieldAddress;
    private final EditText mFieldPhone;
    private final EditText mFieldWebsite;
    private final RatingBar mFieldGrade;
    private final ImageView mFieldPhoto;

    private Student student;

    public FormHelper( FormActivity activity ) {
        mFieldName = (EditText) activity.findViewById( R.id.formulario_nome );
        mFieldAddress = (EditText) activity.findViewById( R.id.formulario_endereco );
        mFieldPhone = (EditText) activity.findViewById( R.id.formulario_telefone );
        mFieldWebsite = (EditText) activity.findViewById( R.id.formulario_site );
        mFieldGrade = (RatingBar) activity.findViewById( R.id.formulario_nota );
        mFieldPhoto = (ImageView) activity.findViewById( R.id.formulario_foto );

        student = new Student();
    }

    public Student getStudent() {
        student.setName( mFieldName.getText().toString() );
        student.setEndereço( mFieldAddress.getText().toString() );
        student.sesetPhone( mFieldPhone.getText().toString() );
        student.setWebsite( mFieldWebsite.getText().toString() );
        student.setGrade( Double.valueOf( mFieldGrade.getProgress() ) );
        student.setPathPhoto( (String) mFieldPhoto.getTag() );

        return student;
    }

    public void fillFormWith( Student student ) {
        mFieldName.setText( student.getName() );
        mFieldAddress.setText( student.getAddress() );
        mFieldPhone.setText( student.getPhone() );
        mFieldWebsite.setText( student.getWebsite() );
        mFieldGrade.setProgress( student.getGrade().intValue() );

        loadImage( student.getPathPhoto() );
        this.student = student;
    }

    public void loadImage( String pathPhoto ) {
        if( pathPhoto != null ) {
            Bitmap bitmap = BitmapFactory.decodeFile( pathPhoto );
            Bitmap reducedBitmap = Bitmap.createScaledBitmap( bitmap, 300, 300, true );

            mFieldPhoto.setImageBitmap( reducedBitmap );
            mFieldPhoto.setScaleType( ImageView.ScaleType.FIT_XY );
            mFieldPhoto.setTag( pathPhoto );
        }
    }
}
