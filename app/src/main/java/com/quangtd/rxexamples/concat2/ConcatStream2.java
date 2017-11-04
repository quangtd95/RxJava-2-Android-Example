package com.quangtd.rxexamples.concat2;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.IoScheduler;

/**
 * QuangTD on 11/4/2017.
 */

public class ConcatStream2 {
    public ConcatStream2() {
        Observable<Integer> observable1 = Observable.fromArray(1, 2, 3, 4, 5);
        Observable<Integer> observable2 = Observable.fromArray(6, 7, 8, 9, 10);
//        Observable.concat(observable1, observable2)
        Observable.merge(observable1, observable2)
                .subscribeOn(new IoScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override public void onSubscribe(Disposable d) {

                    }

                    @Override public void onNext(Integer integer) {
                        Log.e("TAGG",integer+"");
                    }

                    @Override public void onError(Throwable e) {

                    }

                    @Override public void onComplete() {

                    }
                });
    }
}
