package com.boby.myapp.myapplication;




    import android.content.Context;
    import android.content.Intent;
    import android.net.Uri;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.support.v7.widget.LinearLayoutManager;
    import android.support.v7.widget.RecyclerView;
    import android.util.Log;
    import android.view.Menu;
    import android.view.MenuInflater;
    import android.view.MenuItem;
    import android.view.View;

    import android.widget.Button;
    import android.widget.ImageButton;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import com.firebase.ui.database.FirebaseRecyclerAdapter;
    import com.github.clans.fab.FloatingActionButton;
    import com.github.clans.fab.FloatingActionMenu;
    import com.google.firebase.auth.FirebaseAuth;
    import com.google.firebase.auth.FirebaseUser;
    import com.google.firebase.database.ChildEventListener;
    import com.google.firebase.database.DataSnapshot;
    import com.google.firebase.database.DatabaseError;
    import com.google.firebase.database.DatabaseReference;
    import com.google.firebase.database.FirebaseDatabase;
    import com.google.firebase.database.Query;
    import com.google.firebase.database.ValueEventListener;
    import com.squareup.picasso.Callback;
    import com.squareup.picasso.NetworkPolicy;
    import com.squareup.picasso.Picasso;

    import static android.R.attr.countDown;
    import static android.R.attr.fingerprintAuthDrawable;
    import static android.R.attr.id;

public class Main2Activity extends AppCompatActivity  {

        //firebase auth object
        private FirebaseAuth firebaseAuth;
        private RecyclerView mbloglist;
        private DatabaseReference mDatabase;
    FirebaseDatabase database;
    private DatabaseReference mDatabaselike;
   private DatabaseReference mDatabaselike2;

        //view objects

    private FirebaseAuth mAuth;
    private  Boolean mProcesslike=false;
    private DatabaseReference mDatabaseOne;
    private Query mQueryOne;
    public  long numLike;
    public  long numLike2;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2,  floatingActionButton4, floatingActionButton5, floatingActionButton6;






        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main2);




         mbloglist=(RecyclerView) findViewById(R.id.blog_view);

            mbloglist.setHasFixedSize(true);




            mbloglist.setLayoutManager(new LinearLayoutManager(this));


            firebaseAuth = FirebaseAuth.getInstance();
            mAuth=FirebaseAuth.getInstance();


            if(firebaseAuth.getCurrentUser() == null){
                //close this activity
                finish();
                //opening profile activity
                startActivity(new Intent(Main2Activity. this, Main3Activity.class));
            }

            //database=FirebaseDatabase.getInstance();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("Blog");
         // mDatabase.keepSynced(true);
            mDatabaselike=FirebaseDatabase.getInstance().getReference().child("Like");
            mDatabaselike2=FirebaseDatabase.getInstance().getReference().child("Like2");
            //mDatabaselike.keepSynced(true);


//mDatabaseOne=FirebaseDatabase.getInstance().getReference().child("Blog");
            //String user="boby19@gmail.com";
           //mQueryOne=mDatabaseOne.orderByChild("title");

                   //equalTo(user);
            materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
            floatingActionButton1 = (FloatingActionButton) findViewById(R.id.floating_facebook);
            floatingActionButton2 = (FloatingActionButton) findViewById(R.id.floating_twitter);

            floatingActionButton4 = (FloatingActionButton) findViewById(R.id.floating_google_plus);
            floatingActionButton5 = (FloatingActionButton) findViewById(R.id.floating_instagram);
            floatingActionButton6 = (FloatingActionButton) findViewById(R.id.floating_youtube);

            floatingActionButton1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu first item clicked
                   Intent facebookIntent = getOpenFacebookIntent(Main2Activity.this);
                    startActivity(facebookIntent);

                }
            });
            floatingActionButton2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu second item clicked
                    Intent twitterIntent = getOpenTwitterIntent(Main2Activity.this);
                    startActivity(twitterIntent);

                }
            });


            floatingActionButton4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu first item clicked
                    Intent googleplusIntent = getOpenGPlusIntent(Main2Activity.this);
                    startActivity(googleplusIntent);
                }
            });
            floatingActionButton5.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu second item clicked
                    Intent instagramIntent = getOpenInstagramIntent(Main2Activity.this);
                    startActivity(instagramIntent);
                }
            });
            floatingActionButton6.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO something when floating action menu third item clicked
                    Intent youtubeIntent = getOpenYouTubeIntent(Main2Activity.this);
                    startActivity(youtubeIntent);
                }
            });








        }

        public static Intent getOpenFacebookIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.facebook.katana", 0); //Checks if FB is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("fb://page/376227335860239")); //Trys to make intent with FB's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenTwitterIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.twitter.android", 0); //Checks if Twitter is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com/drkarthiik")); //Trys to make intent with Twitter's's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://twitter.com")); //catches and opens a url to the desired page
        }
    }



    public static Intent getOpenGPlusIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.apps.plus", 0); //Checks if G+ is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com/u/0/+KarthikM128")); //Trys to make intent with G+'s URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://plus.google.com")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenInstagramIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.instagram.android", 0); //Checks if Instagram is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //Trys to make intent with Instagram's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/accounts/login/")); //catches and opens a url to the desired page
        }
    }

    public static Intent getOpenYouTubeIntent(Context context) {

        try {
            context.getPackageManager()
                    .getPackageInfo("com.google.android.youtube", 0); //Checks if YT is even installed.
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com")); //Trys to make intent with YT's URI
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.youtube.com")); //catches and opens a url to the desired page
        }
    }




        @Override
        protected void onStart() {
            super.onStart();

            FirebaseRecyclerAdapter<Blog, BlogViewHolder>firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                    Blog.class,
                    R.layout.blog_row,
                    BlogViewHolder.class,
                    mDatabase


                    ) {


                @Override
                protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, final int position) {

                  final String post_key =getRef(position).getKey();
                    viewHolder.setTitle(model.getTitle());
                    viewHolder.setDescription(model.getDescription());

                    viewHolder.setImageUrl(getApplicationContext(),model.getImageUrl());




                    viewHolder.setlikebtn(post_key);

                   viewHolder.setNumLike(model.getNumLike());
                    viewHolder.setlikebtn2(post_key);
                    viewHolder.setNumLike2(model.getNumLike2());
                   //viewHolder.Likebtn(post_key);


                    viewHolder.title.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Main2Activity.this, singlePost.class);
                            intent.putExtra("blog_id",post_key);

                            startActivity(intent);

                        }
                    });
                    viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Main2Activity.this, singlePost.class);
                            intent.putExtra("blog_id",post_key);

                            startActivity(intent);

                        }
                    });








                    viewHolder.mlikebtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            mProcesslike=true;


                              mDatabaselike.addValueEventListener(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                      if(mProcesslike) {
                                          if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                             // mDatabaselike.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                              //mProcesslike = false;


                                          } else {
                                              mDatabaselike.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue("hello");
                                              mProcesslike = false;






                                          }
                                      }


                                  }

                                  @Override
                                  public void onCancelled(DatabaseError databaseError) {


                                  }
                              });



                            mDatabaselike.addChildEventListener(new ChildEventListener()
                            {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s)
                                {

                                        numLike = dataSnapshot.getChildrenCount();
                                    mDatabaselike.child(post_key).child("numLike").setValue(numLike);

                                        mDatabase.child(post_key).child("numLike").setValue(numLike);

                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s)
                                {
                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot)
                                {
                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s)
                                {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError)
                                {

                                }
                            });




                        }


                    });
                    viewHolder.mlike2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mProcesslike=true;


                            mDatabaselike2.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(mProcesslike) {
                                        if (dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {
                                            // mDatabaselike.child(post_key).child(mAuth.getCurrentUser().getUid()).removeValue();
                                            //mProcesslike = false;


                                        } else {
                                            mDatabaselike2.child(post_key).child(mAuth.getCurrentUser().getUid()).setValue(mAuth.getCurrentUser().getEmail());
                                            mProcesslike = false;






                                        }
                                    }


                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {


                                }
                            });



                            mDatabaselike2.addChildEventListener(new ChildEventListener()
                            {
                                @Override
                                public void onChildAdded(DataSnapshot dataSnapshot, String s)
                                {

                                    numLike2 = dataSnapshot.getChildrenCount();
                                    mDatabaselike2.child(post_key).child("numLike2").setValue(numLike2);

                                    mDatabase.child(post_key).child("numLike2").setValue(numLike2);

                                }

                                @Override
                                public void onChildChanged(DataSnapshot dataSnapshot, String s)
                                {
                                }

                                @Override
                                public void onChildRemoved(DataSnapshot dataSnapshot)
                                {
                                }

                                @Override
                                public void onChildMoved(DataSnapshot dataSnapshot, String s)
                                {

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError)
                                {

                                }
                            });






                        }


                    });



                }
            };
            mbloglist.setAdapter(firebaseRecyclerAdapter);

        }
        public static class BlogViewHolder extends RecyclerView.ViewHolder{
            View mView;
            ImageButton mlikebtn;
            ImageButton mlike2;
            DatabaseReference mDatabaselike;
            DatabaseReference mDatabaselike2;
            DatabaseReference db2;
            FirebaseAuth mAuth;


            TextView title;





            public BlogViewHolder(View itemView) {
                super(itemView);
                mView=itemView;
                mlikebtn=(ImageButton) mView.findViewById(R.id.likebtn);
                mlike2=(ImageButton) mView.findViewById(R.id.likebtn1);
                mDatabaselike=FirebaseDatabase.getInstance().getReference().child("Like");
                //mDatabaselike.keepSynced(true);
                mDatabaselike2=FirebaseDatabase.getInstance().getReference().child("Like2");
              db2=FirebaseDatabase.getInstance().getReference().child("Like").child("numLike");


                 title=(TextView) mView.findViewById(R.id.post_title);







                //mDatabaselike.keepSynced(true);
                mAuth=FirebaseAuth.getInstance();



            }






            public void setlikebtn(final String post_key){

                mDatabaselike.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){
                            mlikebtn.setImageResource(R.mipmap.up);
                            mlikebtn.setEnabled(false);
                            mlikebtn.setClickable(false);
                            mlike2.setEnabled(false);
                            mlike2.setClickable(false);






                        }
                       // else {
                        //    mlikebtn.setImageResource(R.mipmap.ic_thumb_up_black_24dp);
                        //}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }
            public void setlikebtn2(final String post_key){

                mDatabaselike2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())){
                            mlike2.setImageResource(R.drawable.red);
                            mlike2.setEnabled(false);
                            mlike2.setClickable(false);
                            mlikebtn.setEnabled(false);
                            mlikebtn.setClickable(false);






                        }
                        // else {
                        //    mlikebtn.setImageResource(R.mipmap.ic_thumb_up_black_24dp);
                        //}
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }




           /*  public void Likebtn(final String post_key){




                db2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(post_key).hasChild(mAuth.getCurrentUser().getUid())) {

                            String numLike = dataSnapshot.getValue(String.class);
                            mCount=(TextView) mView.findViewById(R.id.count);

                            mCount.setText("90");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }*/
            public void setNumLike2(long numLike2) {
                TextView postCount2=(TextView) mView.findViewById(R.id.count1);
                postCount2.setText(""+numLike2);
            }
           public void setNumLike(long numLike) {
              TextView postCount=(TextView) mView.findViewById(R.id.count);
               postCount.setText(""+numLike);
           }



            public void setTitle(String title){
                TextView post_title=(TextView) mView.findViewById(R.id.post_title);
                post_title.setText(title);

            }
            public void setDescription(String description){
                TextView post_des=(TextView) mView.findViewById(R.id.post_des);
                post_des.setText(description);

            }
            public void  setImageUrl(final Context ctx ,final String imageUrl){
                final ImageView post_image=(ImageView) mView.findViewById(R.id.post_image);
                Picasso.with(ctx).load(imageUrl).into(post_image);
                /*Picasso.with(ctx).load(imageUrl).networkPolicy(NetworkPolicy.OFFLINE).into(post_image, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(ctx).load(imageUrl).into(post_image);

                    }
                });*/

            }




        }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();



        if (id == R.id.action_logout ) {
            logout();
            return true;

        }


        if (id == R.id.action_add  ) {
            startActivity(new Intent(Main2Activity. this ,post.class));

            return true;





        }
        if(id  ==  R.id.admin_about){
            startActivity(new Intent(Main2Activity. this ,about_us.class));
            return true;
        }




        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }





}