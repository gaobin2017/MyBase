package com.lsl.mybase.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.lsl.mybase.R;
import com.lsl.mybase.global.NetUrl;
import com.lsl.mybase.model.RegisterBodyBean;
import com.lsl.mybase.model.ResponseModel;
import com.lsl.mybase.network.RetrofitUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

public class SampleActivity extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView mTv;
    @BindView(R.id.bt)
    Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt)
    public void onViewClicked() {

        postData();

    }




    private void postData() {
        //网络访问调用解释
        //1,设置请求体，以对象的形式设置
        RegisterBodyBean registerBodyBean = new RegisterBodyBean();
        registerBodyBean.setScene("REGIST");
        registerBodyBean.setMobile("13512345678");
        registerBodyBean.setPlatformCode("MOBILE");
        //2,网络访问调用
        RetrofitUtil.postNetData(NetUrl.getVerifyCode, registerBodyBean, new Observer<ResponseModel>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                mTv.setText(e.getMessage());
            }

            @Override
            public void onNext(ResponseModel responseModel) {
                mTv.setText(responseModel.toString());
            }
        });

    }


}
