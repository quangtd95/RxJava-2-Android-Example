package com.quangtd.rxexamples.rxSearch;

import android.support.v7.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;


/**
 * QuangTD on 11/4/2017.
 */

public class RxSearchObservable {
    public static Observable<String> fromView(SearchView searchView) {
        PublishSubject<String> subject = PublishSubject.create();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return true;
            }
        });
        return subject;
    }
}
