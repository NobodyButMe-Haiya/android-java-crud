package com.youtube.crudyoutube;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class MainActivity  extends AppCompatActivity {
    // this is pure empty not link with the design layout
    public MainActivity(){
        // now we link the code to the design layout .  but  android don't know which java to start ?
        super(R.layout.main_layout);
    }
    // we will create our own navigation so remove the old  one

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}
