package com.boby.myapp.myapplication;

import android.app.ProgressDialog;
        import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main3Activity  extends AppCompatActivity /*implements View.OnClickListener*/ {


    //defining views
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;
   private DatabaseReference mDatabaseUser;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        TextView myTextView = (TextView) findViewById(R.id.textView2);
        Typeface typeface=Typeface.createFromAsset(getAssets(), "Fonts/dd.ttf");
        myTextView.setTypeface(typeface);


         //mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Users");
        //mDatabaseUser.keepSynced(true);

        //getting firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();
       // mDatabaseuser= FirebaseDatabase.getInstance().getReference().child("Users");

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            finish();
            //opening profile activity
            startActivity(new Intent(Main3Activity. this, Main2Activity.class));
        }

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) findViewById(R.id.buttonSignin);
        textViewSignup  = (TextView) findViewById(R.id.textViewSignUp);

        progressDialog = new ProgressDialog(this);

        //attaching click listener
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    userLogin();

            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(Main3Activity. this, MainActivity.class));
            }
        });
    }

    //method for user login
    protected void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                           // checkDatabase();
                            Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();



                        }
                        else{
                            //display some message here
                            Toast.makeText(Main3Activity.this,"Login Error",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
  /* protected void checkDatabase(){
       final String user_id=mAuth.getCurrentUser().getUid();
       mDatabaseuser.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
             if(dataSnapshot.hasChild(user_id)){
                 Intent intent = new Intent(Main3Activity.this, Main2Activity.class);
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 startActivity(intent);
                 finish();
             }
             else{
                 //display some message here
                 Toast.makeText(Main3Activity.this,"You don't have account plaese Signup",Toast.LENGTH_LONG).show();
             }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
   }*/


}