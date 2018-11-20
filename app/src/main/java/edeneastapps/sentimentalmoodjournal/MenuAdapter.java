package edeneastapps.sentimentalmoodjournal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private Context mContext;
    private List<MenuItem> mItems;

    MenuAdapter(Context context, List<MenuItem> items) {
        mContext = context;
        mItems = items;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MenuAdapter.MenuViewHolder(LayoutInflater.from(mContext).inflate(R.layout.menu_recycler_item, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {
        menuViewHolder.title.setText(mItems.get(i).getTitle());
        Utils.configCardLayout(mContext, menuViewHolder.layout, mItems.get(i).getBackgroundColor());
        menuViewHolder.title.setTextColor(mContext.getResources().getColor(mItems.get(i).getTextColor()));
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.menu_option_title)
        TextView title;
        @BindView(R.id.menu_layout)
        ConstraintLayout layout;
        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
