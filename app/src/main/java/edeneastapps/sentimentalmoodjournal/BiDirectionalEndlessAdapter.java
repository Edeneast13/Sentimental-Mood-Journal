package edeneastapps.sentimentalmoodjournal;

import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BiDirectionalEndlessAdapter<VH extends RecyclerView.ViewHolder, DataItem> extends RecyclerView.Adapter<VH> {

    protected List<DataItem> data;
    private Callback mEndlessCallback = null;
    private int bottomAdvanceCallback = 0;
    private boolean mIsFirstBind = true;

    public void setEndlessCallback(Callback callback) {
        mEndlessCallback = callback;
    }

    public void addItemsAtBottom(ArrayList<DataItem> bottomList) {
        if (data == null) {
            throw new NullPointerException("Data container is `null`. Are you missing a call to setDataContainer()?");
        }

        if (bottomList == null || bottomList.isEmpty()) {
            return;
        }

        int adapterSize = getItemCount();

        data.addAll(adapterSize, bottomList);

        notifyItemRangeInserted(adapterSize, adapterSize + bottomList.size());
    }

    public void addItemsAtTop(ArrayList<DataItem> topList) {
        if (data == null) {
            throw new NullPointerException("Data container is `null`. Are you missing a call to setDataContainer()?");
        }

        if (topList == null || topList.isEmpty()) {
            return;
        }

        Collections.reverse(topList);
        data.addAll(0, topList);

        notifyItemRangeInserted(0, topList.size());
    }

    public void setBottomAdvanceCallback(int bottomAdvance) {
        if (bottomAdvance < 0) {
            throw new IndexOutOfBoundsException("Invalid index, bottom index must be greater than 0");
        }

        bottomAdvanceCallback = bottomAdvance;
    }

    public void setDataContainer(List<DataItem> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        onBindViewHolder(holder, data.get(position), position);

        if (position == 0 && !mIsFirstBind) {
            notifyTopReached();
        }
        else if ((position + bottomAdvanceCallback) >= (getItemCount() - 1)) {
            notifyBottomReached();
        }

        mIsFirstBind = false;
    }

    abstract void onBindViewHolder(VH holder, DataItem data, int position);

    protected void notifyTopReached() {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> {
            if (mEndlessCallback != null) {
                mEndlessCallback.onTopReached();
            }
        }, 50);

    }

    protected void notifyBottomReached() {
        Handler handler = new Handler(Looper.getMainLooper());

        handler.postDelayed(() -> {
            if (mEndlessCallback != null) {
                mEndlessCallback.onBottomReached();
            }
        }, 50);
    }

    public interface Callback {
        void onTopReached();
        void onBottomReached();
    }
}
