package me.yluo.ruisiapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    public static void showKeyboard(final View view) {
        if (view != null && view.requestFocus()) {

            if (view.requestFocus()) {
                InputMethodManager imm = (InputMethodManager)
                        view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        }
    }

    public static void hideKeyboard(final View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null)
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null)
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static void hideKeyboard(Window window) {
        if (window != null) {
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }
}