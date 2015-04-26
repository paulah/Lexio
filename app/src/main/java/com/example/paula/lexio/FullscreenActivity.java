package com.example.paula.lexio;

import com.example.paula.lexio.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.view.*;

import java.io.IOException;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;

    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    //Added by me
    private WebView webView, webView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fullscreen);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //final View controlsView = findViewById(R.id.fullscreen_content_controls);
        //final View contentView = findViewById(R.id.fullscreen_content);

        webView = (WebView) findViewById(R.id.webView);
        webView2 = (WebView) findViewById(R.id.webView2);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView2.getSettings().setBuiltInZoomControls(true);
        webView2.getSettings().setDisplayZoomControls(false);
        String customHtml = "<div style='background-color: #FFFFF0'>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>READ</strong>&nbsp;(2-3 times)</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>1. Read the scripture slowly.</strong>&nbsp;Watch for a key phrase or word that jumps out at you or promises to have special meaning for you.</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\">It is better to dwell profoundly on one word or phrase than to skim the surface of several chapters. Read with your own life and choices in mind.</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>REFLECT</strong></span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>2. Reflect on a word or phrase.</strong> Let the special word or phrase that you discovered in the first phrase sink into your heart. Bring mind, will, and emotions to the task. Be like Mary, Jesus' mother, who heard of the angel's announcement and \"treasured\" and \"pondered\" what she had heard (Luke 2:19)</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>RESPOND</strong></span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>3. Respond to what you have read.</strong> Form a prayer that expresses your response to the idea, then \"pray it back to God.\" What you have read is woven through what you tell God.</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>REST</strong> (\"being\")</span></p>\n" +
                "<p><span style=\"font-family: georgia, palatino;\"><strong>4. Rest in God's word.</strong> Let the text soak into your deepest being, savoring an encounter with God and truth. When ready, move toward the moment in which you can ask God to show you how to live out what you have experienced.</span></p>\n" +
                "</div>";
        webView.loadData(customHtml, "text/html", "UTF-8");
        String gospel = "test";
        try {
            gospel = SiteScrape.getGospel();
        } catch(IOException e) {
            e.printStackTrace();
        }
        webView2.loadData("<div style='font-family: georgia; background-color: #FFFFC2'>" + gospel  + "</div>", "text/html", "UTF-8");
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }
}
