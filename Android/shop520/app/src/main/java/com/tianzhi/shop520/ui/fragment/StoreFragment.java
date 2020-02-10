package com.tianzhi.shop520.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseLazyFragment;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.home.HomeListEntity;
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;
import com.tianzhi.shop520.entity.store.ListCategoryItem;
import com.tianzhi.shop520.entity.store.StoreInfo;
import com.tianzhi.shop520.entity.store.StoreInfoEntity;
import com.tianzhi.shop520.ui.activity.SearchActivity;
import com.tianzhi.shop520.ui.activity.store.GoodsClassifyAct;
import com.tianzhi.shop520.ui.activity.store.GoodsInfoAct;
import com.tianzhi.shop520.ui.activity.store.PagListAct;
import com.tianzhi.shop520.ui.adapter.StorGirdAdapter;
import com.tianzhi.shop520.ui.adapter.StoreBagAdapter;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollBase;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollPagerAdapter;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollViewPager;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.PageControlBase;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.RoundedTextView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 * 商城
 */

public class StoreFragment extends BaseLazyFragment {
    @BindView(R.id.viewPager)
    AutoScrollViewPager viewPager;
    @BindView(R.id.store_grid_recycler)
    RecyclerView storeGridRecycler;
    @BindView(R.id.stor_more_backpage)
    TextView storMoreBackpage;
    @BindView(R.id.land_list)
    RecyclerView landList;
    @BindView(R.id.stor_more_redwine)
    TextView storMoreRedwine;
    @BindView(R.id.store_redwine_pic1)
    ImageView storeRedwinePic1;
    @BindView(R.id.store_redwine_text1)
    TextView storeRedwineText1;
    @BindView(R.id.store_redwine_price1)
    TextView storeRedwinePrice1;
    @BindView(R.id.store_redwine_VIPprice1)
    TextView storeRedwineVIPprice1;
    @BindView(R.id.store_redwine_pic2)
    ImageView storeRedwinePic2;
    @BindView(R.id.store_redwine_text2)
    TextView storeRedwineText2;
    @BindView(R.id.store_redwine_price2)
    TextView storeRedwinePrice2;
    @BindView(R.id.store_redwine_VIPprice2)
    TextView storeRedwineVIPprice2;
    @BindView(R.id.ll_store_redwine2)
    LinearLayout llStoreRedwine2;
    @BindView(R.id.ll_store_redwine1)
    LinearLayout llStoreRedwine1;
    @BindView(R.id.stor_more_whiteWine)
    TextView storMoreWhiteWine;
    @BindView(R.id.store_whiteWine_pic1)
    ImageView storeWhiteWinePic1;
    @BindView(R.id.store_whiteWine_text1)
    TextView storeWhiteWineText1;
    @BindView(R.id.store_whiteWine_price1)
    TextView storeWhiteWinePrice1;
    @BindView(R.id.store_whiteWine_VIPprice1)
    TextView storeWhiteWineVIPprice1;
    @BindView(R.id.ll_store_whiteWine1)
    LinearLayout llStoreWhiteWine1;
    @BindView(R.id.store_whiteWine_pic2)
    ImageView storeWhiteWinePic2;
    @BindView(R.id.store_whiteWine_text2)
    TextView storeWhiteWineText2;
    @BindView(R.id.store_whiteWine_price2)
    TextView storeWhiteWinePrice2;
    @BindView(R.id.store_whiteWine_VIPprice2)
    TextView storeWhiteWineVIPprice2;
    @BindView(R.id.ll_store_whiteWine2)
    LinearLayout llStoreWhiteWine2;
    @BindView(R.id.store_more_makeup)
    TextView storeMoreMakeup;
    @BindView(R.id.store_makeup_pic)
    ImageView storeMakeupPic;
    @BindView(R.id.store_makeup_text)
    TextView storeMakeupText;
    @BindView(R.id.store_makeup_price)
    TextView storeMakeupPrice;
    @BindView(R.id.store_makeup_VIPprice)
    TextView storeMakeupVIPprice;
    @BindView(R.id.ll_store_makeup)
    LinearLayout llStoreMakeup;
    @BindView(R.id.et_home_serch)
    TextView etHomeSerch;
    private String[] banner_list;
    Context mContext;
    private Util util;
    View view;
    private List<ListCategoryItem> listCategory;//商品分类目录
    List<GoodsInfoEntity> goodsInfoEntityList;
    StoreInfoEntity redWinList;//红酒
    StoreInfoEntity whiteWinList;//白酒
    StoreInfoEntity makeupList;//化妆品
    StoreInfoEntity pagList;//套餐
    ArrayList<HomeListEntity> storeBannerList;
    StoreInfo storeInfo;
    ACache aCache;
    int height;
    int w;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        aCache = ACache.get(mContext);
        util = new Util(getContext());
        view = inflater.inflate(R.layout.fragment_store, container, false);
        storeInfo = (StoreInfo) aCache.getAsObject(Constants.STORELIST);
        ButterKnife.bind(this, view);
        return view;
    }

    /*商城首页信息*/
    private void getData() {
        if (null != storeInfo) {
            setData();
        }
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
         height = dm.heightPixels;
         w = dm.widthPixels;
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.GOODLIST)
                .tag(this)
                .execute(new DialogCallback<BaseResponse<StoreInfo>>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponse<StoreInfo> storeInfoBaseResponse, Call call, Response response) {
                        super.onSuccess(storeInfoBaseResponse, call, response);
                        storeInfo = storeInfoBaseResponse.data;
                        aCache.put(Constants.STORELIST, storeInfo);
                        setData();
                    }
                });
    }

    private void setData() {
        listCategory = storeInfo.getItemCategoryList();
        initGirdAdapter();
        for (int i = 0; i < storeInfo.getIndexItemList().size(); i++) {
            if ("4".equals(storeInfo.getIndexItemList().get(i).getSort())) {
                pagList = storeInfo.getIndexItemList().get(i);
            } else if ("1".equals(storeInfo.getIndexItemList().get(i).getSort())) {
                redWinList = storeInfo.getIndexItemList().get(i);
            } else if ("2".equals(storeInfo.getIndexItemList().get(i).getSort())) {
                whiteWinList = storeInfo.getIndexItemList().get(i);
            } else if ("3".equals(storeInfo.getIndexItemList().get(i).getSort())) {
                makeupList = storeInfo.getIndexItemList().get(i);
            }
        }
        storeBannerList = storeInfo.getTzContentList();
        if (null != storeBannerList && storeBannerList.size() > 0) {
            setBannerData();
        }

        //设置横向滑动adapter
        if(null!=pagList&&null != pagList.getIndexItemList()&&pagList.getIndexItemList().size()>0){
            initLandAdapter();
        }
//                        //红酒   化妆品
        setRedWineData();
//                        //白酒
        setWhiteWineData();
    }

    /*设置红酒类，化妆品类等*/
    public void setRedWineData() {
        if (null!=pagList&&null != redWinList&&null!=redWinList.getIndexItemList()
                &&redWinList.getIndexItemList().size()>0) {
            if(redWinList.getIndexItemList().size()>1){
                Glide.with(context).load(redWinList.getIndexItemList().get(1).homepageUrl).into((ImageView) storeRedwinePic2);
                storeRedwinePrice2.setText("￥" + redWinList.getIndexItemList().get(1).price);
                storeRedwineVIPprice2.setText("￥" + redWinList.getIndexItemList().get(1).memberPrice);
                storeRedwineText2.setText(redWinList.getIndexItemList().get(1).itemTitle);

            }
            Glide.with(context).load(redWinList.getIndexItemList().get(0).homepageUrl).into((ImageView) storeRedwinePic1);
            storeRedwinePrice1.setText("￥" + redWinList.getIndexItemList().get(0).price);
            storeRedwineVIPprice1.setText("￥" + redWinList.getIndexItemList().get(0).memberPrice);
            storeRedwineText1.setText(redWinList.getIndexItemList().get(0).itemTitle);
        }
        if (null != makeupList&&null != makeupList&&null!=makeupList.getIndexItemList()
                &&makeupList.getIndexItemList().size()>0) {
            Glide.with(context).load(makeupList.getIndexItemList().get(0).homepageUrl).into((ImageView) storeMakeupPic);
            storeMakeupText.setText(makeupList.getIndexItemList().get(0).itemTitle);
            storeMakeupPrice.setText("￥" + makeupList.getIndexItemList().get(0).price);
            storeMakeupVIPprice.setText("￥" + makeupList.getIndexItemList().get(0).memberPrice);
        }
    }

    /*白酒*/
    public void setWhiteWineData() {
        if (null != whiteWinList&&null != whiteWinList&&null!=whiteWinList.getIndexItemList()
                &&whiteWinList.getIndexItemList().size()>0) {
            if(whiteWinList.getIndexItemList().size()>1){
                Glide.with(context).load(whiteWinList.getIndexItemList().get(1).homepageUrl).into((ImageView) storeWhiteWinePic2);
                storeWhiteWinePrice2.setText("￥" + whiteWinList.getIndexItemList().get(1).price);
                storeWhiteWineVIPprice2.setText("￥" + whiteWinList.getIndexItemList().get(1).memberPrice);
                storeWhiteWineText2.setText("￥" + whiteWinList.getIndexItemList().get(1).itemTitle);

            }
            Glide.with(context).load(whiteWinList.getIndexItemList().get(0).homepageUrl).into((ImageView) storeWhiteWinePic1);
            storeWhiteWinePrice1.setText("￥" + whiteWinList.getIndexItemList().get(0).price);
            storeWhiteWineVIPprice1.setText("￥" + whiteWinList.getIndexItemList().get(0).memberPrice);
            storeWhiteWineText1.setText("￥" + whiteWinList.getIndexItemList().get(0).itemTitle);
        }

    }

    public void onFirstUserVisible() {
        getData();
    }

    public void onUserVisible() {
        getData();
    }

    public void onFirstUserInvisible() {
    }

    public void onUserInvisible() {
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    /*tab商品分类目录*/
    private void initGirdAdapter() {
        GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局
        storeGridRecycler.setLayoutManager(mLayoutManager);
        StorGirdAdapter GirdAdapter = new StorGirdAdapter(R.layout.item_home_gird, listCategory, mContext);
//        GirdAdapter.openLoadAnimation();
        GirdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString("Classname", listCategory.get(position).name);
                paras.putString("ClassId", listCategory.get(position).id);
                LogUtils.e("发送商品分类id", listCategory.get(position).id);
                if ("4".equals(listCategory.get(position).id)) {
                    goNext(PagListAct.class, paras, false);
                } else
                    goNext(GoodsClassifyAct.class, paras, false);
            }
        });
        storeGridRecycler.setAdapter(GirdAdapter);
    }

    /*横向滑动*/
    private void initLandAdapter() {
        landList.setHasFixedSize(true);
        landList.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(mContext);
        linearLayoutManager2.setOrientation(0);
        landList.setLayoutManager(linearLayoutManager2);

        StoreBagAdapter storeAdapter = new StoreBagAdapter(R.layout.item_land_store, pagList.getIndexItemList(), context,w,height);
//        storeAdapter.openLoadAnimation();
        storeAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString(Constants.GOODSID, pagList.getIndexItemList().get(position).getId());
                goNext(GoodsInfoAct.class, paras, false);
            }
        });
        landList.setAdapter(storeAdapter);
    }


    public void setBannerData() {
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(w,w*10/17));
        viewPager.setAdapter(new AutoScrollPagerAdapter() {
            @Override
            public void onBindView(View itemView, int position) {
                Glide.with(mContext).load(storeBannerList.get(position).getFirstPrics().get(0)).into((ImageView) itemView);
            }

            @Override
            public int getCount() {
                return storeBannerList.size();
            }

            @Override
            public int onLayoutId() {
                return R.layout.image_view;
            }
        });
        viewPager.autoScroll();
        viewPager.setOnItemClickListener(new AutoScrollBase.OnItemClickListener() {
            @Override
            public void onItemClick(int index, View view) {
                //点击banner\
                LogUtils.e("index", storeBannerList.get(index).getUrl());
                paras.putString(Constants.GOODSID, storeBannerList.get(index).getUrl());
                goNext(GoodsInfoAct.class, paras, false);
            }
        });
        viewPager.setIndictorAdapter(new PageControlBase.Adapter() {
            @Override
            public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                RoundedTextView textView = new RoundedTextView(getContext(), null);
                textView.setTextColor(-0x1);
                textView.setTextSize(10.0f);
                textView.setRadius(util.dp2px(5.0f));
                textView.setLayoutParams(new ViewGroup.LayoutParams((int) util.dp2px(20), (int) util.dp2px(0.0f)));
                return new PageControlBase.ViewHolder(textView);
            }

            public void onBindViewHolder(PageControlBase.ViewHolder holder, int position, int currentPosition) {
                RoundedTextView textView = (RoundedTextView) holder.itemView;
                if (position == currentPosition) {
                    textView.setFillColor(Color.parseColor("#3ca7d8"));
                    return;
                }
                textView.setFillColor(Color.parseColor("#eeeeee"));
            }
        });
    }

    @OnClick({R.id.stor_more_redwine, R.id.ll_store_redwine2, R.id.ll_store_redwine1,
            R.id.stor_more_whiteWine, R.id.ll_store_whiteWine1, R.id.ll_store_whiteWine2,
            R.id.store_more_makeup, R.id.ll_store_makeup, R.id.stor_more_backpage,
            R.id.et_home_serch})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stor_more_redwine:
                paras.putString("Classname", "红酒");
                paras.putString("ClassId", redWinList.getId());
                goNext(GoodsClassifyAct.class, paras, false);
                break;
            case R.id.ll_store_redwine2:
                paras.putString(Constants.GOODSID, redWinList.getIndexItemList().get(1).getId());
                goNext(GoodsInfoAct.class, paras, false);
                break;
            case R.id.ll_store_redwine1:
                paras.putString(Constants.GOODSID, redWinList.getIndexItemList().get(0).getId());
                goNext(GoodsInfoAct.class, paras, false);
                break;
            case R.id.stor_more_whiteWine:
                paras.putString("Classname", "白酒");
                paras.putString("ClassId", whiteWinList.getId());
                goNext(GoodsClassifyAct.class, paras, false);
                break;
            case R.id.ll_store_whiteWine1:
                paras.putString(Constants.GOODSID, whiteWinList.getIndexItemList().get(0).getId());
                goNext(GoodsInfoAct.class, paras, false);
                break;
            case R.id.ll_store_whiteWine2:
                paras.putString(Constants.GOODSID, whiteWinList.getIndexItemList().get(1).getId());
                goNext(GoodsInfoAct.class, paras, false);
                break;
            case R.id.store_more_makeup:
                paras.putString("Classname", makeupList.getName());
                paras.putString("ClassId", makeupList.getId());
                goNext(GoodsClassifyAct.class, paras, false);
                break;
            case R.id.ll_store_makeup:
                paras.putString(Constants.GOODSID, makeupList.getIndexItemList().get(0).getId());
                goNext(GoodsInfoAct.class, paras, false);
                break;
            case R.id.stor_more_backpage:
                paras.putString("Classname", pagList.getName());
                paras.putString("ClassId", pagList.getId());
                goNext(PagListAct.class, paras, false);
                break;
            case R.id.et_home_serch:
                goNext(SearchActivity.class);
                break;
        }
    }

    @OnClick(R.id.et_home_serch)
    public void onViewClicked() {
    }
}

