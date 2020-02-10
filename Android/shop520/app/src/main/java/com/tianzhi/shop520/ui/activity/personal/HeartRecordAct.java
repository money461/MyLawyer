package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.personal.ConsumptionList;
import com.tianzhi.shop520.ui.adapter.HeartRecordAdp;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/24.
 */

public class HeartRecordAct extends BaseFragmentActivity {
    @BindView(R.id.heart_record)
    RecyclerView heartRecord;
    ArrayList<ConsumptionList> consumptionList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_heartrecord);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("爱心值记录");
        getConsumptionList();

    }
    /*查询爱心值消费记录*/
    public void getConsumptionList(){
        String url = BaseConstant.RECOMMENDURL+BaseConstant.GETCONSUMPTIONLIST;
        OkGo.get(url.replace("userId", AppShared.getInstance(context).getLoginInfo().id)
                .replace("userToken",AppShared.getInstance(context).getLoginInfo().userToken))
                .execute(new DialogCallback<BaseResponse<ArrayList<ConsumptionList>>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<ConsumptionList>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        consumptionList = listBaseResponse.data;

                        initAdapter();
                    }
                });
    }

    private void initAdapter() {
        heartRecord.setLayoutManager(new LinearLayoutManager(this));
        heartRecord.setHasFixedSize(true);
        HeartRecordAdp addressAdp = new HeartRecordAdp(context, consumptionList);
        heartRecord.setAdapter(addressAdp);

    }
}
