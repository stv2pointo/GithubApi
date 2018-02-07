package com.stvjuliengmail.githubapi.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.stvjuliengmail.githubapi.R;
import com.stvjuliengmail.githubapi.model.GitHubUser;
import com.stvjuliengmail.githubapi.rest.APIClient;
import com.stvjuliengmail.githubapi.rest.GitHubUserEndPoints;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.HTTP;

public class UserActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedRepos;

    Bundle extras;
    String newString;

    Bitmap myImage;

    public UserActivity() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = (ImageView) findViewById(R.id.avatar);
        userNameTV = (TextView) findViewById(R.id.username);
        followersTV = (TextView) findViewById(R.id.followers);
        followingTV = (TextView) findViewById(R.id.following);
        logIn = (TextView) findViewById(R.id.logIn);
        email = (TextView) findViewById(R.id.email);
        ownedRepos = (Button) findViewById(R.id.ownedReposBtn);

        extras = getIntent().getExtras();
        newString = extras.getString("STRING_I_NEED");

        System.out.println(newString);
        loadData();

    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url  = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void loadData(){
        final GitHubUserEndPoints apiService =
                APIClient.getClient().create(GitHubUserEndPoints.class);

        Call<GitHubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {

                ImageDownloader task = new ImageDownloader();
                try{
                    myImage = task.execute(response.body().getAvatar()).get();
                    avatarImg.setImageBitmap(myImage);
                    avatarImg.getLayoutParams().height = 220;
                    avatarImg.getLayoutParams().width = 220;
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                String un = "Username: ";
                un += (response.body().getName() == null) ? "No name" : response.body().getName();
                userNameTV.setText(un);
                String fr = "Followers: ";
                fr += (response.body().getFollowers() == null) ? "None" : response.body().getFollowers();
                followersTV.setText(fr);
                String fg = "Following: ";
                fg += (response.body().getFollowing() == null) ? "None" : response.body().getFollowing();
                followingTV.setText(fg);
                String li = "LogIn: ";
                li += (response.body().getLogin() == null) ? "None" : response.body().getLogin();
                logIn.setText(li);
                String em = "Email: ";
                em += (response.body().getEmail() == null) ? "No email provided" : response.body().getEmail();
                email.setText(em);
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {

            }
        });
    }

    public void loadOwnRepos(View view){
        Intent intent = new Intent(UserActivity.this, Repositories.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }


}

