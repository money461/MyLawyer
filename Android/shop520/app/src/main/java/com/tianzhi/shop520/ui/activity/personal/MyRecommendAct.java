package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.personal.MyRecommend;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 * 我的推荐
 */

public class MyRecommendAct extends BaseFragmentActivity {

    @BindView(R.id.my_QRcode)
    TextView myQRcode;
    @BindView(R.id.tv_extract)
    TextView tvExtract;
    MyRecommend myRecommend;
    @BindView(R.id.tv_loveTotal)
    TextView tvLoveTotal;
    @BindView(R.id.tv_loveSurplus)
    TextView tvLoveSurplus;
    @BindView(R.id.tv_gradeOne)
    TextView tvGradeOne;
    @BindView(R.id.tv_gradeTwo)
    TextView tvGradeTwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_myrecommend);
        initView();


    }

    @Override
    protected void initView() {
        setActivityTitle("我的推荐");
        getData();
    }

    /*我的推荐 获取爱心值 收益等*/
    private void getData() {
        String url =BaseConstant.RECOMMENDURL + BaseConstant.GETRECOMMEND;
        showLoading();
        OkGo.get(url.replace("userId",AppShared.getInstance(context).getLoginInfo().id)
        .replace("userToken",AppShared.getInstance(context).getLoginInfo().userToken))
                .execute(new DialogCallback<BaseResponse<MyRecommend>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<MyRecommend> myRecommendBaseResponse, Call call, Response response) {
                        super.onSuccess(myRecommendBaseResponse, call, response);
                        dismissLoading();
                        myRecommend = myRecommendBaseResponse.data;
                        tvGradeOne.setText(myRecommend.getGradeOne());
                        tvGradeTwo.setText(myRecommend.getGradeTwo());
                        tvLoveSurplus.setText(myRecommend.getLoveSurplus());
                        tvLoveTotal.setText(myRecommend.getLoveTotal());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }


    @OnClick({R.id.my_QRcode, R.id.tv_extract})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_QRcode:
                goNext(CreateQrcodeAct.class);
                break;
            case R.id.tv_extract:
//                goNext(PayDemoActivity.class);
//                alipayLogin();
//                paras.putString(Constants.lovesurplus,myRecommend.getLoveSurplus());
//                paras.putString(Constants.STATUS,myRecommend.getStatus());
                goNext(HeartExtractAct.class);
                break;
        }
    }

}
