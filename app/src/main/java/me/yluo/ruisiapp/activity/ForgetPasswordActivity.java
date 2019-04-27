package me.yluo.ruisiapp.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

import me.yluo.ruisiapp.App;
import me.yluo.ruisiapp.R;
import me.yluo.ruisiapp.myhttp.HttpUtil;
import me.yluo.ruisiapp.myhttp.ResponseHandler;
import me.yluo.ruisiapp.utils.RuisUtils;
import me.yluo.ruisiapp.utils.UrlUtils;

/**
 * 找回密码
 */
public class ForgetPasswordActivity extends BaseActivity {

    private TextInputEditText edEmail, edUsername;
    private View btnSubmit;
    private TextInputLayout emailTextInput;
    private ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        initToolBar(true, "找回密码");
        edEmail = findViewById(R.id.email);
        edUsername = findViewById(R.id.username);
        btnSubmit = findViewById(R.id.btn_submit);
        emailTextInput = findViewById(R.id.email_input);
        TextView infoView = findViewById(R.id.info_view);
        infoView.setText("外网用户无法访问邮件里面的找回密码链接，需要将域名修改为外网域名"
                + App.BASE_URL_ME
                + "并加上尾缀&mobile=2\n举例:原始链接[http://rs.xidian.edu.cn/xxx]\n修改后:["
                + App.BASE_URL_ME + "xxx&mobile=2]");
        btnSubmit.setOnClickListener(v -> submit());
        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                emailTextInput.setError(null);
                if (!TextUtils.isEmpty(edEmail.getText())) {
                    btnSubmit.setEnabled(true);
                } else {
                    btnSubmit.setEnabled(false);
                }
            }
        });
    }


    private void submit() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("提交中，请稍后......");
        dialog.show();

        final String email = edEmail.getText().toString().trim();
        final String username = edUsername.getText().toString().trim();

        Map<String, String> params = new HashMap<>();
        params.put("handlekey", "lostpwform");
        params.put("email", email);
        params.put("username", username);

        String ul = UrlUtils.getForgetPasswordUrl();
        HttpUtil.post(ul, params, new ResponseHandler() {
            @Override
            public void onSuccess(byte[] response) {
                String res = new String(response);
                String errorText;

                if (res.contains("取回密码的方法已通过")) {
                    showLongToast("取回密码的方法已通过 Email 发送到您的信箱中，请尽快修改您的密码");
                } else if ((errorText = RuisUtils.getErrorText(res)) != null) {
                    emailTextInput.setError(errorText);
                } else {
                    emailTextInput.setError("账号或者密码错误");
                }
            }

            @Override
            public void onFailure(Throwable e) {
                emailTextInput.setError("网络异常:" + e.getMessage());
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
            }
        });
    }
}
