package com.example.myhighereducationofcomplexbam002.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myhighereducationofcomplexbam002.Model.Faculty;
import com.example.myhighereducationofcomplexbam002.R;

import java.util.List;

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {

    private Context context;
    private List<Faculty> facultyList;

    public FacultyAdapter(Context context, List<Faculty> facultyList){

        this.context = context;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item_faculty,parent,false);

        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacultyViewHolder facultyViewHolder, int position) {

        facultyViewHolder.name.setText(facultyList.get(position).getFaculty_name());
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }



    public class FacultyViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_list_name);

        }
    }

}
