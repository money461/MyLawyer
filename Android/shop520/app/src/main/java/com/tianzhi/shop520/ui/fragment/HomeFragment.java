package com.tianzhi.shop520.ui.fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
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
import com.tianzhi.shop520.entity.home.ContentCategoryList;
import com.tianzhi.shop520.entity.home.HomeEntity;
import com.tianzhi.shop520.entity.home.HomeListEntity;
import com.tianzhi.shop520.entity.store.ListCategoryItem;
import com.tianzhi.shop520.ui.activity.SearchActivity;
import com.tianzhi.shop520.ui.activity.personal.WebViewAct;
import com.tianzhi.shop520.ui.activity.store.GoodsClassifyAct;
import com.tianzhi.shop520.ui.activity.store.PagListAct;
import com.tianzhi.shop520.ui.adapter.HomeGirdAdapter;
import com.tianzhi.shop520.ui.adapter.HomeGirdAdp2;
import com.tianzhi.shop520.ui.adapter.HomeInfoAdapter;
import com.tianzhi.shop520.ui.adapter.HomeListAdapter;
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
 * Created by thinkpad on 2017/10/14.
 */

public class HomeFragment extends BaseLazyFragment {
    Context mContext;
    @BindView(R.id.viewPager)
    AutoScrollViewPager viewPager;
    @BindView(R.id.home_grid_recycler)
    RecyclerView homeGridRecycler;
    @BindView(R.id.home_more_news)
    TextView homeMoreNews;
    @BindView(R.id.rv_list_news)
    RecyclerView rvListNews;
    @BindView(R.id.home_more_info)
    TextView homeMoreInfo;
    @BindView(R.id.rv_list_info)
    RecyclerView rvListInfo;
    @BindView(R.id.et_home_serch)
    TextView etHomeSerch;
    @BindView(R.id.space)
    TextView space;
    @BindView(R.id.home_grid_recycler2)
    RecyclerView homeGridRecycler2;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.textView2)
    TextView textView2;
    //    private ArrayList<HomeNewsItem> mDataList2;
    private Util util;
    View view;

    //    private String[] banner_list = new String[]{"http://img.zcool.cn/community/011f4356d56e336ac7252ce6e85df4.jpg", "http://img.zcool.cn/community/01723d57bc1c8d0000018c1b259faf.png", "http://img.zcool.cn/community/01135556d56e2832f875520f4c7f18.jpg"};
    private CoordinatorLayout coordinatorLayout;
    private GridLayoutManager mLayoutManager;
    private HomeEntity homeEntity;
    private HomeEntity acacheHomeEntity;
    ArrayList<HomeListEntity> newsList;
    ArrayList<HomeListEntity> bannerList;
    ArrayList<HomeListEntity> infoList;
    private List<ListCategoryItem> listCategory;//商品分类目录1
    private List<ContentCategoryList> listCategory2;//商品分类目录2
    ACache aCache;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = this.getActivity();
        aCache = ACache.get(mContext);
        util = new Util(getContext());
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        homeGridRecycler.setHasFixedSize(true);
        homeGridRecycler.setNestedScrollingEnabled(false);
        rvListNews.setHasFixedSize(true);
        rvListNews.setNestedScrollingEnabled(false);
        rvListInfo.setHasFixedSize(true);
        rvListInfo.setNestedScrollingEnabled(false);
        homeGridRecycler2.setHasFixedSize(true);
        homeGridRecycler2.setNestedScrollingEnabled(false);

        initView();
        return view;
    }

    private void initView() {
        acacheHomeEntity = (HomeEntity) aCache.getAsObject(Constants.HOMELIST);
        if (null != acacheHomeEntity&&null!=acacheHomeEntity.getHomePageContent()&&acacheHomeEntity.getHomePageContent().size()>0) {
            LogUtils.e("首页数据", acacheHomeEntity.toString());
            setData(acacheHomeEntity);
        }
        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.APPINDEX)
                .execute(new DialogCallback<BaseResponse<HomeEntity>>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponse<HomeEntity> homeEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(homeEntityBaseResponse, call, response);
                        homeEntity = homeEntityBaseResponse.data;
                        aCache.put(Constants.HOMELIST, homeEntity);
//                        homeEntity = (HomeEntity) aCache.getAsObject(Constants.HOMELIST);
                        LogUtils.e("首页请求的数据", homeEntity.toString());
                        setData(homeEntity);
                    }
                });
    }

    private void setData(HomeEntity homeEntity) {
        bannerList = homeEntity.getHomePageContent().get(0).contentList;
        newsList = homeEntity.getHomePageContent().get(1).contentList;
        infoList = homeEntity.getHomePageContent().get(2).contentList;
        setBannerData();
        initNewsAdapter();
        listCategory = homeEntity.getItemCategoryList();
        listCategory2 = homeEntity.getContentCategoryList();
        initGirdAdapter();
    }

    /*首页上面四个个tab*/
    private void initGirdAdapter() {
        mLayoutManager = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);//设置为一个4列的纵向网格布局
        homeGridRecycler.setLayoutManager(mLayoutManager);
        HomeGirdAdapter homeGirdAdapter = new HomeGirdAdapter(R.layout.item_home_gird, listCategory,mContext);
        homeGirdAdapter.openLoadAnimation();
        homeGirdAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
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
        GridLayoutManager mLayoutManager2  = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);//设置为一个3列的纵向网格布局;
        homeGridRecycler2.setLayoutManager(mLayoutManager2);
        HomeGirdAdp2 homeGirdAdapter2 = new HomeGirdAdp2(R.layout.item_home_gird, listCategory2,mContext);
        homeGirdAdapter2.openLoadAnimation();
        homeGirdAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString("Classname",listCategory2.get(position).getName());
                paras.putString("linkAddress", listCategory2.get(position).getLinkAddress());
                    goNext(WebViewAct.class, paras, false);
            }
        });
        homeGridRecycler.setAdapter(homeGirdAdapter);
        homeGridRecycler2.setAdapter(homeGirdAdapter2);
    }

    /*新闻*/
    private void initNewsAdapter() {
        rvListNews.setLayoutManager(new LinearLayoutManager(mContext));
        rvListNews.setHasFixedSize(true);
        HomeListAdapter homeListAdapter = new HomeListAdapter(R.layout.item_home_list, newsList, mContext);
//        homeListAdapter.openLoadAnimation();
        homeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString("Classname","新闻");
                paras.putString("linkAddress", newsList.get(position).getUrl());
                goNext(WebViewAct.class, paras, false);
            }
        });
        rvListNews.setAdapter(homeListAdapter);
        rvListInfo.setLayoutManager(new LinearLayoutManager(mContext));
        rvListInfo.setHasFixedSize(true);
        HomeInfoAdapter homeInfoAdapter = new HomeInfoAdapter(R.layout.item_home_info, infoList, mContext);
//        homeInfoAdapter.openLoadAnimation();
        homeInfoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                paras.putString("Classname","资讯");
                paras.putString("linkAddress", infoList.get(position).getUrl());
                goNext(WebViewAct.class, paras, false);
            }
        });
        rvListInfo.setAdapter(homeInfoAdapter);

    }


    public void setBannerData() {
        DisplayMetrics dm = new DisplayMetrics();// 获取手机分辨率
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect frame = new Rect();
        this.getActivity().getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        // 高度
        int height = dm.heightPixels;
        int w = dm.widthPixels;
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(w,w*10/17));
        viewPager.setAdapter(new AutoScrollPagerAdapter() {
            @Override
            public void onBindView(View itemView, int position) {
                if(null!=bannerList.get(position).getFirstPrics()&&bannerList.get(position).getFirstPrics().size()>0){
                    Glide.with(mContext).
                            load(bannerList.get(position).getFirstPrics().get(0))
                            .into((ImageView) itemView);
                }
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
//                switch (view.getId()) {
//                    case R.id.item_context:
//                        paras.putString("code", orderList.get(position).code);
//                        paras.putString("page","");
//                        goNext(OrderInfoAct.class, paras, false);
//                        break;
//                }
            }
        });
        viewPager.setIndictorAdapter(new PageControlBase.Adapter() {
            @Override
            public PageControlBase.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                RoundedTextView textView = new RoundedTextView(getContext(), null);
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

    public void onFirstUserVisible() {
//        initView();
    }

    public void onUserVisible() {
    }

    public void onFirstUserInvisible() {
    }

    public void onUserInvisible() {
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    //
//    /**
//     * 商品分类目录
//     */
//    public void getlistCategory() {
//        OkGo.get(BaseConstant.GoodsUrl + BaseConstant.LISTCATEGORY)
//                .tag(this)
//                .execute(new DialogCallback<BaseResponse<List<ListCategoryItem>>>(this.getActivity(), true) {
//                    @Override
//                    public void onSuccess(BaseResponse<List<ListCategoryItem>> listBaseResponse, Call call, Response response) {
//                        super.onSuccess(listBaseResponse, call, response);
//                        listCategory = new ArrayList<ListCategoryItem>();
//                        listCategory = listBaseResponse.data;
//                        listCategory = listCategory.subList(0, 8);
//                        for (int i = 0; i < listCategory.size(); i++) {
//                            listCategory.get(i).imageResource = IMG[i];
//                        }
//                        LogUtils.e("商品分类", listBaseResponse.toString());
//                        initGirdAdapter();
//                    }
//                });
//    }
    @OnClick(R.id.et_home_serch)
    public void onViewClicked() {
        goNext(SearchActivity.class);
    }
}
