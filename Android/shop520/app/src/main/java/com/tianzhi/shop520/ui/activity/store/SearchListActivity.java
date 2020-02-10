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
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;
import com.tianzhi.shop520.entity.store.SearchInfo;
import com.tianzhi.shop520.ui.adapter.SearchAdp;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;

import butterknife.BindView;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/11/9.
 * 模糊查询
 *搜索出来的商品
 */

public class SearchListActivity extends BaseFragmentActivity {
    @BindView(R.id.goodsclassify_rlv)
    RecyclerView goodsclassifyRlv;
    private Context mContext;
    String itemTitle;//模糊查询
    ArrayList<GoodsInfoEntity> goodsInfoEntityList;
    SearchInfo searchInfo;//查出数据
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentLayout(R.layout.act_goodsclassify);
//        initData();
        initView();

    }

    protected void initView() {
        itemTitle = paras.getString("itemTitle");
        if(!TextUtils.isEmpty(itemTitle)){
            setActivityTitle(itemTitle);
            queryItemByItemTitle();
        }else {
            getData();
        }
    }
    /*商城首页*/
    private void getData() {
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.GOODLIST)
                .tag(this)
                .params("curPage", "1")
                .params("rows", "20")
                .execute(new DialogCallback<BaseResponse<ArrayList<GoodsInfoEntity>>>(this, true) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<GoodsInfoEntity>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        goodsInfoEntityList = listBaseResponse.data;
                        LogUtils.e("商品",goodsInfoEntityList.toString());
                    }
                });
    }


    /*模糊查询商品*/
    private void queryItemByItemTitle(){
        OkGo.get(BaseConstant.GoodsUrl+ BaseConstant.QUERYITEMBYITEMTITLE)
                .params("itemTitle",itemTitle)
                .params("curPage","1")
                .params("rows","10")
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        LogUtils.e("模糊查询",s);
//
//                    }
//                });
                .execute(new DialogCallback<BaseResponse<SearchInfo>>(this,true) {
                    @Override
                    public void onSuccess(BaseResponse<SearchInfo> searchInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(searchInfoBaseResponse, call, response);
                        searchInfo = searchInfoBaseResponse.data;
                        goodsInfoEntityList = searchInfo.getList();
                        initAdapter();
                    }
                });

    }


    private void initAdapter() {
        goodsclassifyRlv.setLayoutManager(new LinearLayoutManager(mContext));
        SearchAdp goodsClassAdp = new SearchAdp(R.layout.item_goodsclass, goodsInfoEntityList);
        goodsClassAdp.openLoadAnimation();
        goodsClassAdp.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString(Constants.GOODSID,goodsInfoEntityList.get(position).id);
                goNext(GoodsInfoAct.class,paras,false);
            }
        });
        goodsclassifyRlv.setAdapter(goodsClassAdp);
    }

}


