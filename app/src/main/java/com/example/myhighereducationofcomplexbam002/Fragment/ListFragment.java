package com.example.myhighereducationofcomplexbam002.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myhighereducationofcomplexbam002.R;

public class ListFragment extends Fragment {

    RecyclerView rv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rv = (RecyclerView) inflater.inflate(R.layout.each_fragment_list_content,container,false);

        setupRecycler();

        return rv;
    }

    public void setupRecycler(){

    }
}
