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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChartAdapter extends RecyclerView.Adapter<ChartAdapter.ChartViewHolder>{

    private List<ChartItem> mData;
    private Context mContext;

    ChartAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ChartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ChartAdapter.ChartViewHolder(LayoutInflater.from(mContext).inflate(R.layout.chart_detail_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChartViewHolder chartViewHolder, int position) {
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
            }
        }
        chartViewHolder.mTitle.setText(mData.get(position).getTitle());
        Utils.configCardLayout(mContext, chartViewHolder.mLayout, R.color.cardBackground);
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

    public void setData(List<ChartItem> data){
        this.mData = data;
    }
}
