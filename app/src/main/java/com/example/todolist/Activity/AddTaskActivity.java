package com.example.todolist.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.todolist.Data.MyDBManager;
import com.example.todolist.R;

public class AddTaskActivity extends AppCompatActivity {

    private Button addTodoBtn;
    private EditText titleEditText;
    private EditText descEditText;

    private MyDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Add Task");
        setSupportActionBar(toolbar);

        addTodoBtn = findViewById(R.id.addButton);
        titleEditText = findViewById(R.id.titleText);
        descEditText = findViewById(R.id.descriptionText);
        dbManager = new MyDBManager(this);
        dbManager.open();
    }

    public void onClickAddTask(View view) {

        final String name = titleEditText.getText().toString();
        final String desc = descEditText.getText().toString();

        if (name.length() == 0 || desc.length()==0)  {
            return;
        }

        dbManager.insert(name, desc);

        Intent intent = new Intent(AddTaskActivity.this, MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
    }
}
