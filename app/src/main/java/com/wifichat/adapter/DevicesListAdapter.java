package com.wifichat.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wifichat.ChatMessagesActivity;
import com.wifichat.R;

import java.util.List;

/**
 * Created by Divya on 2/5/2017.
 */

public class DevicesListAdapter extends RecyclerView.Adapter<DevicesListAdapter.DevicesViewAdapter> {

    private List<WifiP2pDevice> permsList;
    private Context context;
    private WifiP2pManager mManager;
    private WifiP2pManager.Channel mChannel;


    public static class DevicesViewAdapter extends RecyclerView.ViewHolder {
        TextView permName;
        LinearLayout itemContainer;

        public DevicesViewAdapter(View v) {
            super(v);
            permName = (TextView) v.findViewById(R.id.device_name);
            itemContainer = (LinearLayout) v.findViewById(R.id.item_container);
        }
    }

    public DevicesListAdapter(List<WifiP2pDevice> permsList, Context context,
                              WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel) {
        this.permsList = permsList;
        this.context = context;
        this.mChannel = channel;
        this.mManager = wifiP2pManager;
    }

    @Override
    public DevicesListAdapter.DevicesViewAdapter onCreateViewHolder(ViewGroup parent,
                                                                    int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_device, parent, false);
        return new DevicesListAdapter.DevicesViewAdapter(view);
    }


    @Override
    public void onBindViewHolder(final DevicesListAdapter.DevicesViewAdapter holder, final int position) {

        holder.permName.setText(permsList.get(position).deviceName);
        holder.itemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WifiP2pDevice device;
                final WifiP2pConfig config = new WifiP2pConfig();
                config.deviceAddress = permsList.get(position).deviceAddress;
                mManager.connect(mChannel, config, new WifiP2pManager.ActionListener() {

                    @Override
                    public void onSuccess() {
                        //success logic
                        Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent((Activity) context, ChatMessagesActivity.class);
                        intent.putExtra("device",permsList.get(position));
                        ((Activity) context).startActivity(intent);
                    }

                    @Override
                    public void onFailure(int reason) {
                        //failure logic
                        Toast.makeText(context, "Not able to connect", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return permsList.size();
    }
}
