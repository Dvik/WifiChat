package com.wifichat.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wifichat.R;
import com.wifichat.data.MessagesContract;

/**
 * Created by Divya on 2/5/2017.
 */

public class MessagesAdapter extends CursorRecyclerViewAdapter<MessagesAdapter.MessageViewHolder> {

    private Context context;

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        TextView messageTitle;
        LinearLayout itemContainer;

        public MessageViewHolder(View v) {
            super(v);
            messageTitle = (TextView) v.findViewById(R.id.device_name);
            itemContainer = (LinearLayout) v.findViewById(R.id.item_container);
        }
    }

    public MessagesAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        this.context = context;
    }

    @Override
    public MessagesAdapter.MessageViewHolder onCreateViewHolder(ViewGroup parent,
                                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_device, parent, false);
        return new MessageViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final MessageViewHolder holder, Cursor cursor) {

        final String name = cursor.getString(cursor.getColumnIndex(MessagesContract.MessagesEntry.COLUMN_MESSAGE));

        holder.messageTitle.setText(name);

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }
}
