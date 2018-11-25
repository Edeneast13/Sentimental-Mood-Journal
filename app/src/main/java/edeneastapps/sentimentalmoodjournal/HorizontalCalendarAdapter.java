package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalCalendarAdapter extends RecyclerView.Adapter<HorizontalCalendarAdapter.HorizontalCalendarViewHolder>{

    private Context mContext;
    private HorizontalCalendarAdapter.OnDaySelectedListener mOnDaySelectedListener;
    private HorizontalCalendarAdapter.OnEndReachedListener mOnEndReachedListener;
    private int bottomAdvanceCallback = 0;
    private boolean mIsFirstBind = true;
    private List<HorizontalCalendarItem> mData;

    HorizontalCalendarAdapter(Context context, HorizontalCalendarAdapter.OnDaySelectedListener onDaySelectedListener, OnEndReachedListener onEndReachedListener) {
        this.mContext = context;
        this.mOnDaySelectedListener = onDaySelectedListener;
        this.mOnEndReachedListener = onEndReachedListener;
    }

    @NonNull
    @Override
    public HorizontalCalendarAdapter.HorizontalCalendarViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new HorizontalCalendarAdapter.HorizontalCalendarViewHolder(LayoutInflater.from(mContext).inflate(R.layout.horizontal_calendar_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalCalendarAdapter.HorizontalCalendarViewHolder holder, int position) {

        if (position == 0 && !mIsFirstBind) {
            notifyEndReached();
        }
        else if ((position + bottomAdvanceCallback) >= (getItemCount() - 1)) {
            notifyStartReached();
        }

        mIsFirstBind = false;

        holder.mDay.setText(String.valueOf(mData.get(position).getDay()));
        holder.mMonth.setText(HorizontalCalendarUtils.returnMonthName(mData.get(position).getMonth()));
        holder.mItemLayout.setOnClickListener(view -> {
            mOnDaySelectedListener.OnDaySelected(
                    view,
                    Utils.returnStringDate(mData.get(position).getMonth(), mData.get(position).getDay(), mData.get(position).getYear()),
                    position);
        });
        holder.mItemLayout.setBackgroundColor(mContext.getResources().getColor(mData.get(position).getBackgroundColor()));
    }

    public interface OnDaySelectedListener {
        void OnDaySelected(View view, String date, int position);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
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

    public void addItemsAtBottom(List<HorizontalCalendarItem> bottomList) {
        if (mData == null) {
            throw new NullPointerException("Data container is `null`. Are you missing a call to setDataContainer()?");
        }

        if (bottomList == null || bottomList.isEmpty()) {
            return;
        }

        int adapterSize = getItemCount();
        mData.addAll(adapterSize, bottomList);
        notifyItemRangeInserted(adapterSize, adapterSize + bottomList.size());
    }

    public void addItemsAtTop(List<HorizontalCalendarItem> topList) {
        if (mData == null) {
            throw new NullPointerException("Data container is `null`. Are you missing a call to setDataContainer()?");
        }

        if (topList == null || topList.isEmpty()) {
            return;
        }

        Collections.reverse(topList);
        mData.addAll(0, topList);
        notifyItemRangeInserted(0, topList.size());
    }

    public void setBottomAdvanceCallback(int bottomAdvance) {
        if (bottomAdvance < 0) {
            throw new IndexOutOfBoundsException("Invalid index, bottom index must be greater than 0");
        }
        bottomAdvanceCallback = bottomAdvance;
    }

    public void setData(List<HorizontalCalendarItem> data) {
        this.mData = data;
    }

    private void notifyEndReached() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (mOnEndReachedListener != null) {
                mOnEndReachedListener.onEndReached();
            }
        }, 50);
    }

    private void notifyStartReached() {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(() -> {
            if (mOnEndReachedListener != null) {
                mOnEndReachedListener.onStartReached();
            }
        }, 50);
    }

    public interface OnEndReachedListener {
        void onEndReached();
        void onStartReached();
    }
}
