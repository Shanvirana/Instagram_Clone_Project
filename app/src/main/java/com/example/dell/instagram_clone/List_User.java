package com.example.dell.instagram_clone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dell.instagram_clone.Model.Users;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class List_User extends AppCompatActivity {

    ListView viewUserListView;
    ArrayAdapter<Users> usernamelistAdapter;
    List<Users> userList;
    ProgressBar progressbar;
    ArrayList<String> usernames;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list__user);
        Parse.initialize(this);
        viewUserListView = (ListView)findViewById(R.id.list_user);
        progressbar = (ProgressBar)findViewById(R.id.progress_bar);

        userList = new ArrayList<Users>();
        usernamelistAdapter = new ArrayAdapter<Users>(this,R.layout.users,R.id.name_user,userList);
        viewUserListView.setAdapter(usernamelistAdapter);
        showUsers();
        ParseUser currentUser=ParseUser.getCurrentUser();
        String user=currentUser.getUsername().toString();
        //.getUsername();
        setTitle(user);
    }
    public void showUsers()
    {
        /*
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Insta_Users");
        progressbar.setVisibility(View.VISIBLE);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null)
                {
                    progressbar.setVisibility(View.INVISIBLE);
                    //userList.clear();
                    for (ParseObject users : objects)
                    {
                        Users users1 = new Users(users.getString("name"), users.getString("email"));
                        users.getString("name");
                        users.getString("email");
                        //users.getString("password");
                       // users.getString("gender");
                        userList.add(users1);
                    }
                    usernamelistAdapter.notifyDataSetChanged();
                }
                else
                {
                    Log.d("ERROR MESSAGE","ERROR :  "+e.getMessage());
                }
            }
        });
        */


        usernames=new ArrayList<String>();
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,usernames);
        final ListView userList=(ListView)findViewById(R.id.list_user);

        ParseQuery<ParseUser> query=ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");

        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e==null){
                    if(objects.size()>0){
                        for(ParseUser user:objects){
                            usernames.add(user.getUsername());
                        }

                        userList.setAdapter(arrayAdapter);
                    }
                }
            }
        });


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
