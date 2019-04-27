package me.yluo.ruisiapp.widget.htmlview.callback;

import android.graphics.drawable.Drawable;


public interface ImageGetterCallBack {
    void onImageReady(String source, int start, int end, Drawable d);
}
