package com.example.mds;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CreateMemoActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_create_memo, menu);
        return true;
    }
}
