package com.youtube.crudyoutube.service;

import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.youtube.crudyoutube.adapter.ReadAdapter;
import com.youtube.crudyoutube.model.FailureModel;
import com.youtube.crudyoutube.model.ReadModel;
import com.youtube.crudyoutube.model.SuccessModel;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReadService {
    private final View view;
    private final FragmentActivity fragmentActivity;
    // final and not final ? final one times only .. declare
    private String URL;
    // are we missing something ? Maybe we need something call volley
    private RequestQueue requestQueue;

   private ReadAdapter readAdapter;
    public ReadService(View view1, FragmentActivity fragmentActivity1, ReadAdapter readAdapter1) {

        fragmentActivity = fragmentActivity1;
        view = view1;
        // URL = NetworksConnection.SERVER.toString();  // what his weird  ? Sometimes we want to put everything one place url
        // as some lesson before  we use port since localhost is from the server phone it self so put try ip..
        // make sure no https  unless your server at internet.. or your setup it
        URL = "http://192.168.0.154/php_tutorial/api.php";
        readAdapter = readAdapter1;
    }

    // we don't need parameter but if you needed you can add yourself
    public void execute() {
        // We don't prefer to call on the spot so we call execute when we needed

        // what the heck android studio ? give error but no info ?
        // oh lamda  each day become me confuse
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, this::getDataFromServer, error -> Log.d("info", error.toString())){
            @Nullable
            @Override
            protected Map<String, String> getParams() {
                // seem nicer but really odd
                return new HashMap<String,String>(){{
                    put("mode","read");
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
            if(response.contains("error")){
                // you can return text you can return anything but we prefer  sweet alert android version
                // but how to parse this json  and did we need model  ?
                final FailureModel failureModel;
                failureModel =  new Gson().fromJson(response,FailureModel.class);
                // so at least we know what how to get the data
                Log.e("info",failureModel.getMessage());
            }else{
                // but here a bit diff  ..  We need to bind or inform whatever we got list
                final ReadModel readModel;
                readModel =  new Gson().fromJson(response,ReadModel.class);
                // it will list all data ..
                Log.e("info",readModel.toString());
                // the next state is video is the list and we need something call adapter too loop the data. Do subscribe ? or just wait also can till we publish it .
                // re-continue ..  We need something 1 extra layout - detail and how to loop the data using Adapter ? RecyclerView ? Example this kinda scrary sometimes
                readAdapter.execute(readModel.getData());
                // haloo halllooo we have data  we have data
                readAdapter.notifyDataSetChanged();

            }
        }catch (Exception ex){
            Log.d("info",ex.toString());
        }
    }

}
