package com.kontenkeren.easytravel.easytravel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Djoko Priyono on 14/02/2016. djoko@maxmobilemedia.com
 */
public class MainActivity extends Activity {

    WebView main1WebView = null;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //must be called before adding content!
        this.getWindow().requestFeature(Window.FEATURE_PROGRESS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

        main1WebView = (WebView) findViewById(R.id.main1WebView);

        String suburl = "http://www.kontenkeren.com/easytravel/index.php?loc=view";
        main1WebView.loadUrl(suburl);

        main1WebView.setWebViewClient(new MainWebViewClient());

        getWindow().setFeatureInt(Window.FEATURE_PROGRESS,
                Window.PROGRESS_VISIBILITY_ON);

        main1WebView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                MainActivity.this.setTitle("Loading...");
                MainActivity.this.setProgress(progress * 100);
                if (progress == 100){
                    MainActivity.this.setTitle(view.getTitle());
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        if(main1WebView.canGoBack())
            main1WebView.goBack();
        else
            super.onBackPressed();
    }
    }
    class MainWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.i("Log", "loading: " + url);

            view.loadUrl(url);
            return true;
        }
    }
