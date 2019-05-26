package com.yuan.test.provider;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MesssengerService extends Service {

    static final int MSG_SAY_HELLO = 1;
    private static final String TAG = "MesssengerService";

    /**
     * 从服务端传递过来的数据
     */
    class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Log.i(TAG, "client success");
                    Messenger client = msg.replyTo;
                    Message replyMsg = Message.obtain(null, MesssengerService.MSG_SAY_HELLO);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply", "ok");
                    replyMsg.setData(bundle);
                    try {
                        client.send(replyMsg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    final Messenger messenger = new Messenger(new IncomingHandler());


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service is invoke onBind");
        return messenger.getBinder();
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totlaHeight = 0;
        int len = listAdapter.getCount();
        for (int i = 0; i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0,0);
            totlaHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totlaHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
