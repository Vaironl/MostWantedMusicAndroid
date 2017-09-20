package com.example.vaironl.mostwantedradio;

import com.facebook.AccessToken;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.squareup.picasso.Picasso;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private TableLayout imageTL;//imagesTableLayout
    private Button addImageButton;
    private WebView browser;
    private String LINORADIOURL = "http://www.linoradio.com/es/home";
    private String imageURLs[] = {"https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21370899_509698619368112_4050977821023319945_n.jpg?oh=a72e8346284549d3f6ee5f927b35130f&oe=5A54EE50",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21462933_511336482537659_8145365664971928660_n.jpg?oh=70294a2537a9d995d19a13a3e200b563&oe=5A190DC3",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21462834_510428682628439_2699983785360964199_n.jpg?oh=1f1c4c72dfab5c85558acc625bf9cb90&oe=5A1A4ADD",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21317614_510182819319692_1574990176623324803_n.jpg?oh=7a1f7bb79b3bd564d3af1bb907a3e8e4&oe=5A4F1FF5",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21317614_510182819319692_1574990176623324803_n.jpg?oh=7a1f7bb79b3bd564d3af1bb907a3e8e4&oe=5A4F1FF5",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21317614_510182819319692_1574990176623324803_n.jpg?oh=7a1f7bb79b3bd564d3af1bb907a3e8e4&oe=5A4F1FF5",
            "https://scontent-iad3-1.xx.fbcdn.net/v/t1.0-9/21317614_510182819319692_1574990176623324803_n.jpg?oh=7a1f7bb79b3bd564d3af1bb907a3e8e4&oe=5A4F1FF5"};

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

        addImageButton = (Button) findViewById(R.id.addImage);

        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addImageToRow();
                Toast.makeText(MainActivity.this, "|Clicked", Toast.LENGTH_LONG).show();

            }
        });

        browser = (WebView) findViewById(R.id.browser);
        browser.setWebViewClient(new WebViewClient());
        browser.getSettings().setJavaScriptEnabled(true);
        browser.loadUrl(LINORADIOURL);


    }

    private void addImageToRow() {

        TableRow newRow = new TableRow(MainActivity.this);

        int n = 0;
        for (String link : imageURLs) {


            ImageView image = new ImageView(MainActivity.this);

            Picasso.with(MainActivity.this).load(link).fit().into(image);

            image.setImageResource(R.drawable.com_facebook_button_icon);

            newRow.addView(image);

            ViewGroup.LayoutParams layoutParams = image.getLayoutParams();
            layoutParams.width = 480;
            layoutParams.height = 480;
            image.setLayoutParams(layoutParams);

            n++;

            if (n == 2) {
                n = 0;

                imageTL.addView(newRow);

                newRow = new TableRow(MainActivity.this); //creaters fresh row


            }

        }


    }

    private void hideImages() {
        imageTL.setVisibility(View.GONE);
        addImageButton.setVisibility(View.GONE);
    }

    private void showImages() {
        imageTL.setVisibility(View.VISIBLE);
        addImageButton.setVisibility(View.VISIBLE);
        mTextMessage.setText("Images");
    }

    private void showLiveRadio() {
        mTextMessage.setText("Live Radio");
        browser.setVisibility(View.VISIBLE);
//        browser.loadUrl(LINORADIOURL);
        browser.loadUrl("https://www.google.com");
    }

    private void hideLiveRadio() {
        browser.setVisibility(View.GONE);
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
