package com.example.todolist.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todolist.R;
import com.example.todolist.TodoListClass;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<TodoListClass> listClasses;
    private Context mContext;
    private ClickListener clickListener;

    public RecyclerViewAdapter(){

    }

    public RecyclerViewAdapter(Context context,List<TodoListClass> listClasses){

        this.listClasses=listClasses;
        this.mContext=context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        TodoListClass list = listClasses.get(position);

        holder._id.setText(String.valueOf(list.getUid()));
        holder._title.setText(list.getTitle());
        holder._desc.setText(list.getDescription());

    }
    public void setClickListener(ClickListener clickListener){
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return listClasses.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView _title;
        private TextView _desc;
        private TextView _id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            _title = itemView.findViewById(R.id.title_text);
            _desc = itemView.findViewById(R.id.desc_text);
            _id= itemView.findViewById(R.id.id_text);
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();

            if(clickListener != null){

                clickListener.itemClicked(v,getPosition());
            }
        }


    }

    public interface ClickListener{

        public void itemClicked(View view, int position);
    }
}
