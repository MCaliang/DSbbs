package me.yluo.ruisiapp.widget.emotioninput;

import android.content.Context;
import android.content.SharedPreferences;

import me.yluo.ruisiapp.utils.DimenUtils;


/**
 * For save the keyboard height.
 */
class KeyBoardHeightPreference {

    private final static String FILE_NAME = "keyboard.common";
    private final static String KEY_KEYBOARD_HEIGHT = "sp.key.keyboard.height";
    private volatile static SharedPreferences SP;

    public static boolean save(final Context context, final int keyboardHeight) {
        return with(context).edit()
                .putInt(KEY_KEYBOARD_HEIGHT, keyboardHeight)
                .commit();
    }

    private static SharedPreferences with(final Context context) {
        if (SP == null) {
            synchronized (KeyBoardHeightPreference.class) {
                if (SP == null) {
                    SP = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
                }
            }
        }

        return SP;
    }

    public static int get(final Context context, int defaultHeight) {
        defaultHeight = DimenUtils.dip2px(context, defaultHeight);
        return with(context).getInt(KEY_KEYBOARD_HEIGHT, defaultHeight);
    }

}
