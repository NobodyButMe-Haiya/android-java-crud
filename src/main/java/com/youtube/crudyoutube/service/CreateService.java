package com.youtube.crudyoutube.service;
// if you ask me why doesn't so easy to make folder . i don't know ?

import android.app.DownloadManager;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;

// you may get more info at https://developer.android.com/training/volley
import com.android.volley.AuthFailureError;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.youtube.crudyoutube.R;
import com.youtube.crudyoutube.model.FailureModel;
import com.youtube.crudyoutube.model.SuccessModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CreateService {
    private final View view;
    private final FragmentActivity fragmentActivity;
    // final and not final ? final one times only .. declare
    private String URL;
    // are we missing something ? Maybe we need something call volley
    private RequestQueue requestQueue;

    NavController navController;
    public CreateService(View view1, FragmentActivity fragmentActivity1,NavController navController1) {

        fragmentActivity = fragmentActivity1;
        view = view1;
        // URL = NetworksConnection.SERVER.toString();  // what his weird  ? Sometimes we want to put everything one place url
        URL = "http://192.168.0.154/php_tutorial/api.php";
        navController = navController1;
    }

    /// is this normal  not it just our ways
    public void execute(final String... strings) {
        // We don't prefer to call on the spot so we call execute when we needed
        Log.d("Info here", "Value 0 " + strings[0] + "Value 1 " + strings[1] + "...  so on whatever paramter you want to post");

        // what the heck android studio ? give error but no info ?
        // oh lamda  each day become me confuse
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, this::getDataFromServer, error -> Log.d("info", error.toString())){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                // seem nicer but really odd
                return new HashMap<String,String>(){{
                    put("name",strings[0]);
                    put("age",strings[1]);
                    put("mode","create");
                }};
            }
        };
        // or maybe crazy shorter
        Volley.newRequestQueue(fragmentActivity).add(stringRequest);
    }
    private void getDataFromServer(String response){
        Log.d("info",response);
        // we continue back with the response .
        // now we can copy paste as  update  and delete we just change the parameter
        try {
            new JSONObject(response);
            if(response.contains("false")){
                // you can return text you can return anything but we prefer  sweet alert android version
                // but how to parse this json  and did we need model  ?
                final FailureModel failureModel;
                failureModel =  new Gson().fromJson(response,FailureModel.class);
                // so at least we know what how to get the data
                Log.e("info",failureModel.getMessage());
            }else{
                final SuccessModel successModel;
                successModel =  new Gson().fromJson(response,SuccessModel.class);
                // so at least we know what how to get the data
                Log.e("info",successModel.getMessage());
                navController.navigate(R.id.navList);
            }
        }catch (Exception ex){
            Log.d("info",ex.toString());
        }
    }

}
