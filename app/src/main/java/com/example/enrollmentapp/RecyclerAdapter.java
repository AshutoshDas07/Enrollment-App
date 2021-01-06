package com.example.enrollmentapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<UserDetails> listData;

    public RecyclerAdapter(ArrayList<UserDetails> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.usercard_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Object o=listData.get(position);
        holder.name.setText(((UserDetails) o).getFirst_name()+" "+((UserDetails) o).getLast_name());
        holder.gender.setText(((UserDetails) o).getGender());
        holder.place.setText(((UserDetails) o).getState());
        holder.age.setText(((UserDetails) o).getDob());
        holder.delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference driverRef = FirebaseDatabase.getInstance().getReference().child("User");
                driverRef.child(((UserDetails) o).getPhone_number()).removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private TextView name,gender,age,place;
        private Button delete_btn;
        public ViewHolder(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardview);
            name=(TextView)itemView.findViewById(R.id.user_name);
            gender=(TextView)itemView.findViewById(R.id.user_gender);
            age=(TextView)itemView.findViewById(R.id.user_age);
            place=(TextView)itemView.findViewById(R.id.user_place);
            delete_btn=(Button) itemView.findViewById(R.id.delete_button);
        }
    }
}
