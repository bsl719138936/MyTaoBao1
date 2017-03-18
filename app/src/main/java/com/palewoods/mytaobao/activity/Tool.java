package com.palewoods.mytaobao.activity;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by bsl52840 on 2017/3/15.
 * 工具类
 */

public class Tool {
    public static void ShowManager(Context context,String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
