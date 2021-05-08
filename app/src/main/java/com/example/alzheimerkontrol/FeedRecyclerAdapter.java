package com.example.alzheimerkontrol;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.PostHolder> {
    private ArrayList<String> userEmailList;
    private ArrayList<String> userNameList;
    private ArrayList<String> userLastNameList;
    private ArrayList<String> userImageList;
    private ArrayList<String> userBirthDateList;
    private ArrayList<String> userCityList;
    private ArrayList<String> userInfoList;
    private ArrayList<String> userFamilyList;


    public FeedRecyclerAdapter(ArrayList<String> userBirthDateList, ArrayList<String> userInfoList, ArrayList<String> userCityList, ArrayList<String> userFamilyList,  ArrayList<String> userNameList, ArrayList<String> userLastNameList, ArrayList<String> userImageList) {

        this.userNameList = userNameList;
        this.userLastNameList = userLastNameList;
        this.userImageList = userImageList;
        this.userBirthDateList= userBirthDateList;
        this.userInfoList = userInfoList;
        this.userCityList = userCityList;
        this.userFamilyList= userFamilyList;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row,parent,false);

        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        holder.nameText.setText(userNameList.get(position));
        holder.lastNameText.setText(userLastNameList.get(position));
        holder.birthDateText.setText(userBirthDateList.get(position));
        holder.cityText.setText(userCityList.get(position));
        holder.infoText.setText(userInfoList.get(position));
        holder.familyText.setText(userFamilyList.get(position));
        Picasso.get().load(userImageList.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return userNameList.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nameText;
        TextView lastNameText;
        TextView birthDateText;
        TextView cityText;
        TextView infoText;
        TextView familyText;
        TextView userBirthDateFrom ;
        TextView userInfoFrom ;
        TextView userCityFrom ;
        TextView userFamilyFrom ;
        public PostHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.recycler_imageView);
            nameText = itemView.findViewById(R.id.recycler_textView_Name);
            lastNameText = itemView.findViewById(R.id.recycler_textView_LastName);
            birthDateText = itemView.findViewById(R.id.recycler_textView_BirthDate);
            infoText = itemView.findViewById(R.id.recycler_textView_Information);
            cityText =  itemView.findViewById(R.id.recycler_textView_City);
            familyText  = itemView.findViewById(R.id.recycler_textView_FamilyMember);
        }
    }


}
