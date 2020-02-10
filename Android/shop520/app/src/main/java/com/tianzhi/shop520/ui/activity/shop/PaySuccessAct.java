package com.tianzhi.shop520.ui.activity.shop;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;
import com.tianzhi.shop520.ui.MainActivity;
import com.tianzhi.shop520.ui.activity.order.MyorderAct;
import com.tianzhi.shop520.ui.activity.store.GoodsInfoAct;
import com.tianzhi.shop520.ui.adapter.PaySuccessAdp;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/10.
 * 支付成功界面
 */

public class PaySuccessAct extends BaseFragmentActivity {
    List<GoodsInfoEntity> pagList;
    @BindView(R.id.look_order)
    TextView lookOrder;
    @BindView(R.id.back_home)
    TextView backHome;
    @BindView(R.id.land_list)
    RecyclerView landList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_pay_success);
        initView();
        getRecommendItem();
    }

    @Override
    protected void initView() {
        setActivityTitle("支付成功");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            goNext(MyorderAct.class);
        }
        return false;
    }

    /*商品推荐*/
    public void getRecommendItem() {
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.RECOMMENDITEM)
                .tag(this)
                .params("i", "2")
                .execute(new DialogCallback<BaseResponse<List<GoodsInfoEntity>>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsInfoEntity>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        pagList = listBaseResponse.data;
                        initLandAdapter();
                    }
                });
    }


    /*横向滑动*/
    private void initLandAdapter() {
        landList.setHasFixedSize(true);
        landList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(0);
        landList.setLayoutManager(linearLayoutManager2);
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
        int height = dm.heightPixels;
        int w = dm.widthPixels;
        PaySuccessAdp storeAdapter = new PaySuccessAdp(R.layout.item_land_paysuccess, pagList, context,w,height);
        storeAdapter.openLoadAnimation();
        storeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString(Constants.GOODSID, pagList.get(position).id);
                goNext(GoodsInfoAct.class, paras, true);
            }
        });
        landList.setAdapter(storeAdapter);
    }

    @OnClick({R.id.look_order, R.id.back_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.look_order:
                goNext(MyorderAct.class,true);
                break;
            case R.id.back_home:
                paras.putString("page","0");
                goNext(MainActivity.class,paras,true);
                break;
        }
    }
}
