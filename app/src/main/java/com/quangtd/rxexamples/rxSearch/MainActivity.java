package com.quangtd.rxexamples.rxSearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.widget.TextView;

import com.quangtd.rxexamples.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * QuangTD on 11/4/2017.
 */

public class MainActivity extends AppCompatActivity {
    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchView searchView = findViewById(R.id.searchView);
        TextView emptyView = findViewById(R.id.empty_view);
        RxSearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                /**
                 * loại bỏ những kí tự thừa (ví dụ như toàn là dấu cách hoặc dấu cách ở cuối) để đỡ phải tạo request.
                 */
                .filter(s -> !s.isEmpty())
                /**
                 * Chúng ta sử dụng operator này để tránh việc tạo các request trùng nhau.
                 * Ví dụ kết quả search hiện tại đang cho từ khoá "abc",
                 * nhưng người dùng nó lại xoá chữ c đi và lại gõ chữ c vào (trong khoảng thời gian 300ms)
                 * thì cuối cùng PublishSubject vẫn sẽ emit ra chuỗi "abc" mà thôi,
                 * cho nên chúng ta ko cần phải search lại làm gì cả
                 */
                .distinctUntilChanged()
                .switchMap(this::getDataFromQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(emptyView::setText);

    }

    private ObservableSource<String> getDataFromQuery(String s) {
        return observer -> observer.onNext(String.format("%s-%s-%s", s, s, s));
    }

}
