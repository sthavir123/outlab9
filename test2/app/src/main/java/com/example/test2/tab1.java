package com.example.test2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tab1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tab1 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    DatabaseHelper myDb;
    ArrayList<String> TYPE, DATE ,TIME ,DESCRIPTION,id;
    ArrayList<Integer> tab;
    CustomAdaptor customAdaptor;
    Integer tabid = 1;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tab1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tab1.
     */
    // TODO: Rename and change types and number of parameters
    public static tab1 newInstance(String param1, String param2) {
        tab1 fragment = new tab1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_tab1, container, false);
        recyclerView = view.findViewById(R.id.recyler_view);
        add_button = view.findViewById(R.id.add_1);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),addActivity.class);
                intent.putExtra("tab",1);
                startActivity(intent);
            }
        });

        myDb = new DatabaseHelper(App.getContext());
        TYPE = new ArrayList<>();
        TIME = new ArrayList<>();
        DATE = new ArrayList<>();
        DESCRIPTION = new ArrayList<>();
        id = new ArrayList<>();
        tab = new ArrayList<>();
        storeDataInArrays();
        customAdaptor = new CustomAdaptor(getActivity(), App.getContext(),TYPE,TIME,DATE,DESCRIPTION,id,tab);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(App.getContext()));
        return view;
    }

    //@Override
    //public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    //    super.onActivityResult(requestCode, resultCode, data);
    //}

    public void storeDataInArrays(){
        Cursor cursor = myDb.readAllData();
        if(cursor.getCount() == 0){
            //
        }
        else{
            while(cursor.moveToNext()){
                if(cursor.getInt(5) == tabid) {
                    id.add(cursor.getString(0));
                    TYPE.add(cursor.getString(1));
                    DATE.add(cursor.getString(2));
                    TIME.add(cursor.getString(3));
                    DESCRIPTION.add(cursor.getString(4));
                    tab.add(cursor.getInt(5));
                }

            }
        }
    }
}