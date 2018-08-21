package com.boby.myapp.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.net.URL;


public class post extends AppCompatActivity {
private final static int GALLARY_REQUEST=1;
    private ImageView mImageview;
    private EditText ed1;
    private EditText ed2;
    private Button pst;
    private  Uri  mImageuri=null;
    private StorageReference mStorage;
    private ProgressDialog mprogress;
    private DatabaseReference mDb;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabaseb;
    private FirebaseUser mCurrentUser;
public long p=0;
    Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        ed1=(EditText) findViewById(R.id.title_et);
        ed2=(EditText) findViewById(R.id.des_et);
        pst=(Button) findViewById(R.id.postbtn);
        mprogress=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mCurrentUser=mAuth.getCurrentUser();



        mStorage= FirebaseStorage.getInstance().getReference();


         mDatabaseb= FirebaseDatabase.getInstance().getReference().child("Blog");
        mDb=FirebaseDatabase.getInstance().getReference().child("users").child(mCurrentUser.getUid());
        //mDatabaseb.keepSynced(true);


        mImageview=(ImageView) findViewById(R.id.imageView);
        mImageview.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent Gallaryin=new Intent(Intent.ACTION_GET_CONTENT);
                Gallaryin.setType("Image/*");
                startActivityForResult(Gallaryin, GALLARY_REQUEST);
            }
        });
        pst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startpost();
            }
        });

    }

    private void startpost() {
        mprogress.setMessage("Postig to Blog ...");

        final String title_val=ed1.getText().toString().trim();
       final String des_val=ed2.getText().toString().trim();
        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(des_val)&& mImageuri!=null){
            mprogress.show();
            final StorageReference filepath=mStorage.child("blog image").child(mImageuri.getLastPathSegment());
            filepath.putFile(mImageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    @SuppressWarnings("VisibleForTests") final Uri downloadUrl = taskSnapshot.getDownloadUrl();


                    final DatabaseReference newpost = mDatabaseb.push();
                    mDb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            newpost.child("email").setValue(mCurrentUser.getEmail());
                            newpost.child("title").setValue(title_val);
                            newpost.child("description").setValue(des_val);
                            newpost.child("imageUrl").setValue(downloadUrl.toString());

                            newpost.child("numLike").setValue(0);
                            newpost.child("numLike2").setValue(0);

                            newpost.child("usern").setValue(dataSnapshot.child("username").getValue()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        mprogress.dismiss();

                                        startActivity(new Intent(post.this, Main2Activity.class));
                                    }
                                }
                            });
                        }


                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }

                    });



                }


            });

        }




        if (TextUtils.isEmpty(title_val) || TextUtils.isEmpty(des_val)|| mImageuri==null){
            Toast.makeText(post. this, "Please  fill all detail", Toast.LENGTH_SHORT).show();


        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GALLARY_REQUEST && resultCode==RESULT_OK){

            mImageuri=data.getData();
            mImageview.setImageURI(mImageuri);
        }

    }

}
