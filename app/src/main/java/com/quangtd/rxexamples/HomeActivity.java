package com.quangtd.rxexamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.quangtd.rxexamples.concat2.ConcatStream2;
import com.quangtd.rxexamples.loadMoreRecyclerView.MainActivity;
import com.quangtd.rxexamples.zip.Zip;

/**
 * QuangTD on 11/4/2017.
 */

public class HomeActivity extends AppCompatActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void concat2(View view) {
        new ConcatStream2();
        Toast.makeText(this, "see result in console log!", Toast.LENGTH_SHORT).show();
    }

    public void loadMoreRecyclerView(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void concatStream(View view) {
        startActivity(new Intent(this, com.quangtd.rxexamples.concatStream.MainActivity.class));
    }

    public void rxSearch(View view) {
        startActivity(new Intent(this, com.quangtd.rxexamples.rxSearch.MainActivity.class));
    }

    public void zip(View view) {
        new Zip();
        Toast.makeText(this, "see result in console log!", Toast.LENGTH_SHORT).show();
    }

    public void zip_merge(View view) {
    }
}
