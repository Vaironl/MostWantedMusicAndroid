package com.example.vaironl.mostwantedradio;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TableLayout imageTL;//imagesTableLayout

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    showImages();
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    hideImages();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    hideImages();
                    return true;
            }
            return false;
        }

    };

    private void init(){
        imageTL = (TableLayout) findViewById(R.id.imagesTableLayout);

    }

    private void getImages(){
        AccessToken accessToken = null;

        GraphRequest request = GraphRequest.newGraphPathRequest(null, "/GameOfThrones/photos",
                new GraphRequest.Callback(){
                    @Override
                    public void onCompleted(GraphResponse response) {
                        //Insert your code here
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void hideImages(){
        imageTL.setVisibility(View.GONE);
    }
    private void showImages(){
        imageTL.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
