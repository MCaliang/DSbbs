package me.yluo.ruisiapp.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.yluo.ruisiapp.R;

/**
 * Created by yang on 18-08-01.
 * 翻页dialog
 */
public class ArticleJumpDialog extends DialogFragment {

    private EditText content;
    private int currentPage = 1;
    private int maxPage = 1;
    private JumpDialogListener dialogListener;
    private int selectPage = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_jump_page, null);
        builder.setView(view);

        content = view.findViewById(R.id.content);

        TextView textView = view.findViewById(R.id.textpage);
        if (currentPage < 1) {
            currentPage = 1;
        }
        String text = "当前 第" + currentPage + "/" + maxPage + "页";
        textView.setText(text);
        TextView btn_cancel = view.findViewById(R.id.btn_cancel);
        TextView btn_send = view.findViewById(R.id.btn_send);

        btn_send.setOnClickListener(view1 -> {
            if (checkInput()) {
                dialogListener.JumpComfirmClick(ArticleJumpDialog.this, selectPage);
                ArticleJumpDialog.this.getDialog().cancel();
            }
        });

        btn_cancel.setOnClickListener(view12 -> dismiss());


        return builder.create();
    }

    public interface JumpDialogListener {
        void JumpComfirmClick(DialogFragment dialog, int page);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            dialogListener = (JumpDialogListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

    private boolean checkInput() {
        String str = content.getText().toString();
        if (str.isEmpty()) {
            content.setError("不能为空");
            return false;
        }

        int a = Integer.parseInt(str);
        if (a > maxPage || a < 1) {
            content.setError("请输入正确的页数");
            return false;
        } else {
            selectPage = a;
        }

        return true;
    }
}