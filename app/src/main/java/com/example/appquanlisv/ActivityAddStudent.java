package com.example.appquanlisv;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appquanlisv.database.database;
import com.example.appquanlisv.model.Student;

public class ActivityAddStudent extends AppCompatActivity {

    Button buttonAddStudent;
    EditText editTextStudentName, editTextStudentCode,editTextDateofbirth;
    RadioButton radiobuttonMale, radiobuttonFemale;
    database database;
    private int id_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        buttonAddStudent = findViewById(R.id.buttonAddStudent);
        editTextDateofbirth = findViewById(R.id.EditTextStudentBirthday);
        editTextStudentCode = findViewById(R.id.EditTextStudentCode);
        editTextStudentName = findViewById(R.id.EditTextStudentName);

        radiobuttonFemale = findViewById(R.id.radiobuttonFemale);
        radiobuttonMale = findViewById(R.id.radiobuttonMale);


        //lay id subject
        Intent intent = getIntent();
        int ib_subject = intent.getIntExtra("id_subject",0);

        database = new database(this);
        buttonAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(ib_subject);
            }
        });
    }

    //dialog add
    private void DialogAdd(int ib_subject) {

        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.buttonNoAddStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextStudentName.getText().toString().trim();
                String code = editTextStudentCode.getText().toString().trim();
                String birthday = editTextDateofbirth.getText().toString().trim();
                String sex = "";

                //kiem tra radiobutton true tai dau thi gia tri nam hay nu gan vao sex
                if(radiobuttonMale.isChecked()){
                    sex ="Male";
                }
                else if(radiobuttonFemale.isChecked()){
                    sex ="Female";
                }
                if (name.equals("") || code.equals("") || birthday.equals("") || sex.equals("")){
                    Toast.makeText(ActivityAddStudent.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    Student student = CreateStudent(id_subject);

                    // LOI KHUC NAY
                    database.AddStudent(student);

                    Intent intent = new Intent(ActivityAddStudent.this,ActivityStudent.class);
                    intent.putExtra("id_subject",id_subject);
                    startActivity(intent);

                    Toast.makeText(ActivityAddStudent.this,"more succes",Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();

    }

    private Student CreateStudent(int id_subject){
        String name = editTextStudentName.getText().toString().trim();
        String code = editTextStudentCode.getText().toString().trim();
        String birthday = editTextDateofbirth.getText().toString().trim();
        String sex = "";

        //kiem tra radiobutton true tai dau thi gia tri nam hay nu gan vao sex
        if(radiobuttonMale.isChecked()){
            sex = "Male";
        } else {
            sex = "Female";
        }

        Student student = new Student(name,sex,code,birthday,id_subject);
        return student;
    }
}