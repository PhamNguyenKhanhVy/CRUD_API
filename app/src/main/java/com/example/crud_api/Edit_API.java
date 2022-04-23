package com.example.crud_api;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.crud_api.api.ApiClient;
import com.example.crud_api.api.Api_Interface;
import com.example.crud_api.model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_API extends AppCompatActivity {
    private EditText edt_name, edt_student_code, edt_grade, edt_major,edt_date;
    private CircleImageView mPicture;
    private FloatingActionButton mFabChoosePic;
    Calendar myCalendar = Calendar.getInstance();
    private Bitmap bitmap;
    private String name, species, breed, picture, birth;
    private Api_Interface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_api);
        edt_name = findViewById(R.id.ed_std_name);
        edt_student_code = findViewById(R.id.ed_std_code);
        edt_grade = findViewById(R.id.ed_std_grade);
        edt_major = findViewById(R.id.ed_std_major);
        edt_date = findViewById(R.id.ed_std_date);
        mPicture = findViewById(R.id.picture);
        mFabChoosePic = findViewById(R.id.fabChoosePic);
        edt_date.setFocusableInTouchMode(false);
        edt_date.setFocusable(false);
        edt_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Edit_API.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        mFabChoosePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseFile();
            }
        });
        Button btn = findViewById(R.id.btn_luu);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postData("insert");
                readMode();
            }
        });

    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setBirth();
        }

    };

    private void setBirth() {
        String myFormat = "dd MMMM yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edt_date.setText(sdf.format(myCalendar.getTime()));
    }
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void chooseFile() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Select Picture"));

    }
    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
if(result.getResultCode() == Activity.RESULT_OK){
    Intent data = result.getData();
    if(data == null){
        return;
    }
    else {
        Uri uri = data.getData();
        try {

            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            mPicture.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
                }
            }
            );

    @SuppressLint("RestrictedApi")
    void readMode(){

        edt_name.setFocusableInTouchMode(false);
        edt_student_code.setFocusableInTouchMode(false);
        edt_grade.setFocusableInTouchMode(false);
        edt_major.setFocusable(false);


        edt_date.setEnabled(false);

        mFabChoosePic.setVisibility(View.INVISIBLE);

    }

    private void postData(final String key) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Saving...");
        progressDialog.show();

        readMode();

        String name = edt_name.getText().toString().trim();
        String student_code = edt_student_code.getText().toString().trim();
        String grade = edt_grade.getText().toString().trim();
        String major = edt_major.getText().toString().trim();
        String birth = edt_date.getText().toString().trim();
        String image = null;
        Log.i("bitmap",String.valueOf(bitmap));
        if (bitmap == null) {
            image = "";
        } else {
            image = getStringImage(bitmap);

        }
      System.out.println(image);

        apiInterface = ApiClient.getApiClient().create(Api_Interface.class);

        Call<Student> call = apiInterface.insertStudent( key, name, student_code, grade,major, birth, image);

        call.enqueue(new Callback<Student>() {
            @Override
            public void onResponse(Call<Student> call, Response<Student> response) {

                progressDialog.dismiss();

                Log.i(Edit_API.class.getSimpleName(), response.toString());

                String value = response.body().getValue();
                String message = response.body().getMassage();

                if (value.equals("1")){
                    finish();
                } else {
                    Toast.makeText(Edit_API.this, message, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Student> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(Edit_API.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}