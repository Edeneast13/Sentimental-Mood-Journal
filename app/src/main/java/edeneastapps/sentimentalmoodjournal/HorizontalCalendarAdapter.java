package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalCalendarAdapter extends RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>{

    private List<HorizontalCalendarItem> mCalendarItems;
    private Context mContext;

    public HorizontalCalendarAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public HorizontalCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HorizontalCalendarAdapter.HorizontalCalendarViewHolder(LayoutInflater.from(mContext).inflate(R.layout.horizontal_calendar_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCalendarViewHolder holder, int i) {
        holder.mDay.setText(mCalendarItems.get(i).day);
        holder.mMonth.setText(mCalendarItems.get(i).month);
    }

    @Override
    public int getItemCount() {
        return mCalendarItems == null ? 0 : mCalendarItems.size();
    }

    class HorizontalCalendarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.calendar_day)
        TextView mDay;
        @BindView(R.id.calendar_month)
        TextView mMonth;
        HorizontalCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<HorizontalCalendarItem> items){
        this.mCalendarItems = items;
    }
}
