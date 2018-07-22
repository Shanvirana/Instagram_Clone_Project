package com.example.dell.instagram_clone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class signup_activity extends AppCompatActivity {


    Button signup;
    EditText name,email,pass,gender;
    String n,p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup_activity);
        Parse.initialize(this);
        signup=(Button)findViewById(R.id.signup);
        name=(EditText)findViewById(R.id.name);
        pass=(EditText)findViewById(R.id.pass);
        email=(EditText)findViewById(R.id.email);
        //gender=(EditText)findViewById(R.id.gender);

setTitle("");
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n=name.getText().toString();
                final ProgressDialog dig=new ProgressDialog(signup_activity.this);
                dig.setTitle("Please wait");
                dig.setMessage("Signing Up..Please Wait");
                dig.show();
                ParseUser user=new ParseUser();
                user.setUsername(name.getText().toString());
                user.setPassword(pass.getText().toString());
                user.setEmail(email.getText().toString());
               // user.setGender(gender.getText().toString());

                ParseObject parseobj = new ParseObject("Insta_Users");
                parseobj.put("name",name.getText().toString());
                parseobj.put("email",email.getText().toString());
                parseobj.put("password",pass.getText().toString());
               /* if(gender.getText().toString() == "M")
                    parseobj.put("gender","male");
                else if(gender.getText().toString() == "F")
                    parseobj.put("gender","female");
                else
                    parseobj.put("gender","default");
*/
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        dig.dismiss();
                        if(e!=null){
                            Toast.makeText(signup_activity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                        else{
                            if(ParseUser.getCurrentUser()!=null){
                                startActivity(new Intent(signup_activity.this,User_home.class));
                            }
                            else
                                Toast.makeText(signup_activity.this,"Error occured",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent i=new Intent(signup_activity.this,MainActivity.class);
                startActivity(i);
                setTitle("");
            }
        });


    }
}
