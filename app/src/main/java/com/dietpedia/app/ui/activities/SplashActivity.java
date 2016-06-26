package com.dietpedia.app.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import hugo.weaving.DebugLog;

/**
 * Created by ccavusoglu on 30.03.2016.
 */
public class SplashActivity extends BaseActivity {
    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
