package com.quangtd.rxexamples.loadMoreRecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quangtd.rxexamples.R;

import java.util.ArrayList;
import java.util.List;

/**
 * QuangTD on 11/4/2017.
 */

public class MainActivity extends AppCompatActivity {
    private TextView tvEmptyView;
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private LinearLayoutManager mLayoutManager;

    private List<Student> studentList;


    protected Handler handler;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvEmptyView = findViewById(R.id.empty_view);
        mRecyclerView = findViewById(R.id.my_recycler_view);
        studentList = new ArrayList<>();
        handler = new Handler();

        loadData();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new DataAdapter(studentList, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
        if (studentList.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            tvEmptyView.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            tvEmptyView.setVisibility(View.GONE);
        }
        mAdapter.setOnLoadMoreListener(() -> {
            studentList.add(null);
            mAdapter.notifyItemInserted(studentList.size() - 1);
            handler.postDelayed(() -> {
                studentList.remove(studentList.size() - 1);
                mAdapter.notifyItemRemoved(studentList.size());
                int start = studentList.size();
                int end = start + 20;
                for (int i = start + 1; i <= end; i++) {
                    studentList.add(new Student("Student " + i, "student_" + i + "@gmail.com"));
                    mAdapter.notifyItemInserted(studentList.size());
                }
                mAdapter.setLoaded();
            }, 2000);
        });
    }


    private void loadData() {
        for (int i = 1; i <= 20; i++) {
            studentList.add(new Student("Student " + i, "student:i" + i + "@gmail.com"));
        }
    }

}
