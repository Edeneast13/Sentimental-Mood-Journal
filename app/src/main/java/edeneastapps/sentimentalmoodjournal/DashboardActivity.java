package edeneastapps.sentimentalmoodjournal;

import android.content.Intent;
import android.graphics.Rect;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;

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

    @BindView(R.id.bmb)
    BoomMenuButton mBoomMenuButton;

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
        initBmB(getMenuItems());

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
        mEntryAdapter = new EntryAdapter(getApplicationContext(), entry -> {
            Intent intent = new Intent(DashboardActivity.this, EntryDetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
            overridePendingTransition(0, 0);
        });
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

    void initBmB(List<Menu> menuItems){
        for (int i = 0; i < mBoomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(menuItems.get(i).getDrawable())
                    .normalTextRes(menuItems.get(i).getTitle())
                    .subNormalTextRes(menuItems.get(i).getSubText())
                    .normalColor(getResources().getColor(getMenuItems().get(i).getColor()))
                    .imagePadding(new Rect(32, 32, 32, 32))
                    .listener(index -> {
                        switch (index){
                            case 0:{
                                startAddEntryActivity();
                            }
                        }
                    });
            mBoomMenuButton.addBuilder(builder);
        }

    }

    List<Menu> getMenuItems(){
        List<Menu> items = new ArrayList<>();
        items.add(new Menu(R.string.menu_add_title, R.string.menu_add_subText, R.mipmap.ic_journal_white, R.color.colorPrimary));
        items.add(new Menu(R.string.menu_stats_title, R.string.menu_stats_subText, R.mipmap.ic_graph_white, R.color.colorPrimary));
        items.add(new Menu(R.string.menu_rate_title, R.string.menu_rate_subText, R.mipmap.ic_ribbon_white, R.color.colorPrimary));
        items.add(new Menu(R.string.menu_settings_title, R.string.menu_settings_subText, R.mipmap.ic_settings_white, R.color.colorPrimary));
        return items;
    }

    void initHorizontalCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        setCalenderProperties(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR));
        HorizontalCalendarLayoutManager layoutManager = new HorizontalCalendarLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
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
                mCalendarAdapter.addItemsAtBottom(getCalendarItems(getCalendarProperties().getMonthAtStart(), getCalendarProperties().getCurrentYear()));
            }
        };
        HorizontalCalendarAdapter.OnDaySelectedListener onDaySelectedListener = new HorizontalCalendarAdapter.OnDaySelectedListener() {
            @Override
            public void OnDaySelected(View view, String date, int position) {
                updateEntries(date);
                scrollToCenter(view, layoutManager);
            }
        };
        mCalendarAdapter = new HorizontalCalendarAdapter(this, onDaySelectedListener, onEndReachedListener);
        mCalendarRecycler.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mCalendarRecycler);
        mCalendarRecycler.smoothScrollToPosition(calendar.get(Calendar.DAY_OF_MONTH));
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

//    @OnClick(R.id.entry_add_button)
//    void setEntryAddButton(){
//        startActivity(new Intent(DashboardActivity.this, NewEntryActivity.class));
//        overridePendingTransition(0, 0);
//    }

    void startAddEntryActivity(){
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
