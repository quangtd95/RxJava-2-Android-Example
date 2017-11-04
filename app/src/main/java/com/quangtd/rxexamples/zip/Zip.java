package com.quangtd.rxexamples.zip;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.NewThreadScheduler;

/**
 * QuangTD on 11/4/2017.
 */

public class Zip {
    class ZipObject {
        int number;
        String alphabet;
    }

    public Zip() {
        Observable<Integer> observable1 = Observable.fromArray(1, 2, 3, 4, 5, 6);
        Observable<String> observable2 = Observable.fromArray("A", "B", "C", "D", "E");
        Observable<ZipObject> observable3 = Observable
                .zip(observable1, observable2, (integer, s) -> {
                    ZipObject zipObject = new ZipObject();
                    zipObject.alphabet = s;
                    zipObject.number = integer;
                    return zipObject;
                });
        observable3.subscribeOn(new NewThreadScheduler())
                .observeOn(new NewThreadScheduler())
                .subscribe(new Observer<ZipObject>() {
                    @Override public void onSubscribe(Disposable d) {

                    }

                    @Override public void onNext(ZipObject zipObject) {
                        Log.e("TAGG", "Output:" + zipObject.number + " " + zipObject.alphabet);
                    }

                    @Override public void onError(Throwable e) {

                    }

                    @Override public void onComplete() {

                    }
                });
    }
}
