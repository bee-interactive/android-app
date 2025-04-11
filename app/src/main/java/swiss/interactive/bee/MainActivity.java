package swiss.interactive.bee;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import androidx.annotation.Nullable;

public class MainActivity extends Activity {

    private WebView mWebView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize SwipeRefreshLayout
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mWebView = findViewById(R.id.activity_main_webview);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                swipeRefreshLayout.setRefreshing(false); // Stop refresh animation when page loads
                super.onPageFinished(view, url);
            }
        });

        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // Load the website
        mWebView.loadUrl("https://bee-interactive.ch/open-source/ios-android"); // 👈 define your url here

        // Handle SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener(() -> mWebView.reload());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
