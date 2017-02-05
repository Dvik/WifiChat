package com.wifichat;

import android.database.Cursor;
import android.net.wifi.p2p.WifiP2pDevice;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wifichat.adapter.MessagesAdapter;
import com.wifichat.data.MessagesContract;

public class ChatMessagesActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private EditText chatMessage;
    private ImageView chatSend;
    private MessagesAdapter messagesAdapter;
    private Cursor cursor;
    private RecyclerView recyclerView;
    private TextView emptyView;
    private WifiP2pDevice device;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_messages);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initViews();

        device = getIntent().getParcelableExtra("device");

    }

    private void initViews() {
        chatMessage = (EditText) findViewById(R.id.chat_message);
        chatSend = (ImageView) findViewById(R.id.chat_send);
        recyclerView = (RecyclerView) findViewById(R.id.rv_chat_message);
        emptyView = (TextView) findViewById(R.id.empty_view);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(ChatMessagesActivity.this, MessagesContract.MessagesEntry.CONTENT_URI,
                MessagesContract.MessagesEntry.SITUATION_PROJECTION,
                MessagesContract.MessagesEntry.COLUMN_SENDER + "== ?", new String[]{device.deviceName},
                MessagesContract.MessagesEntry.COLUMN_ID + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        messagesAdapter.swapCursor(data);
        cursor = data;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        messagesAdapter.swapCursor(null);
    }
}
