package com.example.dell.instagram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText  emailedit, passedit;
    Button login,signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       Parse.initialize(this);
        setContentView(R.layout.activity_main);
        emailedit = (EditText) findViewById(R.id.email);
        passedit = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
        setTitle("");
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.login) {

            //Toast.makeText(MainActivity.this," login ",Toast.LENGTH_SHORT).show();
            ParseUser.logInInBackground(emailedit.getText().toString(), passedit.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    //dig.dismiss();
                    if(e!=null){
                        Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(MainActivity.this,signup_activity.class);
                        startActivity(i);
                    }
                    else{
                        //Toast.makeText(MainActivity.this,"else login",Toast.LENGTH_SHORT).show();
                        if(ParseUser.getCurrentUser() != null){
                            startActivity(new Intent(MainActivity.this,User_home.class));
                           // Toast.makeText(MainActivity.this,"else login if",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            //Toast.makeText(MainActivity.this,"else login else",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,signup_activity.class));
                        }
                    }
                }
            });


        }
        else if (v.getId() == R.id.signup){
            startActivity(new Intent(this,signup_activity.class));

        }
    }

}

