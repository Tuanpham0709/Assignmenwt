package com.example.assignmentfinal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.assignmentfinal.R;
import com.example.assignmentfinal.model.ClassRoom;

import java.util.ArrayList;

public class ListClass_Adaper extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<ClassRoom> listClass;

    public ListClass_Adaper(Context context, int resource, ArrayList<ClassRoom> objects)throws  NullPointerException {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listClass = objects;
    }
    @Override
    public View getView(int position,  View convertView,ViewGroup parent) {
        ViewHolder viewHolder ;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_class,parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvStt = convertView.findViewById(R.id.tv_stt);
            viewHolder.classCode = convertView.findViewById(R.id.tv_class_code);
            viewHolder.className = convertView.findViewById(R.id.class_name);
            convertView.setTag(viewHolder);

        }else {
            viewHolder =(ViewHolder) convertView.getTag();
        }
        ClassRoom classRoom = listClass.get(position);
        viewHolder.tvStt.setText((position+1) + "");
        viewHolder.classCode.setText(classRoom.getClassCode());
        viewHolder.className.setText(classRoom.getClassname());
        return convertView;
    }

    public class ViewHolder{
        TextView tvStt, classCode, className;
    }
}
