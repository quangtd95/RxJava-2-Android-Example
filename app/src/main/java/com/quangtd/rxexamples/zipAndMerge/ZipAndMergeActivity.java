package com.quangtd.rxexamples.zipAndMerge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.quangtd.rxexamples.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * QuangTD on 11/4/2017.
 */

public class ZipAndMergeActivity extends AppCompatActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip_merge);
    }

    public void zip(View view) {
        Observable.zip(createObservable(1), createObservable(2), createObservable(3), DataZip::new)
                .subscribe(zip -> ((TextView) findViewById(R.id.txt_zip)).setText(String.format(Locale.getDefault(), "%d %d %d", zip.numberOne, zip.numberTwo, zip.numberThree)));
    }

    public void zipList(View view) {
        Observable.zip(createListObservable(), objects -> new DataZip((int) objects[0], (int) objects[1], (int) objects[2]))
                .subscribe(zip -> ((TextView) findViewById(R.id.txt_zip_list)).setText(String.format(Locale.getDefault(), "%d %d %d", zip.numberOne, zip.numberTwo, zip.numberThree)));
    }

    public void merge(View view) {
        Observable.merge(createObservable(1), createObservable(2), createObservable(3))
                .subscribe(new Observer<Integer>() {
                    @Override public void onSubscribe(Disposable d) {

                    }

                    @Override public void onNext(Integer integer) {
                        ((TextView) findViewById(R.id.txt_merge)).setText(integer + "");
                    }

                    @Override public void onError(Throwable e) {

                    }

                    @Override public void onComplete() {

                    }
                });
    }

    public void mergeList(View view) {
    }

    private Observable<Integer> createObservable(int data) {
        return Observable.just(data);
    }

    private List<Observable<?>> createListObservable() {
        List<Observable<?>> result = new ArrayList<>();
        result.add(createObservable(1));
        result.add(createObservable(2));
        result.add(createObservable(3));
        return result;
    }

    class DataZip {
        int numberOne;
        int numberTwo;
        int numberThree;

        DataZip(int numberOne, int numberTwo, int numberThree) {
            this.numberOne = numberOne;
            this.numberTwo = numberTwo;
            this.numberThree = numberThree;
        }
    }
}
