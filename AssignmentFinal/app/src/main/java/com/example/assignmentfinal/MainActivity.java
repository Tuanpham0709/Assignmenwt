 package com.example.assignmentfinal;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.assignmentfinal.data.StudentDBManager;
import com.example.assignmentfinal.model.ClassRoom;
import com.example.assignmentfinal.model.StudentClass;

import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {
     ArrayList<ClassRoom> classArrayList;
     ArrayList<StudentClass> ListStudent;
     StudentDBManager studentDBManager;
     private Button btnAddClass, btnShowClass, btnStudentManager, btnRemoveInfoClass, btnSaveInfoClass;
    private EditText edtClassCode;
    private EditText edtClassName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        onClickButton();

    }
    private void dialogAddClass(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.dialog_add_class);
        edtClassCode = dialog.findViewById(R.id.edt_code_class);
        edtClassName = dialog.findViewById(R.id.edt_name_class);
        btnRemoveInfoClass = dialog.findViewById(R.id.btn_remove);
        btnSaveInfoClass = dialog.findViewById(R.id.btn_save);
        dialog.show();
        removeInfoClass(); saveInfoClass();

    }
    private void removeInfoClass(){
        btnRemoveInfoClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtClassCode.setText("");
                edtClassName.setText("");
            }
        });
    }
    private void saveInfoClass(){


        btnSaveInfoClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkEmptyString()){
                   ClassRoom classRoom = new ClassRoom(edtClassCode.getText().toString(), edtClassName.getText().toString());
                    studentDBManager.addClass(classRoom);
                   Toast.makeText(MainActivity.this, "Luu thanh cong", Toast.LENGTH_SHORT).show();
               }else {
                   Toast.makeText(MainActivity.this, "Khong duoc bo trong cac o nhap", Toast.LENGTH_SHORT).show();
               }

            }
        });

    }
    private boolean checkEmptyString(){
        if(edtClassCode.getText().toString().equalsIgnoreCase("") ||
                edtClassName.getText().toString().equalsIgnoreCase("")){
            return false;
        }
        return true;
    }
    void init(){
        studentDBManager = new StudentDBManager(MainActivity.this);
        ListStudent = new ArrayList<>();
        classArrayList = new ArrayList<>();
        btnAddClass = findViewById(R.id.btn_addClass);
        btnShowClass = findViewById(R.id.btn_showlistClass);
        btnStudentManager = findViewById(R.id.btn_student_manager);
    }
    private void onClickButton(){
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogAddClass();

            }
        });
         btnShowClass.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, ListClass.class);
                 classArrayList.clear();
                 classArrayList = studentDBManager.getAllClass();
                 Bundle bundle = new Bundle();
                 bundle.putParcelableArrayList("list", classArrayList);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });
         btnStudentManager.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, StudentManager.class);
                 classArrayList.clear();
                 classArrayList = studentDBManager.getAllClass();
                 Bundle bundle = new Bundle();
                 bundle.putParcelableArrayList("list", classArrayList);
                 intent.putExtras(bundle);
                 startActivity(intent);
             }
         });
    }
}
