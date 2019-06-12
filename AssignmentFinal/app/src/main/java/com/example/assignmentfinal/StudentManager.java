package com.example.assignmentfinal;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.assignmentfinal.adapter.LissStudent_Adapter;
import com.example.assignmentfinal.data.StudentDBManager;
import com.example.assignmentfinal.model.ClassRoom;
import com.example.assignmentfinal.model.StudentClass;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class StudentManager extends AppCompatActivity {
    Spinner spinnerListRoom;
    ListView lvStudent;
    EditText edtBirtday, edtStudentName;
    Button btnAddStudent;
    StudentDBManager dbManager;
    private String className;
    final Calendar calendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        init();
        Bundle bundle = getIntent().getExtras();
        ArrayList<ClassRoom> listClass = bundle.getParcelableArrayList("list");
        ArrayList<String> listNameClass = new ArrayList<>();
        for (ClassRoom s:listClass){
            listNameClass.add(s.getClassname());
        }
        listNameClass.add("Show All");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,listNameClass);
        spinnerListRoom.setAdapter(adapter);
        spinnerListRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                className = spinnerListRoom.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edtBirtday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDate();
            }
        });
        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addStudent();
                fillToListView();


            }
        });

    }
    private  void addStudent(){
        if(checkEmptyString() && className.equalsIgnoreCase("Show All") ==false) {
            StudentClass student = new StudentClass(edtStudentName.getText().toString(), className, edtBirtday.getText().toString());

            Log.e("StudentManager", edtStudentName.getText().toString() +  "    Class   "+className+  "Birtday   " + edtBirtday.getText().toString() );
            try {
                dbManager.addStudent(student);
                Toast.makeText(this, "Da luu", Toast.LENGTH_SHORT).show();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }




        }else{
            Toast.makeText(this, "Khong duoc bo trong o nhap va Spinner", Toast.LENGTH_SHORT).show();
        }
    }
    private void fillToListView(){
        ArrayList<StudentClass> list;
        list =  dbManager.getAllStudent();
        if (list.size() >0){
          LissStudent_Adapter lissStudent_adapter = new LissStudent_Adapter(this, R.layout.list_item_student, list);
          lvStudent.setAdapter(lissStudent_adapter);
        }
    }
    void init(){
        lvStudent = findViewById(R.id.lv_list_student);
        dbManager = new StudentDBManager(StudentManager.this);
        spinnerListRoom = findViewById(R.id.spn_class_room);
        edtStudentName = findViewById(R.id.edt_student_name);
        edtBirtday = findViewById(R.id.edt_student_birtday);
        btnAddStudent = findViewById(R.id.btn_add_student);
    }
    private boolean checkEmptyString(){
        if(edtBirtday.getText().length()==0 || edtStudentName.getText().length() == 0){
            return false;
        }
        return true;
    }
    private void chooseDate(){
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog date = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                edtBirtday.setText(simpleDateFormat.format(calendar.getTime()));
            }
        }, year, month, day);
        date.show();

    }
}
