package com.tianzhi.shop520.ui.activity.personal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.entity.login.UserInfo;
import com.tianzhi.shop520.ui.diyview.BaseImageView;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.Constants;

import butterknife.BindView;

/**
 * Created by thinkpad on 2017/10/20.
 * 会员中心
 */

public class MemberCenterAct extends BaseFragmentActivity {
    ACache aCache;
    @BindView(R.id.Iv_head)
    BaseImageView IvHead;
    @BindView(R.id.user_nike)
    TextView userNike;
    @BindView(R.id.tv_user_type)
    ImageView tvUserType;
    @BindView(R.id.tv_type_text)
    TextView tvTypeText;
    @BindView(R.id.tv_user_typetext)
    TextView tvUserTypetext;
    @BindView(R.id.tv_user_nexttypetext)
    TextView tvUserNexttypetext;
    @BindView(R.id.text_recommend_person)
    TextView textRecommendPerson;
    int num;
    Context context;
    UserInfo userInfo;
    @BindView(R.id.next_ll)
    LinearLayout nextLl;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentLayout(R.layout.act_member_center);
        aCache = ACache.get(context);
        initView();
    }

    protected void initView() {
        setActivityTitle("会员中心");
        if (null != (UserInfo) aCache.getAsObject(Constants.USERINFO)) {
            userInfo = (UserInfo) aCache.getAsObject(Constants.USERINFO);
        } else
            userInfo = AppShared.getInstance(context).getLoginInfo();

        if (null != userInfo) {
             /*会员昵称*/
            if (!TextUtils.isEmpty(userInfo.userNick)) {
                userNike.setText(userInfo.userNick);
            }
        /*会员类型*/
            if (!TextUtils.isEmpty(userInfo.type)) {
                if ("0".equals(userInfo.type)) {
                    tvTypeText.setText("普通用户");
                    tvUserTypetext.setText("普通用户");
                    tvUserNexttypetext.setText("会员");
                    nextLl.setVisibility(View.VISIBLE);
                } else if ("1".equals(userInfo.type)) {
                    tvTypeText.setText("会员");
                    tvUserTypetext.setText("会员");
                    tvUserNexttypetext.setText("爱心会员");
                    nextLl.setVisibility(View.VISIBLE);
                    tvUserType.setBackgroundResource(R.drawable.member1);
                    if (null != userInfo.recommendTotal) {
                        num = (2 - Integer.parseInt(userInfo.recommendTotal));
                    }
                    textRecommendPerson.setText("升级到爱心会员还需推荐：" + num + "人");
                } else if ("2".equals(userInfo.type)) {
                    tvTypeText.setText("爱心会员");
                    tvUserTypetext.setText("爱心会员");
                    tvUserNexttypetext.setText("爱心合伙人");
                    nextLl.setVisibility(View.VISIBLE);
                    tvUserType.setBackgroundResource(R.drawable.member2);
                    if (null != userInfo.recommendTotal) {
                        num = (10 - Integer.parseInt(userInfo.recommendTotal));
                    }
                    textRecommendPerson.setText("升级到爱心合伙人还需推荐：" + num + "人");
                } else if ("3".equals(userInfo.type)) {
                    tvTypeText.setText("爱心合伙人");
                    tvUserTypetext.setText("爱心合伙人");
                    tvUserNexttypetext.setText("城市爱心合伙人");
                    tvUserType.setBackgroundResource(R.drawable.member3);
                    textRecommendPerson.setText("您目前已推荐:" + userInfo.recommendTotal + "人");
//                    textRecommendPerson.setText("升级到城市爱心合伙人还需推荐："+userInfo.recommendTotal+"人");
                } else if ("4".equals(userInfo.type)) {
                    tvTypeText.setText("城市爱心合伙人");
                    tvUserTypetext.setText("城市爱心合伙人");
                    nextLl.setVisibility(View.GONE);
                    tvUserType.setBackgroundResource(R.drawable.member4);
                    textRecommendPerson.setText("您目前已推荐:" + userInfo.recommendTotal + "人");
                }
            }

              /*用户头像*/
            if (!TextUtils.isEmpty(userInfo.headUrl)) {
                Glide.with(context).load(userInfo.headUrl).into(IvHead);
            } else {
                if (!TextUtils.isEmpty(userInfo.userSex)) {
                    if ("0".equals(userInfo.userSex)) {
                        Glide.with(context).load(R.drawable.user_default).into(IvHead);
                    } else if ("1".equals(userInfo.userSex)) {
                        Glide.with(context).load(R.drawable.user_default_avatar_women).into(IvHead);
                    }
                }
            }
        }
    }

}
