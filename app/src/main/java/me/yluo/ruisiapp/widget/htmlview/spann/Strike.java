package me.yluo.ruisiapp.widget.htmlview.spann;

import android.text.TextPaint;
import android.text.style.CharacterStyle;

public class Strike extends CharacterStyle {

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setStrikeThruText(true);
    }
}
