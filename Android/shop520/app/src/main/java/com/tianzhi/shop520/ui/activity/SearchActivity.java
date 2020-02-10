package com.tianzhi.shop520.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.ui.activity.store.SearchListActivity;
import com.tianzhi.shop520.util.ACache;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by thinkpad on 2017/11/7.
 * 搜索界面
 */

public class SearchActivity extends BaseFragmentActivity {
    @BindView(R.id.hot_tab1)
    TextView hotTab1;
    @BindView(R.id.hot_tab2)
    TextView hotTab2;
    @BindView(R.id.hot_tab3)
    TextView hotTab3;
    @BindView(R.id.id_flowlayout)
    TagFlowLayout idFlowlayout;
    private List<String> mVals = new ArrayList<>();
    private TagFlowLayout mFlowLayout;
    ACache aCache;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentLayout(R.layout.act_search);
        initView();
        aCache = ACache.get(mContext);
    }

    @Override
    protected void initView() {
//        aCache.put("history", (Serializable) mVals);
//        mVals = (List<String>) aCache.getAsObject("history");
        final LayoutInflater mInflater = LayoutInflater.from(context);
        //显示搜索框
        setActivityShowSearch(true);
        setRightButtonShow(true);
//        setRightButtonName("搜索");
        rightBtn.setText("搜索");
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(etTitleSearch.getText().toString())) {
                    if (null != mVals && mVals.size() > 0) {
                        if (!mVals.contains(etTitleSearch.getText().toString())) {
                            mVals.add(etTitleSearch.getText().toString());
                        }
                    }
                    mVals.add(etTitleSearch.getText().toString());
                    aCache.put("history", (Serializable) mVals);
                }
                paras.putString("itemTitle", etTitleSearch.getText().toString());
                goNext(SearchListActivity.class, paras, false);
            }
        });
        idFlowlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        idFlowlayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_search,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }

        });
    }

    @OnClick({R.id.hot_tab1, R.id.hot_tab2, R.id.hot_tab3})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.hot_tab1:
                paras.putString("itemTitle", hotTab1.getText().toString());
                goNext(SearchListActivity.class, paras, false);
                break;
            case R.id.hot_tab2:
                paras.putString("itemTitle", hotTab2.getText().toString());
                goNext(SearchListActivity.class, paras, false);
                break;
            case R.id.hot_tab3:
                paras.putString("itemTitle", hotTab3.getText().toString());
                goNext(SearchListActivity.class, paras, false);
                break;
        }
    }
}
