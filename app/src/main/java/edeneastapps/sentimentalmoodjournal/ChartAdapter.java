package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.charts.Cartesian;
import com.anychart.charts.Pie;
import com.anychart.core.Chart;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.itangqi.waveloadingview.WaveLoadingView;

public class ChartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ChartItem> mData;
    private Entry mEntry;
    private Context mContext;

    ChartAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 1){
            return new ChartAdapter.ChartViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chart_detail_item, viewGroup, false));
        }
        else{
            return new ChartAdapter.WaveViewHolder(LayoutInflater.from(mContext).inflate(R.layout.wave_detail_item, viewGroup, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()){
            case 1: {
                ChartViewHolder chartViewHolder = (ChartViewHolder) viewHolder;
                switch (mData.get(position).getType()){
                    case 0: {
                        //pie
                        Pie pie = AnyChart.pie();
                        pie.data(mData.get(position).getData());
                        chartViewHolder.mChart.setChart(pie);
                        break;
                    }
                    case 1: {
                        //column
                        Cartesian column = AnyChart.column();
                        column.data(mData.get(position).getData());
                        chartViewHolder.mChart.setChart(column);
                        column.animation(true);
                    }
                }
                chartViewHolder.mTitle.setText(mData.get(position).getTitle());
                Utils.configCardLayout(mContext, chartViewHolder.mLayout, R.color.cardBackground);
                break;
            }
            case 0: {
                float score = Float.parseFloat(mEntry.getSentimentScore());
                WaveViewHolder waveViewHolder = (WaveViewHolder) viewHolder;
                Utils.configCardLayout(mContext, waveViewHolder.mLayout, R.color.cardBackground);
                waveViewHolder.mTitle.setText("Sentiment Analysis");
                waveViewHolder.mWaveLoadingView.setCenterTitle("");
                waveViewHolder.mWaveLoadingView.setAnimDuration(4000);
                waveViewHolder.mWaveLoadingView.setBorderColor(
                        mContext.getResources().getColor(Utils.returnSentimentRangeColor(score)));
                waveViewHolder.mWaveLoadingView.setWaveColor(
                        mContext.getResources().getColor(Utils.returnSentimentRangeColor(score)));
                waveViewHolder.mWaveLoadingView.setProgressValue(Utils.calculateSentimentProgress(mEntry));
                if (score < 0){
                    waveViewHolder.mStatus.setText("Mostly Negative");
                }
                else if(score == 0){
                    waveViewHolder.mStatus.setText("Mostly Neutral");
                }
                else{
                    waveViewHolder.mStatus.setText("Mostly Positive");
                }
                break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        int viewType = 1; //Default is 1
        if (position == 0){
            viewType = 0;
        }
        return viewType;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    class ChartViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.chart_title)
        TextView mTitle;
        @BindView(R.id.chart)
        AnyChartView mChart;
        @BindView(R.id.chart_layout)
        ConstraintLayout mLayout;

        ChartViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class WaveViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.wave)
        WaveLoadingView mWaveLoadingView;
        @BindView(R.id.wave_layout)
        ConstraintLayout mLayout;
        @BindView(R.id.wave_title)
        TextView mTitle;
        @BindView(R.id.wave_status)
        TextView mStatus;

        WaveViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<ChartItem> data, Entry entry){
        this.mData = data;
        this.mEntry = entry;
    }
}
