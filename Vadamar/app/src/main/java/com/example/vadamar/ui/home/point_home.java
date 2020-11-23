package com.example.vadamar.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vadamar.Model.ListItem;
import com.example.vadamar.R;

import java.util.List;


public class point_home extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;




    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(R.string.ppd_tamil);
        return inflater.inflate(R.layout.fragment_point_home, container, false);




    }


}