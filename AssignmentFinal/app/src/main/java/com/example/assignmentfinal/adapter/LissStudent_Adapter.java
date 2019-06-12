package com.example.assignmentfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assignmentfinal.R;
import com.example.assignmentfinal.model.StudentClass;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class LissStudent_Adapter extends ArrayAdapter {
    private  Context context;
    private  int resource;
    private  ArrayList<StudentClass> listStudent;
    public LissStudent_Adapter(Context context, int resource, ArrayList<StudentClass> objects) {
        super(context, resource, objects);
        this.context  = context;
        this.resource = resource;
        this.listStudent = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder  =  new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_student, parent, false);
            viewHolder.tvSTT = convertView.findViewById(R.id.tv_stt);
            viewHolder.tvName= convertView.findViewById(R.id.tv_name);
            viewHolder.tvDate= convertView.findViewById(R.id.tv_date);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        StudentClass studentClass = listStudent.get(position);
        viewHolder.tvSTT.setText((position + 1) + "");
        viewHolder.tvName.setText(studentClass.getName());
        viewHolder.tvDate.setText(studentClass.getBirthday());
        return convertView;
    }
    private class ViewHolder{

        TextView tvName, tvSTT;
        TextView tvDate;

    }
}
