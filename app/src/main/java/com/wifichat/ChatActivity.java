package com.wifichat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.wifichat.adapter.DevicesListAdapter;
import com.wifichat.receiver.WifiDirectReceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements WifiP2pManager.PeerListListener {

    WifiP2pManager mManager;
    WifiP2pManager.Channel mChannel;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;
    List<WifiP2pDevice> peers;
    DevicesListAdapter devicesListAdapter;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    TextView emptyView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        mChannel = mManager.initialize(this, getMainLooper(), null);
        mReceiver = new WifiDirectReceiver(mManager, mChannel, this);
        peers = new ArrayList<WifiP2pDevice>();


        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {

                        Toast.makeText(ChatActivity.this, "Success", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onFailure(int reasonCode) {
                        Toast.makeText(ChatActivity.this, "Failed due to " + String.valueOf(reasonCode), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.discovered_peers);
        emptyView = (TextView) findViewById(R.id.empty_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onPeersAvailable(WifiP2pDeviceList wifiP2pDeviceList) {

        Collection<WifiP2pDevice> refreshedPeers = wifiP2pDeviceList.getDeviceList();
        if (!refreshedPeers.equals(peers)) {
            emptyView.setVisibility(View.GONE);
            peers.clear();
            peers.addAll(refreshedPeers);

            devicesListAdapter = new DevicesListAdapter(peers, this, mManager, mChannel);
            recyclerView.setAdapter(devicesListAdapter);

        }

        if (peers.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            Log.d("ChatActivity", "No devices found");
            return;
        }
    }

}



