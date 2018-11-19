package edeneastapps.sentimentalmoodjournal;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.cartesian.series.Column;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryDetailActivity extends AppCompatActivity {

    @BindView(R.id.chart_recycler)
    RecyclerView mChartRecycler;

    @BindView(R.id.entry_detail_toolbar)
    ConstraintLayout mToolbar;

    @BindView(R.id.entry_detail_content)
    TextView mEntryContent;

    @BindView(R.id.entry_detail_emotion)
    ImageView mEntryEmotion;

    @BindView(R.id.entry_detail_layout)
    ConstraintLayout mEntryLayout;

    @BindView(R.id.entry_detail_time)
    TextView mEntryTime;

    @BindView(R.id.entry_detail_title)
    TextView mEntryTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_detail);

        ButterKnife.bind(this);
        initAdapter();
        mToolbar.setElevation(8);

        setEntry(getEntry());
    }

    void initAdapter(){
        ChartAdapter chartAdapter = new ChartAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mChartRecycler.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mChartRecycler);
        mChartRecycler.setAdapter(chartAdapter);
        chartAdapter.setData(getMockChartData());
    }

    List<ChartItem> getMockChartData(){
        List<ChartItem> data = new ArrayList<>();
        data.add(new ChartItem(0, getMockPieChartData(), "Sentiment Analysis"));
        data.add(new ChartItem(1, getMockColumnChartData(), "Emotional Analysis"));
        return data;
    }

    List<DataEntry> getMockPieChartData(){
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Positive", 10000));
        data.add(new ValueDataEntry("Neutral", 12000));
        data.add(new ValueDataEntry("Negative", 18000));
        return data;
    }

    List<DataEntry> getMockColumnChartData(){
        List<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("Anger", 10000));
        data.add(new ValueDataEntry("Calmness", 11000));
        data.add(new ValueDataEntry("Fear", 12000));
        data.add(new ValueDataEntry("Happiness", 13000));
        data.add(new ValueDataEntry("Liking", 14000));
        data.add(new ValueDataEntry("Shame", 13000));
        data.add(new ValueDataEntry("Certainty", 12000));
        data.add(new ValueDataEntry("Surprise", 11000));
        return data;
    }

    Entry getEntry(){
        return getIntent().getParcelableExtra("entry");
    }

    void setEntry(Entry entry){
        mEntryContent.setText(entry.getContent());
        mEntryTime.setText(entry.getTimestamp());
        mEntryTitle.setText(entry.getTitle());
        mEntryEmotion.setImageResource(Utils.returnMoodIconWhite(entry.getMood()));
        Utils.configCardLayout(this, mEntryLayout, entry.getSentimentColor());
    }

    @OnClick(R.id.entry_detail_back_button)
    void setBackButtonListener(){
        finish();
    }
}
