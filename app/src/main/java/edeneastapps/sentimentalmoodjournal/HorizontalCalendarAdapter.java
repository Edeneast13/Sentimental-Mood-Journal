package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalCalendarAdapter extends RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>{

    private Context mContext;
    private int mCurrentMonthLength;
    private int mCurrentMonth;
    private int mCurrentYear;
    private OnDaySelectedCallBack mOnDaySelectedCallBack;

    HorizontalCalendarAdapter(Context context, int currentMonthLength, int currentMonth, int currentYear, OnDaySelectedCallBack callBack) {
        this.mContext = context;
        this.mCurrentMonthLength = currentMonthLength;
        this.mCurrentMonth = currentMonth;
        this.mCurrentYear = currentYear;
        this.mOnDaySelectedCallBack = callBack;
    }

    @NonNull
    @Override
    public HorizontalCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HorizontalCalendarAdapter.HorizontalCalendarViewHolder(LayoutInflater.from(mContext).inflate(R.layout.horizontal_calendar_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCalendarViewHolder holder, int i) {
        holder.mDay.setText(String.valueOf(i + 1));
        holder.mMonth.setText(HorizontalCalendarUtils.returnMonthName(mCurrentMonth));
        holder.mItemLayout.setOnClickListener(view -> {
           mOnDaySelectedCallBack.OnDaySelected(String.valueOf(mCurrentMonth + "/" + (i + 1) + "/" + mCurrentYear));
        });
    }

    @Override
    public int getItemCount() {
        return mCurrentMonthLength;
    }

    class HorizontalCalendarViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.calendar_day)
        TextView mDay;
        @BindView(R.id.calendar_month)
        TextView mMonth;
        @BindView(R.id.calendar_item)
        ConstraintLayout mItemLayout;
        HorizontalCalendarViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnDaySelectedCallBack{
        void OnDaySelected(String dateStamp);
    }
}
