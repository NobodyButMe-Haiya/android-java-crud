package com.youtube.crudyoutube;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.youtube.crudyoutube.adapter.ReadAdapter;
import com.youtube.crudyoutube.service.ReadService;

public class ListFragment extends Fragment {
    public ListFragment (){

        super(R.layout.list_layout);
    }
    // wait with all seem depreciated in the internet ? ignore them all

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // later we will used in next video to appear data from list to form to update
        // so it will work or not ? not sure we don't copy paste and auto complete most of them ?
        // so ne the project oor oops something we need some divider before ending this tutorial
        NavController navController = Navigation.findNavController(view);

        view.findViewById(R.id.createButton).setOnClickListener(view1 -> {
            Bundle bundle = new Bundle();
            bundle.putString("name","");
            bundle.putInt("age",0);
            bundle.putInt("personId",0);
            navController.navigate(R.id.navForm,bundle);
        });
        FragmentActivity fragmentActivity = requireActivity();

        ReadAdapter readAdapter = new ReadAdapter(navController);
        // we don't need here since service will handle it

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(fragmentActivity);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        RecyclerView recyclerView =  view.findViewById(R.id.recycledViewListLayout);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        // hmm  getting weirder since depreciated  so we try new way
        // so in conclusion why i make in title 2021 ? most example is depreciated and even google tutorial some ish
        // we continue on next video .Do subscribe when publish

        // we continue again ..    now more on  design /layout
        // and link all info together
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(),linearLayoutManager.getOrientation()));


        recyclerView.setAdapter(readAdapter);
        //readAdapter.execute();?


        ReadService readService = new ReadService(view,fragmentActivity,readAdapter);
        readService.execute();
    }
}
