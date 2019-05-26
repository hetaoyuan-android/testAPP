package com.yuan.myapplication.call;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.yuan.myapplication.R;

public class CallPersimission extends Activity {
    private Button bt_call;
    private Button bt_permissionsdispatcher;
    private static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.call_phone);
        bt_call = (Button) this.findViewById(R.id.bt_call);
        bt_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CallPersimission.this, "aaa", Toast.LENGTH_SHORT).show();
                call();

            }
        });
    }

    public void call() {

        //检查App是否有permission.CALL_PHONE的权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //如果没有permission.CALL_PHONE的权限的就申请该权限
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }
    }

    public void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + "10086");
        intent.setData(data);
        try {
            startActivity(intent);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            } else {
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                    AlertDialog dialog = new AlertDialog.Builder(this)
                            .setMessage("该功能需要访问电话的权限，不开启将无法正常工作！")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            }).create();
                    dialog.show();
                }
                return;
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
