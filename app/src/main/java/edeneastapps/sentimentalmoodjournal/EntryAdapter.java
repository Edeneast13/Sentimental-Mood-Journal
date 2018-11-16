package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
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
        entryViewHolder.title.setText(mEntryList.get(i).getTitle());
        entryViewHolder.content.setText(mEntryList.get(i).getContent());
        entryViewHolder.time.setText(mEntryList.get(i).getTimestamp());
//        String score = mEntryList.get(i).getSentimentScore();
//        if (score != null){
//            entryViewHolder
//                    .layout
//                    .setBackgroundColor(
//                            mContext.getResources().getColor(Utils.returnSentimentRangeColor(Float.parseFloat(score))));
//        }

        switch (mEntryList.get(i).getMood()){
            case 0: {
                entryViewHolder.emotion.setImageResource(R.mipmap.ic_angry_icon_white);
                break;
            }
            case 1: {
                entryViewHolder.emotion.setImageResource(R.mipmap.ic_sad_emoji_white);
                break;
            }
            case 2: {
                entryViewHolder.emotion.setImageResource(R.mipmap.ic_confused_emoji_white);
                break;
            }
            case 3: {
                entryViewHolder.emotion.setImageResource(R.mipmap.ic_okay_emoji_white);
                break;
            }
            case 4: {
                entryViewHolder.emotion.setImageResource(R.mipmap.ic_happy_emoji_white);
                break;
            }
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setCornerRadius(25);
        shape.setColor(mContext.getResources().getColor(Utils.returnSentimentRangeColor(Float.parseFloat(mEntryList.get(i).getSentimentScore()))));
        entryViewHolder.layout.setBackground(shape);
        entryViewHolder.layout.setElevation(4);
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
}
