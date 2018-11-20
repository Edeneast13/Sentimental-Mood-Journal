package edeneastapps.sentimentalmoodjournal;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DashboardActivity extends AppCompatActivity {

    @BindView(R.id.entry_recycler)
    RecyclerView mEntryRecycler;

    @BindView(R.id.entry_add_button)
    FloatingActionButton mEntryAddButton;

    @BindView(R.id.calender_recycler)
    RecyclerView mCalendarRecycler;

    @BindView(R.id.dashboard_toolbar)
    ConstraintLayout mToolbar;

    EntryViewModel mEntryViewModel;
    EntryAdapter mAdapter;
    HorizontalCalendarAdapter mCalendarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        initViewModel();
        initHorizontalCalendar();
        initAdapter();

        mToolbar.setElevation(8);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEntryViewModel.getAllEntries().observe(this, entries -> mAdapter.setData(entries));
    }

    void initViewModel(){
        mEntryViewModel = new EntryViewModel(getApplication());
    }

    void initAdapter(){
        mAdapter = new EntryAdapter(getApplicationContext());
        mEntryRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mEntryRecycler.setAdapter(mAdapter);
        mEntryViewModel.getAllEntries().observe(this, entries -> mAdapter.setData(entries));
    }

    void initHorizontalCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int currentMonth = calendar.get(Calendar.MONTH);
        HorizontalCalendarLayoutManager layoutManager = new HorizontalCalendarLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCalendarAdapter =
                new HorizontalCalendarAdapter(getApplicationContext(),
                        new HorizontalCalendarProperties(HorizontalCalendarUtils.calculateMonthLength(currentMonth), currentMonth, calendar.get(Calendar.YEAR)),
                        (view, dateStamp, position) -> {
                            updateEntries(dateStamp);
                            scrollToCenter(view, layoutManager);
                        });
        mCalendarRecycler.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mCalendarRecycler);
        int totalVisibleItems = layoutManager.findFirstVisibleItemPosition() - layoutManager.findLastVisibleItemPosition();
        int centeredItemPosition = totalVisibleItems / 2;
        mCalendarRecycler.smoothScrollToPosition(calendar.get(Calendar.DAY_OF_MONTH));
        mCalendarRecycler.setScrollY(centeredItemPosition);
        mCalendarRecycler.setAdapter(mCalendarAdapter);
    }

    private void scrollToCenter(View view, LinearLayoutManager layoutManager) {
        int itemToScroll = mCalendarRecycler.getChildPosition(view);
        int centerOfScreen = mCalendarRecycler.getWidth() / 2 - view.getWidth() / 2;
        layoutManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
    }

    void updateEntries(String dateStamp){
        mEntryViewModel.getByDateCreated(dateStamp).observe(this, entries -> mAdapter.setData(entries));
    }

    @OnClick(R.id.entry_add_button)
    void setEntryAddButton(){
        startActivity(new Intent(DashboardActivity.this, NewEntryActivity.class));
        overridePendingTransition(0, 0);
    }
}
