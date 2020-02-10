package com.tianzhi.shop520.ui.activity.store;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.store.GoodsClassItem;
import com.tianzhi.shop520.ui.adapter.GoodsClassAdp;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 * 商品分类展示
 */

public class GoodsClassifyAct extends BaseFragmentActivity {
    @BindView(R.id.goodsclassify_rlv)
    RecyclerView goodsclassifyRlv;
    private Context mContext;
    private List<GoodsClassItem>  Goodslist;
    String id;//商品分类id
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentLayout(R.layout.act_goodsclassify);
//        initData();
        initView();

    }

    protected void initView() {
        id = paras.getString("ClassId");
        if(!TextUtils.isEmpty(id)){
            getdata(id);
        }
        LogUtils.e("接收商品分类id",id);
        if(!TextUtils.isEmpty(paras.getString("Classname"))){
            setActivityTitle(paras.getString("Classname"));
        }else {
            setActivityTitle(paras.getString("商品分类"));
        }
    }

    /*商品分类*/
    private void getdata(String id){
        String Url = (BaseConstant.GoodsUrl+BaseConstant.QUERYITEMBYCATEGORY);
        OkGo.get(Url)
                .tag(this)
                .params("id",id)
                .execute(new DialogCallback<BaseResponse<List<GoodsClassItem>>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsClassItem>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        Goodslist = new ArrayList<GoodsClassItem>() ;
                        Goodslist = listBaseResponse.data;
                        LogUtils.e("商品分类详情",listBaseResponse.toString());
                        initAdapter();
                    }
                });
    }

    private void initAdapter() {
        goodsclassifyRlv.setLayoutManager(new LinearLayoutManager(mContext));
        GoodsClassAdp goodsClassAdp = new GoodsClassAdp(R.layout.item_goodsclass, Goodslist);
        goodsClassAdp.openLoadAnimation();
        goodsClassAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString(Constants.GOODSID,Goodslist.get(position).id);
                    goNext(GoodsInfoAct.class,paras,false);
            }
        });
        goodsclassifyRlv.setAdapter(goodsClassAdp);
    }

}

