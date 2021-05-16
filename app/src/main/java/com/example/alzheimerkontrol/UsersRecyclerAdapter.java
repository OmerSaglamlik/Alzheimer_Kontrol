package com.example.alzheimerkontrol;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UsersHolder> {

    private ArrayList<String> UsersNameList;
    private ArrayList<String> UsersLastNameList;
    private ArrayList<String> UsersAgeList;

    public UsersRecyclerAdapter(ArrayList<String> usersNameList, ArrayList<String> usersLastNameList, ArrayList<String> usersAgeList) {
        UsersNameList = usersNameList;
        UsersLastNameList = usersLastNameList;
        UsersAgeList = usersAgeList;
    }

    @NonNull
    @Override
    public UsersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.users_row,parent,false);
        return new UsersHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersHolder holder, int position) {
        holder.nameUserText.setText(UsersNameList.get(position));
        holder.lastNameUserText.setText(UsersLastNameList.get(position));
        holder.birthDateUserText.setText(UsersAgeList.get(position));
    }

    @Override
    public int getItemCount() {
        return UsersNameList.size();
    }

    class  UsersHolder extends RecyclerView.ViewHolder{

        ImageView imageUserView;
        TextView nameUserText;
        TextView lastNameUserText;
        TextView birthDateUserText;

        public UsersHolder(@NonNull View itemView) {
            super(itemView);
            imageUserView = itemView.findViewById(R.id.UsersImage);
            nameUserText = itemView.findViewById(R.id.UsersName);
            lastNameUserText = itemView.findViewById(R.id.UsersLastName);
            birthDateUserText = itemView.findViewById(R.id.UsersAge);
        }
    }
}
