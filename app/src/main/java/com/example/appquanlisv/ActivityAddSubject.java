package com.example.appquanlisv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appquanlisv.database.database;
import com.example.appquanlisv.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {

    Button buttonAddSubject;
    EditText editSubjectTitle,editSubjectCredit, editSubjectTime, editSubjectPlace;
    com.example.appquanlisv.database.database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        editSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        editSubjectPlace = findViewById(R.id.EditTextSubjectPlace);
        editSubjectTime = findViewById(R.id.EditTextSubjectTime);
        editSubjectTitle = findViewById(R.id.EditTextSubjectTitle);

        database = new database(this);

        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd() {

        //tao doi tuong cua so
        Dialog dialog = new Dialog(this);

        //nap layout vao dialog
        dialog.setContentView(R.layout.dialogadd);

        //tat lick ngoai la thoat
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = editSubjectTitle.getText().toString().trim();
                String credit = editSubjectCredit.getText().toString().trim();
                String time = editSubjectTime.getText().toString().trim();
                String place = editSubjectPlace.getText().toString().trim();

                //neu du lieu chua nhap day du
                if(subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")){
                    Toast.makeText(ActivityAddSubject.this,"Did not enter enough information",Toast.LENGTH_SHORT).show();
                }
                else {
                    //gan cho doi tuong subject gia tri duoc nhap vao
                    Subject subject = CreaSubject();

                    //add trong database
                    database.AddSubjects(subject);

                    //add thanh cong thi chuyen qua gia dien subject
                    Intent intent = new Intent(ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this,"more succes",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //neu khong add nua
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        //show dialog
        dialog.show();
    }
    //create subject
    private Subject CreaSubject(){
        String subjecttitle = editSubjectTitle.getText().toString().trim();
        int credit = Integer.parseInt(editSubjectCredit.getText().toString().trim());
        String time = editSubjectTime.getText().toString().trim();
        String place = editSubjectPlace.getText().toString().trim();

        Subject subject = new Subject(subjecttitle,credit,time,place);
        return subject;
    }
}