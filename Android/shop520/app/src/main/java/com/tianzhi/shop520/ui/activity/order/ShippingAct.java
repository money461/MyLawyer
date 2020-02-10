package com.tianzhi.shop520.ui.activity.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.order.ShippingEntity;
import com.tianzhi.shop520.ui.adapter.ShippingAdp;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/24.
 * 物流信息
 */

public class ShippingAct extends BaseFragmentActivity {
    ShippingEntity shippingEntity;
    @BindView(R.id.shipping_state)
    TextView shippingState;
    @BindView(R.id.shipping_company)
    TextView shippingCompany;
    @BindView(R.id.shipping_code)
    TextView shippingCode;
    @BindView(R.id.shipping_phone)
    TextView shippingPhone;
    @BindView(R.id.shopping_rcl)
    RecyclerView shoppingRcl;
//    String com = "tt";
//    String code = "668121298995";
String com = "";
        String code = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_shipping);
        initView();
        getData();
    }

    @Override
    protected void initView() {
        setActivityTitle("物流信息");
        com = paras.getString("com");
        code = paras.getString("shippingCode");
    }

    /*获取物流信息*/
    public void getData() {
        String url = BaseConstant.TestUrl + BaseConstant.ORDERLOGISTICS
                .replace("{com}", com).replace("{no}", code);
        showLoading();
        OkGo.get(url)
                .execute(new DialogCallback<BaseResponse<ShippingEntity>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<ShippingEntity> shippingEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(shippingEntityBaseResponse, call, response);
                        dismissLoading();
                        shippingEntity = shippingEntityBaseResponse.data;
                        LogUtils.e("物流信息",shippingEntity.toString());
                        setAdapter();
                        if("1".equals(shippingEntity.status)){
                            shippingState.setText("已签收");
                        }
                        shippingCompany.setText(shippingEntity.company);
                        shippingCode.setText(shippingEntity.getNo());
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    private void setAdapter() {
        shoppingRcl.setLayoutManager(new LinearLayoutManager(this));
        shoppingRcl.setHasFixedSize(true);
        ShippingAdp addressAdp = new ShippingAdp(context, shippingEntity.getList());
        shoppingRcl.setAdapter(addressAdp);

    }
}
