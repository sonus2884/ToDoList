package com.example.todolist.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.Activity.AddTaskActivity;

import com.example.todolist.Activity.ModifyActivity;
import com.example.todolist.Adapter.RecyclerViewAdapter;
import com.example.todolist.Data.DataBaseHelper;
import com.example.todolist.Data.MyDBManager;
import com.example.todolist.R;
import com.example.todolist.TodoListClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class TodoListFragment extends Fragment implements RecyclerViewAdapter.ClickListener {

    private View view;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerViewAdapter mAdapter;

    //Data Base..
    private MyDBManager dbManager;

    private List<TodoListClass> todoList;

    public TodoListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.todo_list_fragment, container, false);

        //Recycler View...
        mRecyclerView = view.findViewById(R.id.recyclerViewTasks);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new RecyclerViewAdapter();
//        mRecyclerView.setAdapter(mAdapter);

        todoList = new ArrayList<>();
        //Floating Button..
        FloatingActionButton fabButton = view.findViewById(R.id.fab);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new intent to start an AddTaskActivity
                Intent addTaskIntent = new Intent(getContext(), AddTaskActivity.class);
                startActivity(addTaskIntent);
            }
        });

        Init();
        return view;
    }

    private void Init() {
        dbManager = new MyDBManager(getActivity());
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        while (cursor.moveToNext()) {

            int cid = cursor.getInt(cursor.getColumnIndex(DataBaseHelper.UID));
            String title = cursor.getString(cursor.getColumnIndex(DataBaseHelper.SUBJECT));
            String desc = cursor.getString(cursor.getColumnIndex(DataBaseHelper.DESC));

            TodoListClass list = new TodoListClass(title,desc,cid);
            todoList.add(list);
        }

//        for (TodoListClass l : todoList){
//            Log.d("_data",l.getUid()+" "+l.getTitle()+" "+l.getDescription());
//        }

        mAdapter = new RecyclerViewAdapter(getContext(), todoList);
        mAdapter.setClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
   }


    @Override
    public void itemClicked(View view, int position) {

//        Toast.makeText(getContext(), "Clicked #: "+position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getContext(), ModifyActivity.class);
//        String id = String.valueOf(view.getId());

        TextView titleTextView =  view.findViewById(R.id.title_text);
        TextView descTextView =  view.findViewById(R.id.desc_text);
        TextView idText = view.findViewById(R.id.id_text);

        String title = titleTextView.getText().toString();
        String desc = descTextView.getText().toString();
        String id= idText.getText().toString();
//        Log.d("_passValue",id+" "+title+" "+desc);

        intent.putExtra("title", title);
        intent.putExtra("desc", desc);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}
