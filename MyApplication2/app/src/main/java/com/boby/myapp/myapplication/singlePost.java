package com.boby.myapp.myapplication;

import android.graphics.Typeface;
import android.icu.text.NumberFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class singlePost extends AppCompatActivity {
  private  String mpost_key=null;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabase2;
    private ImageView singleImage;
    private TextView singleTitle,likeperc1,likeperc2;
    private TextView singleDesc;
    private TextView singleNumLike;
    private ProgressBar first,second;
    private TextView t1,t2,d1,d2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_post);
       mpost_key=getIntent().getExtras().getString("blog_id");
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Blog");
        mDatabase2= FirebaseDatabase.getInstance().getReference().child("BlogDetail");


        singleImage=(ImageView) findViewById(R.id.post_image2);
        singleTitle=(TextView) findViewById(R.id.post_title2);
        singleDesc=(TextView) findViewById(R.id.post_des2);
        singleNumLike=(TextView) findViewById(R.id.count3);
        first=(ProgressBar) findViewById(R.id.progressBar5);
        second=(ProgressBar)  findViewById(R.id.progressBar6);
        likeperc1=(TextView) findViewById(R.id.percentage1);
        likeperc2=(TextView) findViewById(R.id.percentage2);
        t1=(TextView) findViewById(R.id.title1);
        t2=(TextView) findViewById(R.id.title2);
        d1=(TextView) findViewById(R.id.des1);
        d2=(TextView) findViewById(R.id.des2);

        mDatabase2.child(mpost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title1=(String) dataSnapshot.child("title1").getValue();
                String des1=(String) dataSnapshot.child("desc1").getValue();
                String title2=(String) dataSnapshot.child("title2").getValue();
                String des2=(String) dataSnapshot.child("desc2").getValue();

                t1.setText(title1);
                t2.setText(title2);
                d1.setText(des1);
                d2.setText(des2);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mDatabase.child(mpost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String post_title=(String) dataSnapshot.child("title").getValue();
                String post_des=(String) dataSnapshot.child("description").getValue();
                String post_image=(String) dataSnapshot.child("imageUrl").getValue();
               long post_like1=(long) dataSnapshot.child("numLike").getValue();
                long post_like2=(long) dataSnapshot.child("numLike2").getValue();
                Picasso.with(singlePost.this).load(post_image).into(singleImage);
                singleDesc.setText(post_des);
                singleTitle.setText(post_title);

              //  singleNumLike.setText(""+post_like1);
                int temp=(int)post_like1;
                int temp2=(int)post_like2;
                int tot=temp+temp2;
                first.setMax(tot);
                second.setMax(tot);

                first.setProgress(temp);
                second.setProgress(temp2);


                float per1= (float) temp*100/tot;
                float perc2=(float) temp2*100/tot;

                String sValue = (String) String.format("%.2f", per1);
                Double newValue = Double.parseDouble(sValue);
                likeperc1.setText(""+newValue+"% People");
                String s2Value = (String) String.format("%.2f", perc2);
                Double newValue2 = Double.parseDouble(s2Value);


                likeperc2.setText(""+newValue2+"% People");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
