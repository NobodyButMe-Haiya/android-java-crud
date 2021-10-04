package com.youtube.crudyoutube.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

// weird auto complete again
import com.youtube.crudyoutube.R;
import com.youtube.crudyoutube.model.DataModel;

import java.util.List;

public class ReadAdapter extends RecyclerView.Adapter<ReadAdapter.ViewHolder> {
    NavController navController;
    public ReadAdapter(NavController navController1){
        navController = navController1;
    }
    // auto complete kinda hard and mostly problem here no explanation ?
    List<DataModel> dataModelList;
    public void execute(List<DataModel> dataModelList1){
        dataModelList = dataModelList1;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // here we get the information layout  So we need to create dummy layout detail
        // we just follow the auto complete if work hope
        LayoutInflater inflater =  LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.list_layout_detail,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // we need the list something here
        holder.getName().setText(dataModelList.get(position).getName());
        // since this is integer
        holder.getAge().setText(String.valueOf(dataModelList.get(position).getAge()));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("name",dataModelList.get(position).getName());
            bundle.putInt("age",dataModelList.get(position).getAge());
            bundle.putInt("personId",dataModelList.get(position).getPersonId());
            navController.navigate(R.id.navForm,bundle);
        });
    }

    @Override
    public int getItemCount() {
        // so here  hmmm
        return (dataModelList == null)?0:dataModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // so we got the layout then here set the item
            name = itemView.findViewById(R.id.nameLayoutDetail);
            age = itemView.findViewById(R.id.ageLayoutDetail);
        }
        // wanna lazy we create getter
        // hmm how this ai understand ? i don't know ?  sometimes good and not ?
        public TextView getName() {
            return name;
        }

        public TextView getAge() {
            return age;
        }
    }
}
