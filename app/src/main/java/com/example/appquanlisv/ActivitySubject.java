package com.example.appquanlisv;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.appquanlisv.adapter.adaptersubject;
import com.example.appquanlisv.database.database;
import com.example.appquanlisv.model.Subject;

import java.util.ArrayList;

public class ActivitySubject extends AppCompatActivity {

    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    com.example.appquanlisv.database.database database;
    com.example.appquanlisv.adapter.adaptersubject adaptersubject;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id, title,credit,time,place));
        }

        adaptersubject = new adaptersubject(ActivitySubject.this,ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);
        cursor.moveToFirst();
        cursor.close();

        //tao su kien click vao iten subjet
        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ActivitySubject.this,ActivityStudent.class);
                int id_subject = ArrayListSubject.get(i).getId();
                //truyen du lieu id subject qua activity student
                intent.putExtra("id_subject",id_subject);
                startActivity(intent);

            }
        });


    }

    //them mot menu la add vao toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            //neu click vao add thi chuyen man hinh add subject
            case R.id.menuadd:
                Intent intent1 = new Intent(ActivitySubject.this,ActivityAddSubject.class);
                startActivity(intent1);
                break;

                //neu click vao nut con lai la nut black thi quay lai main
            default:
                Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //neu click back o dien thoai se tro ve main activity
    @Override
    public void onBackPressed() {
        count++;
        if (count>=1){
            Intent intent = new Intent(ActivitySubject.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos){

        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == pos){
                Intent intent = new Intent(ActivitySubject.this,ActivitySubjectInformation.class);

                intent.putExtra("id",id);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);

            }
        }
    }

    //phuong thuc xoa subject
    public void delete(final int position){

        //doi tuong cua so
        Dialog dialog = new Dialog(this);

        //nạp layout vào dialog
        dialog.setContentView(R.layout.dialogdeletesubject);

        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteSubject);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //database = new database(ActivitySubject.this);
                //xoa subject trong csdl
                database.DeleteSubject(position);

                //xoa student
                database.DeleteSubjectStudent(position);

                //cap nhat lai activity subject
                Intent intent = new Intent(ActivitySubject.this,ActivitySubject.class);
                startActivity(intent);
            }
        });

        //dong dialog neu no
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //show dialog
        dialog.show();
    }

    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if(id == pos){
                Intent intent = new Intent(ActivitySubject.this,ActivityUpdateSubject.class);


                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                //gui du lieu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);


            }
        }
    }

}