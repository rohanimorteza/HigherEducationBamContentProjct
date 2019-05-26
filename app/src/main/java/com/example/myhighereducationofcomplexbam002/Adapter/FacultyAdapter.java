package com.example.myhighereducationofcomplexbam002.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myhighereducationofcomplexbam002.DetailActivity;
import com.example.myhighereducationofcomplexbam002.Model.Faculty;
import com.example.myhighereducationofcomplexbam002.ProfessorsActivity;
import com.example.myhighereducationofcomplexbam002.R;

import java.util.List;

import static com.example.myhighereducationofcomplexbam002.MainActivity.from;

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
    public void onBindViewHolder(@NonNull FacultyViewHolder facultyViewHolder, final int position) {

        facultyViewHolder.name.setText(facultyList.get(position).getFaculty_name());

        byte[] p = facultyList.get(position).getAx();
        Bitmap bm = BitmapFactory.decodeByteArray(p,0,p.length);
        facultyViewHolder.ax.setImageBitmap(bm);

        facultyViewHolder.crd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(context,""+from,Toast.LENGTH_LONG).show();

                if(from.equals("1")){
                    Intent i = new Intent(context, ProfessorsActivity.class);
                    i.putExtra("IDFACULTY",facultyList.get(position).getId());
                    context.startActivity(i);

                }else {
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("ID",facultyList.get(position).getId());
                    context.startActivity(i);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }



    public class FacultyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView ax;
        CardView crd;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_list_name);
            ax = itemView.findViewById(R.id.img_list_ax);
            crd = itemView.findViewById(R.id.crd);

        }
    }

}
