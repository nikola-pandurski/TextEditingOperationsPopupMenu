package com.mobisystems.menudemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PopupAdapter extends RecyclerView.Adapter<PopupAdapter.PopupVH> {

    public interface ItemClickListener {
        void onItemClick(PopupAdapter adapter, int position);
    }

    private List<PopupItem> _items;
    private Context _ctx;
    private ItemClickListener _listener;

    class PopupVH extends RecyclerView.ViewHolder implements View.OnClickListener {

        public PopupVH(@NonNull View itemView) {
            super(itemView);
            getTextView().setOnClickListener(this);
        }

        TextView getTextView() {
            return (TextView) itemView.findViewById(R.id.textView);
        }

        @Override
        public void onClick(View v) {
            if (_listener != null)
                _listener.onItemClick(PopupAdapter.this, getAdapterPosition());
        }
    }

    public PopupAdapter(Context ctx, List<PopupItem> items, ItemClickListener listener) {
        _items = items;
        _ctx = ctx;
        _listener = listener;
    }

    @NonNull
    @Override
    public PopupVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(_ctx).inflate(R.layout.popup_item, parent, false);
        return new PopupVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PopupVH holder, int position) {
        TextView tv = holder.getTextView();
        PopupItem item = _items.get(position);
        tv.setText(item.getTitle());
        tv.setCompoundDrawablesRelativeWithIntrinsicBounds(0, item.getIcon(), 0, 0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public PopupItem getItem(int position){
        return _items.get(position);
    }

    @Override
    public int getItemCount() {
        return _items.size();
    }
}
