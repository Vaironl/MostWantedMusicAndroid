package com.example.vaironl.mostwantedradio;

import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

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

    private void init() {
        imageTL = (TableLayout) findViewById(R.id.imagesTableLayout);
        getImages();

    }

    private void getImages() {
        String ACCESSTOKEN = "EAACEdEose0cBAExzgEKkE7sCmST8IDZAxf3XIDOnfNjfCjUIkvVGY7aefmrqWZA76fZA94ZBa5VsjvQRLeTgRIckn4Y48G2pMTFZCQD0Ea6ZCNvGlzZANDdk3fSE2ZBcVpl9we14O4ke17EAjD0OcgfULwc38W7NO6TuEZAScQpaZCiBfqNIvWmbC9j8HL8Ijz42AqvxZB181wyyQZDZD";
        AccessToken accessToken = null;

        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        // Application code
                        if (response != null) {
                            Log.i("GraphAPIInfo", "Response was null");
                            Log.i("GraphAPIInfo", isObjectNull(object));
                        } else {
                            Log.i("GraphAPIInfo", response.getRawResponse());

                        }

                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private String isObjectNull(Object object){
        if(object != null){
            return "It is not a null object";
        }
        return "It is a null object";
    }

    private void hideImages() {
        imageTL.setVisibility(View.GONE);
    }

    private void showImages() {
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
