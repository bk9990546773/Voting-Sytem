package com.boby.myapp.myapplication;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

/**
 * Created by boby on 3/10/2017.
 */

public class SimpleBlog extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

       /*if(!FirebaseApp.getApps(this).isEmpty()){

           FirebaseDatabase.getInstance().setPersistenceEnabled(true);}
      /* Picasso.Builder builder=new Picasso.Builder(this);
        builder.downloader(new OkHttpDownloader(this,Integer.MAX_VALUE));
        Picasso build=builder.build();
        build.setIndicatorsEnabled(false);
        build.setLoggingEnabled(true);
        Picasso.setSingletonInstance(build);*/



    }
}
