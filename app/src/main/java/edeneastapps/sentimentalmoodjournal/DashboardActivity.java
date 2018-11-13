package edeneastapps.sentimentalmoodjournal;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    EntryViewModel mEntryViewModel;
    EntryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ButterKnife.bind(this);
        initViewModel();
        initAdapter();
        initHorizontalCalendar();
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
        HorizontalCalendarAdapter adapter =
                new HorizontalCalendarAdapter(getApplicationContext(),
                        HorizontalCalendarUtils.calculateMonthLength(currentMonth),
                        currentMonth,
                        calendar.get(Calendar.YEAR),
                        dateStamp -> {
                            updateEntries(dateStamp);
                        });
        mCalendarRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mCalendarRecycler.setAdapter(adapter);
    }

    void updateEntries(String dateStamp){
        mEntryViewModel.getByDateCreated(dateStamp).observe(this, entries -> mAdapter.setData(entries));
    }

    void addTestEntry(){
        Entry entry = new Entry();
        entry.setTitle("Entry Title");
        entry.setContent("Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. ");
        entry.setTimestamp("9:00 pm");
        mEntryViewModel.addEntry(entry);
    }

    @OnClick(R.id.entry_add_button)
    void setEntryAddButton(){
        startActivity(new Intent(DashboardActivity.this, NewEntryActivity.class));
        overridePendingTransition(0, 0);
    }
}
