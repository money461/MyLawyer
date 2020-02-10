package com.tianzhi.shop520.ui.activity.shop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.store.ReceiverInfo;
import com.tianzhi.shop520.ui.adapter.AddressManagerAdp;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 *
 * 地址管理
 */

public class AddressManageAct extends BaseFragmentActivity {
    @BindView(R.id.tv_add)
    TextView tvAdd;
    @BindView(R.id.rcl_address)
    RecyclerView rclAddress;
    private ArrayList<ReceiverInfo> addresslist;
    private String goActicityName;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_addressmanager);
        initView();
        initData();

    }

    protected void initView() {
        setActivityTitle("管理收货地址");
        goActicityName = paras.getString(Constants.GOACTITYNAME);
        LogUtils.e("传过来的界面值",goActicityName);
    }
    private void initData() {
        OkGo.post(BaseConstant.OrderUrl+BaseConstant.LISTRECEIVERINFO)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<ArrayList<ReceiverInfo>>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<ReceiverInfo>> arrayListBaseResponse, Call call, Response response) {
                        super.onSuccess(arrayListBaseResponse, call, response);
                        addresslist = arrayListBaseResponse.data;
                        initAdapter();
                    }
                });
    }
    private void initAdapter() {
        rclAddress.setLayoutManager(new LinearLayoutManager(this));
        rclAddress.setHasFixedSize(true);
        AddressManagerAdp addressAdp = new AddressManagerAdp(R.layout.item_address_manager, addresslist);
        addressAdp.openLoadAnimation();
        addressAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //判断从那个界面进入的  确认订单界面 2,地址管理
                if("EnsureOrderAct".equals(goActicityName)){
                    ReceiverInfo receiverInfo = new ReceiverInfo();
                    receiverInfo = addresslist.get(position);
//                    paras.putSerializable(Constants.RECEIVERINFO,receiverInfo);
                    Intent intent = new Intent();
                    intent.putExtra(Constants.RECEIVERINFO,receiverInfo);
                    setActivityResult(intent,200);
//                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法
                }
            }
        });
        addressAdp.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if(view.getId()==R.id.address_delect){
                    optReciverInfo(addresslist.get(position).getId());
                }else if(view.getId()==R.id.address_edit){
                    paras.putString("type","update");
                    paras.putSerializable("ReceiverInfo",addresslist.get(position));
                    goNext(AddAddressAct.class,paras,false);
                }else  if(view.getId()==R.id.rl_isdefaul){
                    createReceiverInfo(addresslist.get(position));
                }
            }
        });
        rclAddress.setAdapter(addressAdp);

    }
/*删除地址*/
    private  void optReciverInfo(String id){
        showLoading();
        OkGo.post(BaseConstant.OrderUrl+BaseConstant.OPTRECIVERINFO)
                .params("userId",AppShared.getInstance(context).getLoginInfo().id)
                .params("id",id)
                .params("type","delete")
                .params("userToken",AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        toast(baseResponseData.data);
                        LogUtils.e("删除收货地址",baseResponseData.toString());
                        initData();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
    @OnClick(R.id.tv_add)
    public void onViewClicked() {
        paras.putString("type","add");
        goNext(AddAddressAct.class,paras,false);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initData();
    }

    private void createReceiverInfo(ReceiverInfo receiverInfo) {
        ReceiverInfo receiverInfos = new ReceiverInfo();
        receiverInfos = receiverInfo;
        receiverInfos.setIsDefault("1");
        Gson gson = new Gson();
        String obj = gson.toJson(receiverInfos);
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.CREATERECEIVERINFO)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("type", "update")
                .params("receiverInfo",obj )
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                       dismissLoading();
                        if("200".equals(baseResponseData.flag)){
                            toast(baseResponseData.msg);
                            initData();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
}
