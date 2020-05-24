package com.example.todolist.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todolist.Data.MyDBManager;
import com.example.todolist.Fragment.TodoListFragment;
import com.example.todolist.R;

public class ModifyActivity extends AppCompatActivity {

    private Button updateTodoBtn;
    private EditText updateTitleEditText;
    private EditText updateDescEditText;
    private Long uid;

    private MyDBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Update Task");
        setSupportActionBar(toolbar);

        updateTodoBtn = findViewById(R.id.updateButton);
        updateDescEditText = findViewById(R.id.updateDescriptionText);
        updateTitleEditText = findViewById(R.id.updateTitleText);

        dbManager = new MyDBManager(this);
        dbManager.open();

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String name = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");

        updateTitleEditText.setText(name);
        updateDescEditText.setText(desc);
        uid = Long.parseLong(id);
        Log.d("_recValue",id+" "+name+" "+desc);
    }

    public void onClickUpdateTask(View view){
        String title = updateTitleEditText.getText().toString();
        String desc = updateDescEditText.getText().toString();
        dbManager.update(uid, title, desc);

        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void onClickDeleteTask(View view) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you wanted to Delete?");
                alertDialogBuilder.setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
//                                Toast.makeText(ModifyActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                                dbManager.delete(uid);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

}
