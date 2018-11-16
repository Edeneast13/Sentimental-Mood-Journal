package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalCalendarAdapter extends RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>{

    private Context mContext;
    private HorizontalCalendarProperties mProperties;
    private OnDaySelectedListener mOnDaySelectedListener;

    HorizontalCalendarAdapter(Context context, HorizontalCalendarProperties properties, OnDaySelectedListener callBack) {
        this.mContext = context;
        this.mProperties = properties;
        this.mOnDaySelectedListener = callBack;
    }

    @NonNull
    @Override
    public HorizontalCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HorizontalCalendarAdapter.HorizontalCalendarViewHolder(LayoutInflater.from(mContext).inflate(R.layout.horizontal_calendar_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCalendarViewHolder holder, int position) {
        holder.mDay.setText(String.valueOf(position + 1));
        holder.mMonth.setText(HorizontalCalendarUtils.returnMonthName(mProperties.currentMonth));
        holder.mItemLayout.setOnClickListener(view -> {
           mOnDaySelectedListener.OnDaySelected(view, String.valueOf(mProperties.currentMonth + "/" + (position + 1) + "/" + mProperties.currentYear), position);
        });
    }

    @Override
    public int getItemCount() {
        return mProperties.currentMonthLength;
    }

    class HorizontalCalendarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.calendar_day)
        TextView mDay;
        @BindView(R.id.calendar_month)
        TextView mMonth;
        @BindView(R.id.calendar_item)
        ConstraintLayout mItemLayout;
        @BindView(R.id.select_marker)
        ConstraintLayout mSelectMarker;
        HorizontalCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDaySelectedListener {
        void OnDaySelected(View view, String dateStamp, int position);
    }
}
