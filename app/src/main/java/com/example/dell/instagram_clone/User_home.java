package com.example.dell.instagram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseUser;

public class User_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Parse.initialize(this);
        ParseUser currentUser=ParseUser.getCurrentUser();
        String user=currentUser.getUsername().toString();
        //.getUsername();
        setTitle(user);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.view_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {
            case R.id.use_home:
                startActivity(new Intent(this,User_home.class));
                return true;

            case R.id.camera:
                startActivity(new Intent(this,Take_Photo.class));
                return true;

            case R.id.list_use:
                startActivity(new Intent(this,List_User.class));
                return true;
            case R.id.cancel:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
