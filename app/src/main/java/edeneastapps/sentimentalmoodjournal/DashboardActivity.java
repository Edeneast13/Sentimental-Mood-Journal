package edeneastapps.sentimentalmoodjournal;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;

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

    @BindView(R.id.menu_recycler)
    RecyclerView mMenuRecycler;

    @BindView(R.id.menu_layout)
    ConstraintLayout mMenuLayout;

    @BindView(R.id.calendar_full)
    CalendarView mCalendarFull;

    @BindView(R.id.calendar_layout)
    FrameLayout mCalendarLayout;

    @BindView(R.id.empty_layout)
    ConstraintLayout mEmptyLayout;

    EntryViewModel mEntryViewModel;
    EntryAdapter mEntryAdapter;
    HorizontalCalendarAdapter mCalendarAdapter;
    HorizontalCalendarProperties mCalendarProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        initViewModel();
        initHorizontalCalendar();
        initAdapter();
        initMenu();
        initFullCalendar();

        mToolbar.setElevation(8);
        mMenuLayout.setElevation(12);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mEntryViewModel.getAllEntries().observe(this, entries -> mEntryAdapter.setData(entries));
    }

    void initViewModel(){
        mEntryViewModel = new EntryViewModel(getApplication());
    }

    void initAdapter(){
        mEntryAdapter = new EntryAdapter(getApplicationContext());
        mEntryRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mEntryRecycler.setAdapter(mEntryAdapter);
        mEntryViewModel.getAllEntries().observe(this, entries ->
                mEntryAdapter.setData(entries));
    }

    void initMenu(){
        List<MenuItem> items = new ArrayList<>();
        items.add(new MenuItem("Statistics", R.color.colorAccent, R.color.primaryText));
        items.add(new MenuItem("Settings", R.color.colorAccent, R.color.primaryText));
        MenuAdapter adapter = new MenuAdapter(this, items);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mMenuRecycler.setLayoutManager(layoutManager);
        mMenuRecycler.setAdapter(adapter);
    }

//    void initHorizontalCalendar(){
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(new Date());
//        int currentMonth = calendar.get(Calendar.MONTH);
//        HorizontalCalendarLayoutManager layoutManager = new HorizontalCalendarLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
//        mCalendarAdapter =
//                new HorizontalCalendarAdapter(getApplicationContext(),
//                        new HorizontalCalendarProperties(HorizontalCalendarUtils.calculateMonthLength(currentMonth), currentMonth, calendar.get(Calendar.YEAR)),
//                        (view, dateStamp, position) -> {
//                            updateEntries(dateStamp);
//                            scrollToCenter(view, layoutManager);
//                        });
//        mCalendarRecycler.setLayoutManager(layoutManager);
//        SnapHelper snapHelper = new LinearSnapHelper();
//        snapHelper.attachToRecyclerView(mCalendarRecycler);
//        int totalVisibleItems = layoutManager.findFirstVisibleItemPosition() - layoutManager.findLastVisibleItemPosition();
//        int centeredItemPosition = totalVisibleItems / 2;
//        mCalendarRecycler.smoothScrollToPosition(calendar.get(Calendar.DAY_OF_MONTH));
//        mCalendarRecycler.setScrollY(centeredItemPosition);
//        mCalendarRecycler.setAdapter(mCalendarAdapter);
//    }

    void initHorizontalCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        setCalenderProperties(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        HorizontalCalendarAdapter.OnEndReachedListener onEndReachedListener = new HorizontalCalendarAdapter.OnEndReachedListener() {
            @Override
            public void onEndReached() {
                int monthAtEnd = getCalendarProperties().getMonthAtEnd();
                if(monthAtEnd == 0){
                    getCalendarProperties().setMonthAtEnd(11);
                }
                else{
                    getCalendarProperties().setMonthAtEnd(monthAtEnd - 1);
                }
                Log.i("HC END:: ", getCalendarProperties().getMonthAtEnd() + "");
                mCalendarAdapter.addItemsAtTop(getCalendarItems(getCalendarProperties().getMonthAtEnd(), getCalendarProperties().getCurrentYear()));
            }

            @Override
            public void onStartReached() {
                int monthAtStart = getCalendarProperties().getMonthAtStart();
                if(monthAtStart == 11){
                    getCalendarProperties().setMonthAtStart(0);
                }
                else{
                    getCalendarProperties().setMonthAtStart(monthAtStart + 1);
                }
                Log.i("HC START:: ", getCalendarProperties().getMonthAtStart() + "");
                mCalendarAdapter.addItemsAtBottom(getCalendarItems(getCalendarProperties().getMonthAtStart(), getCalendarProperties().getCurrentYear()));
            }
        };
        HorizontalCalendarAdapter.OnDaySelectedListener onDaySelectedListener = new HorizontalCalendarAdapter.OnDaySelectedListener() {
            @Override
            public void OnDaySelected(View view, String date, int position) {

            }
        };
        HorizontalCalendarLayoutManager layoutManager = new HorizontalCalendarLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mCalendarAdapter = new HorizontalCalendarAdapter(this, onDaySelectedListener, onEndReachedListener);
        mCalendarRecycler.setLayoutManager(layoutManager);
        mCalendarRecycler.setAdapter(mCalendarAdapter);
        mCalendarAdapter.setData(getCalendarItems(getCalendarProperties().getCurrentMonth(), getCalendarProperties().getCurrentYear()));
    }

    void setCalenderProperties(int currentMonth, int currentYear){
        mCalendarProperties = new HorizontalCalendarProperties(currentMonth, currentYear);
    }

    HorizontalCalendarProperties getCalendarProperties(){
        return mCalendarProperties;
    }
    /**
     * returns a list of calendar items for the given date and year
     * @param month
     * @param year
     * @return
     */
    List<HorizontalCalendarItem> getCalendarItems(int month, int year){
        List<HorizontalCalendarItem> items = new ArrayList<>();
        for (int day = 0; day < HorizontalCalendarUtils.calculateMonthLength(month); day++) {
            items.add(new HorizontalCalendarItem(day + 1, month, R.color.colorPrimary, year));
        }
        return items;
    }

    void initFullCalendar(){
        mCalendarFull.setOnDateChangeListener((calendarView, year, month, day) -> {
            String selectedDate = Utils.returnStringDate(month, day, year);
            updateEntries(selectedDate);
            mCalendarRecycler.smoothScrollToPosition(day);
        });
    }

    private void scrollToCenter(View view, LinearLayoutManager layoutManager) {
        int itemToScroll = mCalendarRecycler.getChildPosition(view);
        int centerOfScreen = mCalendarRecycler.getWidth() / 2 - view.getWidth() / 2;
        layoutManager.scrollToPositionWithOffset(itemToScroll, centerOfScreen);
    }

    void updateEntries(String dateStamp){
        mEntryViewModel.getByDateCreated(dateStamp).observe(this, entries -> {
            mEmptyLayout.setVisibility(View.GONE);
            mEntryAdapter.setData(entries);
            if (entries.isEmpty()){
                mEmptyLayout.setVisibility(View.VISIBLE);
            }
        });
    }

    @OnClick(R.id.entry_add_button)
    void setEntryAddButton(){
        startActivity(new Intent(DashboardActivity.this, NewEntryActivity.class));
        overridePendingTransition(0, 0);
    }

    @OnClick(R.id.menu_button)
    void setMenuButtonListener(){
        Utils.toggleViewVisibility(mMenuLayout);
    }

    @OnClick(R.id.menu_close_button)
    void setMenuCloseButtonListener(){
        Utils.toggleViewVisibility(mMenuLayout);
    }

    @OnClick(R.id.calendar_button)
    void setCalendarFullButton(){
        Utils.toggleViewVisibility(mCalendarRecycler);
        Utils.toggleViewVisibility(mCalendarLayout);
    }
}
