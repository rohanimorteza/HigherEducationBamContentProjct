package com.example.myhighereducationofcomplexbam002.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhighereducationofcomplexbam002.Adapter.FacultyAdapter;
import com.example.myhighereducationofcomplexbam002.Model.Faculty;
import com.example.myhighereducationofcomplexbam002.R;
import com.example.myhighereducationofcomplexbam002.dbHandler;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    RecyclerView rv;
    List<Faculty> items;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rv = (RecyclerView) inflater.inflate(R.layout.each_fragment_list_content,container,false);

        items = new ArrayList<>();
        setupRecycler(rv);

        return rv;
    }

    public void setupRecycler(RecyclerView recyclerView){

        dbHandler dbh = new dbHandler(getContext());
        dbh.open();

        int facultCount=dbh.ProfessorCount();

        for(int i=0;i<facultCount;i++){
            int m= i+1;
            if(getArguments().get("FRG").equals(dbh.get_Cat_Prof(""+m))){
                items.add(dbh.display2(m+""));     }
        }

        /*
        if(getArguments().get("FRG").equals("COMP")){

            items.add(dbh.display2("1"));
        }
        if(getArguments().get("FRG").equals("MATH")){
            items.add(dbh.display2("2"));
        }
        */


        dbh.close();

        FacultyAdapter facultyAdapter = new FacultyAdapter(getContext(),items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setAdapter(facultyAdapter);

    }
}
