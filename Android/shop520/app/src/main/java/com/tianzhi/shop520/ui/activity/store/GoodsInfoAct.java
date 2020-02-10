package com.tianzhi.shop520.ui.activity.store;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.shop.CarInfoEntity;
import com.tianzhi.shop520.entity.shop.CartListItem;
import com.tianzhi.shop520.entity.shop.ShopCarEntity;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.entity.store.GoodsInfoEntity;
import com.tianzhi.shop520.ui.activity.login.LoginAct;
import com.tianzhi.shop520.ui.activity.order.EnsureOrderAct;
import com.tianzhi.shop520.ui.adapter.GoodsInfoAdp;
import com.tianzhi.shop520.ui.adapter.StoreBagAdapter;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollBase;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollPagerAdapter;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.AutoScrollViewPager;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.PageControlBase;
import com.tianzhi.shop520.ui.diyview.autoscrollviewpager.RoundedTextView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by thinkpad on 2017/10/20.
 * 商品详情
 */

public class GoodsInfoAct extends BaseFragmentActivity {

    @BindView(R.id.viewPager)
    AutoScrollViewPager viewPager;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_info)
    TextView tvGoodsInfo;
    @BindView(R.id.goods_vip_price)
    TextView goodsVipPrice;
    @BindView(R.id.goods_price)
    TextView goodsPrice;
    @BindView(R.id.land_list)
    RecyclerView landList;
    @BindView(R.id.addto_shopcar)
    TextView addtoShopcar;
    @BindView(R.id.btnto_bug)
    TextView btntoBug;
    String goodsId;
    GoodsInfoEntity goodsInfoEntity;
    List<GoodsInfoEntity> pagList;
    ArrayList<CartListItem> cartlistitems;//存储的上传服务器信息
    ArrayList<ShopCarEntity> shopCarList;//本地购物车信息
    ShopCarEntity shopCarEntity;
    ArrayList<CarInfoEntity> CarItemlist;//购物车分类
    @BindView(R.id.iv_goods_info)
    ImageView ivGoodsInfo;
    @BindView(R.id.rcl_goods_info)
    RecyclerView rclGoodsInfo;
    @BindView(R.id.go_shipping)
    TextView goShipping;
    @BindView(R.id.goods_sellnum)
    TextView goodsSellnum;
    //    @BindView(R.id.ll_context)
//    LinearLayout llContext;
    private int ClassProstion;//记录购物车缓存 分类下标
    ACache aCache;
    ArrayList<String> bannerList;
    private Util util;
    private EnorderEntity enorderEntity;//确认订单  接口返回
    public ArrayList<String> prictures;
    GoodsInfoAdp homeListAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_goodsinfo);
        initView();
        util = new Util(this);
        aCache = ACache.get(context);
        landList.setHasFixedSize(true);
        landList.setNestedScrollingEnabled(false);
        rclGoodsInfo.setHasFixedSize(true);
        rclGoodsInfo.setNestedScrollingEnabled(false);
    }

    protected void initView() {
        setActivityTitle("商品详情");
        goodsId = paras.getString(Constants.GOODSID);
        getdata();
        getRecommendItem();
    }

    /*商品推荐*/
    public void getRecommendItem() {
        showLoading();
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.RECOMMENDITEM)
                .tag(this)
                .params("i", "3")
                .execute(new DialogCallback<BaseResponse<List<GoodsInfoEntity>>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<List<GoodsInfoEntity>> listBaseResponse, Call call, Response response) {
                        super.onSuccess(listBaseResponse, call, response);
                        dismissLoading();
                        pagList = listBaseResponse.data;
                        initLandAdapter();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });

    }

    /*获取商品详情*/
    private void getdata() {
        LogUtils.e("商品id", goodsId);
        showLoading();
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.QUERYITEMDETAILBYID)
                .tag(this)
                .params("id", goodsId)
                .execute(new DialogCallback<BaseResponse<GoodsInfoEntity>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<GoodsInfoEntity> goodsInfoEntityBaseConstant, Call call, Response response) {
                        super.onSuccess(goodsInfoEntityBaseConstant, call, response);
                        dismissLoading();
                        goodsInfoEntity = goodsInfoEntityBaseConstant.data;
                        bannerList = goodsInfoEntity.getImages();
                        prictures = goodsInfoEntity.getPrictures();
                        tvGoodsName.setText(goodsInfoEntity.getItemTitle());
                        tvGoodsInfo.setText(goodsInfoEntity.getDescription());
                        goodsSellnum.setText("已出售："+goodsInfoEntity.salesNum+"件");
                        goodsVipPrice.setText("￥" + goodsInfoEntity.getMemberPrice());
                        goodsPrice.setText("￥" + goodsInfoEntity.getPrice());
                        setBannerData();
                        setPicAdapter();
//                        ivGoodsInfo
//                        Glide.with(context).load(goodsInfoEntity.getPrictures().get(0)).into(ivGoodsInfo);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    //    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if(webview_projectinfo != null){
//            webview_projectinfo.removeAllViews();
//            webview_projectinfo.destroy();
//            webview_projectinfo = null;
//            ll_webview.removeAllViews();
//            ll_webview = null;
//        }
//    }
    public void setPicAdapter() {
        rclGoodsInfo.setLayoutManager(new LinearLayoutManager(context));
        rclGoodsInfo.setHasFixedSize(true);
         homeListAdapter = new GoodsInfoAdp(GoodsInfoAct.this, prictures);
//        homeListAdapter.openLoadAnimation();
        rclGoodsInfo.setAdapter(homeListAdapter);

    }

    public void setBannerData() {
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
        int height = dm.heightPixels;
        int w = dm.widthPixels;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(w, w));
        viewPager.setAdapter(new AutoScrollPagerAdapter() {
            @Override
            public void onBindView(View itemView, int position) {
                Glide.with(context).load(bannerList.get(position)).into((ImageView) itemView);
            }

            @Override
            public int getCount() {
                return bannerList.size();
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
            }
        });
        viewPager.setIndictorAdapter(new PageControlBase.Adapter() {
            @Override
            public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                RoundedTextView textView = new RoundedTextView(context, null);
                textView.setTextColor(-0x1);
                textView.setTextSize(10.0f);
                textView.setRadius(util.dp2px(5.0f));
                textView.setLayoutParams(new ViewGroup.LayoutParams((int) util.dp2px(20), (int) util.dp2px(20)));
                return new PageControlBase.ViewHolder(textView);
            }

            @Override
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
        StoreBagAdapter storeAdapter = new StoreBagAdapter(R.layout.item_land_store, pagList, context, w, height);
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

    @OnClick({R.id.addto_shopcar, R.id.btnto_bug, R.id.go_shipping})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addto_shopcar://加入购物车
                if (APPLICATION.isLogin) {//登录
                    // 调用添加购物车接口
                    addCart();
                } else {//缓存本地
                    addCartoAcache();
                }
                break;
            case R.id.btnto_bug:
                if (!APPLICATION.isLogin) {
                    goNext(LoginAct.class);
                    return;
                }
                ArrayList<orderListItem> orderListItems = new ArrayList<>();
                orderListItem orderListItem = new orderListItem();
                orderListItem.setId(goodsInfoEntity.getId());
                orderListItem.setNum(1);
                orderListItems.add(orderListItem);
                validateOrder(orderListItems);
                break;
            case R.id.go_shipping:
                goNext(ShoppingCarAct.class, paras, true);
                break;
        }
    }

    /*确认订单*/
    private void validateOrder(ArrayList<orderListItem> cartlistitems) {
        Gson gson = new Gson();
        String obj = gson.toJson(cartlistitems);
        LogUtils.e("确认订单参数", obj.toString());
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.VALIDATEORDER)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("items", obj)
                .params("type", "1")
                .execute(new DialogCallback<BaseResponse<EnorderEntity>>(this, false) {
                    @Override
                    public void onSuccess(BaseResponse<EnorderEntity> enorderEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(enorderEntityBaseResponse, call, response);
                        dismissLoading();
                        enorderEntity = enorderEntityBaseResponse.data;
                        paras.putSerializable("enorderEntity", (Serializable) enorderEntity);
                        paras.putString("type", "1");
                        goNext(EnsureOrderAct.class, paras, false);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
    /*判断本地缓存是够存在该商品*/
    private int findGoods(){
        int size = shopCarList.get(ClassProstion).getCartList().size();
        ArrayList<CarInfoEntity> itemList = shopCarList.get(ClassProstion).cartList;
        for (int j = 0; j < size; j++) {
            if (goodsInfoEntity.getId().equals(itemList.get(j).getItemId())) {//存在该商品
                return j;
            }
        }
        return -1;
    }

    /*判断本地缓存是够存在该分类*/
    private boolean findClassItem(ArrayList<ShopCarEntity> shopCarList) {
        for (int i = 0; i < shopCarList.size(); i++) {
            if (shopCarList.get(i).getId().equals(goodsInfoEntity.getCategoryId())) {
                ClassProstion = i;
                return true;
            }
        }
        return false;
    }

    /*未登录时保存本地购物车*/
    private void addCartoAcache() {
        shopCarList = (ArrayList<ShopCarEntity>) aCache.getAsObject(Constants.SHOPCAR);
        if (null != shopCarList && shopCarList.size() > 0) {
            if (findClassItem(shopCarList)) {//判断本地购物车是否存在该分类
//                int size = shopCarList.get(ClassProstion).getCartList().size();
                ArrayList<CarInfoEntity> itemList = shopCarList.get(ClassProstion).cartList;
//                for (int j = 0; j < size; j++) {
                int perstion = findGoods();
                    if (-1 != perstion ) {//存在该商品
                        int num = itemList.get(perstion).getNum();
                        itemList.get(perstion).setNum(num + 1);
                    } else {
                        CarInfoEntity goodsInfo1 = new CarInfoEntity();
                        goodsInfo1.setItemId(goodsInfoEntity.getId());
                        goodsInfo1.setItemTitle(goodsInfoEntity.getItemTitle());
                        goodsInfo1.setDescription(goodsInfoEntity.getDescription());
                        goodsInfo1.setPrice(goodsInfoEntity.getPrice());
                        goodsInfo1.setMemberPrice(goodsInfoEntity.getMemberPrice());
                        goodsInfo1.setHomepageUrl(goodsInfoEntity.getHomepageUrl());
                        goodsInfo1.setNum(1);
                        itemList.add(goodsInfo1);
                    }
            } else {
                //本地购物车添加分类
                AddcarItem();
            }
        } else {
            shopCarList = new ArrayList<>();
            //本地购物车添加分类
            AddcarItem();
        }
        aCache.put(Constants.SHOPCAR, shopCarList);
        toast("加入购物车成功");
        Gson gson = new Gson();
        String obj = gson.toJson(shopCarList);
        LogUtils.e("购物车3", obj);

    }


    /*登录时 上传购物车信息*/
    private void addCart() {
        cartlistitems = new ArrayList();
        CartListItem caritem = new CartListItem();
        caritem.setItemId(goodsInfoEntity.id);
        caritem.setNum(1);
        cartlistitems.add(caritem);
        Gson gson1 = new Gson();
        String obj1 = gson1.toJson(cartlistitems);
        showLoading();
        OkGo.post(BaseConstant.GoodsUrl + BaseConstant.ADDCART)
                .params("cartList", obj1)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this, false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        if ("200".equals(baseResponseData.flag)) {
                            LogUtils.e("加入购物车",baseResponseData.toString());
                            toast(baseResponseData.msg);
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }

    //    /*本地购物车添加分类*/
    private void AddcarItem() {
        shopCarEntity = new ShopCarEntity();
        shopCarEntity.setId(goodsInfoEntity.getCategoryId());
        shopCarEntity.setName(goodsInfoEntity.getCategoryName());
        CarItemlist = new ArrayList<>();
        CarItemlist.clear();
        CarInfoEntity goodsInfo = new CarInfoEntity();
        goodsInfo.setItemId(goodsInfoEntity.getId());
        goodsInfo.setItemTitle(goodsInfoEntity.getItemTitle());
        goodsInfo.setDescription(goodsInfoEntity.getDescription());
        goodsInfo.setPrice(goodsInfoEntity.getPrice());
        goodsInfo.setMemberPrice(goodsInfoEntity.getMemberPrice());
        goodsInfo.setHomepageUrl(goodsInfoEntity.getHomepageUrl());
        goodsInfo.setNum(1);
        CarItemlist.add(goodsInfo);
        shopCarEntity.setCartList(CarItemlist);
        shopCarList.add(shopCarEntity);
        LogUtils.e("购物车4", shopCarList.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public static void recycleImageView(View view){
        if(view==null) return;
        if(view instanceof ImageView){
            Drawable drawable=((ImageView) view).getDrawable();
            if(drawable instanceof BitmapDrawable){
                Bitmap bmp = ((BitmapDrawable)drawable).getBitmap();
                if (bmp != null && !bmp.isRecycled()){
                    ((ImageView) view).setImageBitmap(null);
                    bmp.recycle();
                    LogUtils.e("have recycled ImageView Bitmap","have recycled ImageView Bitmap");
                    bmp=null;
                }
            }
        }
    }
}
