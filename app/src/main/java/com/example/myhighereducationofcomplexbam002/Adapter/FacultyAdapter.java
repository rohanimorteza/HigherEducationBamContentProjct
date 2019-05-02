package com.example.myhighereducationofcomplexbam002.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.myhighereducationofcomplexbam002.Model.Faculty;
import com.example.myhighereducationofcomplexbam002.R;

import java.util.List;

public class FacultyAdapter {

    private Context context;
    private List<Faculty> facultyList;

    public FacultyAdapter(Context context, List<Faculty> facultyList){

        this.context = context;
        this.facultyList = facultyList;
    }

    public class FacultyViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_list_name);

        }
    }

}
