package com.youtube.crudyoutube;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.youtube.crudyoutube.service.CreateService;
import com.youtube.crudyoutube.service.DeleteService;
import com.youtube.crudyoutube.service.UpdateService;

public class FormFragment  extends Fragment {
    EditText name;
    EditText age;
    int personId;
    public FormFragment(){
        super(R.layout.form_layout);
    }
    // still empty

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavController navController = Navigation.findNavController(view);

        //set all button
        view.findViewById(R.id.listButton).setOnClickListener(view1 -> navController.navigate(R.id.navList));
        view.findViewById(R.id.saveButton).setOnClickListener(view1 -> save());
        view.findViewById(R.id.deleteButton).setOnClickListener(view1 -> delete());
        // we don't declare the object ?
        // weird auto complete
        // we complete this basic crud using  android /java . hard yes.. with weird update and error . always plan
        // we will try flutter are it simplier or harder ? React Native seem easy also.. bye subscribe....
        name = view.findViewById(R.id.nameLayoutDetail);
        age = view.findViewById(R.id.ageLayoutDetail);

        personId = requireArguments().getInt("personId");
        if(personId > 0){
            // we check if the person value id > 0
            Log.d("form fragment", "value of person id"+personId);
            // we set all the value here
            name.setText(requireArguments().getString("name"));
            // don't forget this is integer so maybe error and android will give weird error
            age.setText(String.valueOf(requireArguments().getInt("age")));
            // we need enable the button delete
            view.findViewById(R.id.deleteButton).setEnabled(true);
        }
    }
    public void save(){
        if(personId > 0 ){
            Log.d("form fragment", "it go here update");
            // here confirm we will update the value to server
            UpdateService updateService = new UpdateService(getView(),getActivity(),Navigation.findNavController(requireView()));
            updateService.execute(name.getText().toString(),age.getText().toString(),String.valueOf(personId));
        }else {
            Log.d("form fragment", "it go here create");

            CreateService createService = new CreateService(getView(),getActivity(),Navigation.findNavController(requireView()));
            createService.execute(name.getText().toString(),age.getText().toString());
        }
    }
    public void delete() {
        // extra precaution if system don't understand
        if(personId > 0 ) {
            Log.d("form fragment", "it go here delete");

            DeleteService deleteService = new DeleteService(getView(), getActivity(), Navigation.findNavController(requireView()));
            deleteService.execute(String.valueOf(personId));
        }
    }
}
