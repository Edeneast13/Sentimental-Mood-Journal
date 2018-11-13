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

public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.EntryViewHolder> {

    private Context mContext;
    private List<Entry> mEntryList;

    EntryAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public EntryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new EntryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.entry_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EntryViewHolder entryViewHolder, int i) {
        String[] split = mEntryList.get(i).getTimestamp().split(" - ");
        entryViewHolder.title.setText(mEntryList.get(i).getTitle());
        entryViewHolder.content.setText(mEntryList.get(i).getContent());
        entryViewHolder.time.setText(split[1]);
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
        EntryViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setData(List<Entry> entries){
        this.mEntryList = entries;
        notifyDataSetChanged();
    }
}
