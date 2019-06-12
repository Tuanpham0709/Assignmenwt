package com.example.assignmentfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.assignmentfinal.adapter.ListClass_Adaper;
import com.example.assignmentfinal.model.ClassRoom;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListClass extends AppCompatActivity {
    ListView lvClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_class);
        lvClass = findViewById(R.id.lv_list_class);
        Bundle bundle = getIntent().getExtras();
        final ArrayList<ClassRoom> listClass;

            listClass =bundle.getParcelableArrayList("list");

            final ListClass_Adaper listClassAdaper = new ListClass_Adaper(ListClass.this, R.layout.list_item_class, listClass);
            lvClass.setAdapter(listClassAdaper);
            lvClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    listClass.remove(position);
                    listClassAdaper.notifyDataSetChanged();
                    Toast.makeText(ListClass.this, "Da xoa", Toast.LENGTH_SHORT).show();

                    return false;
                }
            });







    }
}
