package edeneastapps.sentimentalmoodjournal.views.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edeneastapps.sentimentalmoodjournal.R;
import edeneastapps.sentimentalmoodjournal.database.entry.Entry;
import edeneastapps.sentimentalmoodjournal.utils.Utils;

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private Context mContext;
    private List<Entry> mEntryList;
    private OnItemSelectedListener mOnItemSelectedListener;

    public EntryAdapter(Context context, OnItemSelectedListener listener) {
        mContext = context;
        mOnItemSelectedListener = listener;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EntryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.entry_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int pos) {
        entryViewHolder.title.setText(mEntryList.get(pos).getTitle());
        entryViewHolder.content.setText(mEntryList.get(pos).getContent());
        entryViewHolder.time.setText(mEntryList.get(pos).getTimestamp());
        entryViewHolder.emotion.setImageResource(Utils.returnMoodIconWhite(mEntryList.get(pos).getMood()));

        Utils.configCardLayout(mContext, entryViewHolder.layout, mEntryList.get(pos).getSentimentColor());

        entryViewHolder.layout.setOnClickListener(view -> {
            mOnItemSelectedListener.OnItemSelected(mEntryList.get(pos));
        });
    }

    @Override
    public int getItemCount() {
        return mEntryList == null ? 0 : mEntryList.size();
    }

    class EntryViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.entry_item_title)
        TextView title;
        @BindView(R.id.entry_item_content)
        TextView content;
        @BindView(R.id.entry_item_time)
        TextView time;
        @BindView(R.id.entry_item_layout)
        ConstraintLayout layout;
        @BindView(R.id.entry_emotion)
        ImageView emotion;
        EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<Entry> entries){
        this.mEntryList = entries;
        notifyDataSetChanged();
    }

    public interface OnItemSelectedListener{
        void OnItemSelected(Entry entry);
    }
}
