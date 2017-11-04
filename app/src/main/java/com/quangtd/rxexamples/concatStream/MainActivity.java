package com.quangtd.rxexamples.concatStream;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.quangtd.rxexamples.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.schedulers.NewThreadScheduler;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * QuangTD on 11/4/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        demoConcat();
    }

    private void demoConcat() {
        List<Integer> data = new ArrayList<>();
        Flowable.concat(Flowable.just(getDataLocal(), getDataServer()))
                .subscribeOn(new NewThreadScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSubscriber<List<Integer>>() {
                    @Override public void onNext(List<Integer> integers) {
                        data.addAll(integers);
                    }

                    @Override public void onError(Throwable t) {
                        Log.e("TAGG", t.getLocalizedMessage(), t);
                    }

                    @Override public void onComplete() {
                        for (Integer o : data) {
                            Log.e("TAGG", "onComplete --------------------: " + o);
                        }
                        Toast.makeText(MainActivity.this, "complete", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private Flowable<List<Integer>> getDataLocal() {
        List<Integer> data = new ArrayList<>();
        data.add(1);
        data.add(2);
        data.add(3);
        return Flowable.just(data);
    }

    private Flowable<List<Integer>> getDataServer() {
        List<Integer> data = new ArrayList<>();
        data.add(3);
        data.add(4);
        data.add(5);
        return Flowable.just(data);
    }

}
