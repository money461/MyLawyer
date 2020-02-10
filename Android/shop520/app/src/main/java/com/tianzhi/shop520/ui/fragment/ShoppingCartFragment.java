package com.tianzhi.shop520.ui.fragment;

/**
 * Created by thinkpad on 2017/10/25.
 * 购物车tab
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.APPLICATION;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseLazyFragment;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.shop.CarInfoEntity;
import com.tianzhi.shop520.entity.shop.CartListItem;
import com.tianzhi.shop520.entity.shop.ShopCarEntity;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.ui.activity.login.LoginAct;
import com.tianzhi.shop520.ui.activity.order.EnsureOrderAct;
import com.tianzhi.shop520.ui.adapter.ShopcartAdapter;
import com.tianzhi.shop520.util.ACache;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.onekeyshare.OnekeyShare;
import okhttp3.Call;
import okhttp3.Response;


public class ShoppingCartFragment extends BaseLazyFragment implements ShopcartAdapter.CheckInterface,
        ShopcartAdapter.ModifyCountInterface {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.subtitle)
    TextView subtitle;
    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.exListView)
    ExpandableListView exListView;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.all_chekbox)
    CheckBox allChekbox;
    @BindView(R.id.tv_delete)
    TextView tvDelete;
    @BindView(R.id.tv_go_to_pay)
    TextView tvGoToPay;
    @BindView(R.id.ll_shar)
    LinearLayout llShar;
    @BindView(R.id.ll_info)
    LinearLayout llInfo;
    @BindView(R.id.tv_share)
    TextView tvShare;
    @BindView(R.id.ll_cart)
    LinearLayout llCart;
    @BindView(R.id.layout_cart_empty)
    LinearLayout cart_empty;
    @BindView(R.id.leftBtn)
    Button leftBtn;
    private Context context;
    private double totalPrice = 0.00;// 购买的商品总价
    private int totalCount = 0;// 购买的商品总数量
    private ShopcartAdapter selva;
    private List<ShopCarEntity> groups = new ArrayList<ShopCarEntity>();// 组元素数据列表
    private Map<String, List<CarInfoEntity>> children = new HashMap<String, List<CarInfoEntity>>();// 子元素数据列表
    private int flag = 0;
    View view;
    ArrayList<ShopCarEntity> shopCarList;//购物车数据
    ACache aCache;
    private ArrayList<CarInfoEntity> checkedList;//选中的购物车
    private EnorderEntity enorderEntity;//确认订单  接口返回

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping, container, false);
        ButterKnife.bind(this, view);
        leftBtn.setVisibility(View.GONE);
        context = getActivity();
        aCache = ACache.get(context);
        return view;
    }

    /*服务器获取购物车数据*/
    public void getCarlist() {
        showLoading();
        OkGo.post(BaseConstant.GoodsUrl + BaseConstant.LISTCART)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogCallback<BaseResponse<ArrayList<ShopCarEntity>>>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponse<ArrayList<ShopCarEntity>> arrayListBaseResponse, Call call, Response response) {
                        super.onSuccess(arrayListBaseResponse, call, response);
                        dismissLoading();
                        shopCarList = arrayListBaseResponse.data;
//                        aCache.put(Constants.SHOPCAR,shopCarList);
                        initDatas();
                        initEvents();
                        setCartNum();
                        doCheckAll();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }


    private void initEvents() {
        selva = new ShopcartAdapter(groups, children, context, this.getActivity(), shopCarList);
        selva.setCheckInterface(this);// 关键步骤1,设置复选框接口
        selva.setModifyCountInterface(this);// 关键步骤2,设置数量增减接口
//        selva.setmListener(this);
        exListView.setAdapter(selva);
        for (int i = 0; i < selva.getGroupCount(); i++) {
            exListView.expandGroup(i);// 关键步骤3,初始化时，将ExpandableListView以展开的方式呈现
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    /**
     * 设置购物车产品数量
     */
    private void setCartNum() {
        int count = 0;
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            ShopCarEntity group = groups.get(i);
            List<CarInfoEntity> childs = children.get(group.getId());
            for (CarInfoEntity goodsInfo : childs) {
                count += 1;
            }
        }
        //购物车已清空
        if (count == 0) {
            clearCart();
        } else {
            cart_empty.setVisibility(View.GONE);
            subtitle.setVisibility(View.VISIBLE);
            llCart.setVisibility(View.VISIBLE);
            title.setText("购物车" + "(" + count + ")");
        }
    }

    private void clearCart() {
        title.setText("购物车" + "(" + 0 + ")");
        subtitle.setVisibility(View.GONE);
        llCart.setVisibility(View.GONE);
        cart_empty.setVisibility(View.VISIBLE);
    }

    /**
     * 模拟数据<br>
     * 遵循适配器的数据列表填充原则，组元素被放在一个List中，对应的组元素下辖的子元素被放在Map中，<br>
     * 其键是组元素的Id(通常是一个唯一指定组元素身份的值)
     */
    private void initDatas() {
        children.clear();
        groups.clear();
        if (null != shopCarList && shopCarList.size() > 0) {
            for (int i = 0; i < shopCarList.size(); i++) {
                groups.add(shopCarList.get(i));
                children.put(groups.get(i).getId(), shopCarList.get(i).getCartList());// 将组元素的一个唯一值，这里取Id，作为子元素List的Key
            }
        }
    }

    /**
     * 删除操作<br>
     * 1.不要边遍历边删除，容易出现数组越界的情况<br>
     * 2.现将要删除的对象放进相应的列表容器中，待遍历完后，以removeAll的方式进行删除
     */
    protected void doDelete() {
        List<ShopCarEntity> toBeDeleteGroups = new ArrayList<ShopCarEntity>();// 待删除的组元素列表
        StringBuffer deleteItemid = new StringBuffer();
        for (int i = 0; i < groups.size(); i++) {
            ShopCarEntity group = groups.get(i);
            if (group.isChoosed()) {
                toBeDeleteGroups.add(group);
            }
            List<CarInfoEntity> toBeDeleteProducts = new ArrayList<CarInfoEntity>();// 待删除的子元素列表
            List<CarInfoEntity> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                if (childs.get(j).isChoosed()) {
                    toBeDeleteProducts.add(childs.get(j));
                    deleteItemid.append(childs.get(j).getItemId() + ",");
                }
            }
            childs.removeAll(toBeDeleteProducts);
            //删除缓存中的子元素
            if (!APPLICATION.isLogin) {
                shopCarList.get(i).getCartList().removeAll(toBeDeleteProducts);
            }
        }
        groups.removeAll(toBeDeleteGroups);
        //记得重新设置购物车
        setCartNum();
        LogUtils.e("要删除的商品id", deleteItemid.toString());
        if (APPLICATION.isLogin) {//登录调用接口
            deleteCart(deleteItemid.toString());
        } else {//删除缓存中的组
//            shopCarList = (ArrayList<ShopCarEntity>)aCache.getAsObject(Constants.SHOPCAR);
            for (int i = 0; i < toBeDeleteGroups.size(); i++) {
                for (int j = 0; j < shopCarList.size(); j++) {
                    if (toBeDeleteGroups.get(i).getId().equals(shopCarList.get(j).getId())) {
                        shopCarList.remove(j);//删除组
                    }
                }
            }
        }
        aCache.put(Constants.SHOPCAR, shopCarList);
        selva.notifyDataSetChanged();
    }


    /*购物车数量修改*/
    public void updateCart(int currentCount, CarInfoEntity product) {
        CartListItem cartListItem = new CartListItem();
        cartListItem.setNum(currentCount);
        cartListItem.setItemId(product.getItemId());
        Gson gson = new Gson();
        String obj = gson.toJson(cartListItem);
        LogUtils.e("修改购物车", obj.toString());
        showLoading();
        OkGo.post(BaseConstant.GoodsUrl + BaseConstant.UPDATECART)
                .params("cart", obj)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        if (!"200".equals(baseResponseData.flag)) {
                            Toast.makeText(context, baseResponseData.msg, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
/*增加*/
    @Override
    public void doIncrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        CarInfoEntity product = (CarInfoEntity) selva.getChild(groupPosition,
                childPosition);

        int currentCount = product.getNum();
        currentCount++;
        product.setNum(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        if (APPLICATION.isLogin) {
            updateCart(currentCount, product);
        }else {
            //本地数量加1
            shopCarList.get(groupPosition).getCartList().get(childPosition).setNum(currentCount);
            aCache.put(Constants.SHOPCAR, shopCarList);
        }
        calculate();
    }
/*删减*/
    @Override
    public void doDecrease(int groupPosition, int childPosition,
                           View showCountView, boolean isChecked) {

        CarInfoEntity product = (CarInfoEntity) selva.getChild(groupPosition,
                childPosition);
        LogUtils.e("减", product.toString());
        int currentCount = product.getNum();
        if (currentCount == 1)
            return;
        currentCount--;
        product.setNum(currentCount);
        ((TextView) showCountView).setText(currentCount + "");
        selva.notifyDataSetChanged();
        if (APPLICATION.isLogin) {
            updateCart(currentCount, product);        }
        else {
            shopCarList.get(groupPosition).getCartList().get(childPosition).setNum(currentCount);
            aCache.put(Constants.SHOPCAR, shopCarList);
        }
        calculate();
    }

    @Override
    public void childDelete(int groupPosition, int childPosition) {
        LogUtils.e("groupPosition $$childPosition",groupPosition+","+childPosition);
        ShopCarEntity group = groups.get(groupPosition);
        List<CarInfoEntity> childs = children.get(group.getId());
        if (APPLICATION.isLogin) {
            deleteCart(groups.get(groupPosition).getCartList().get(childPosition).getItemId());
        }
            List<CarInfoEntity> toBeDeleteGoods = new ArrayList<CarInfoEntity>();// 待删除的子元素列表
            toBeDeleteGoods.add(childs.get(childPosition));
            childs.removeAll(toBeDeleteGoods);
            //删除缓存中的子元素
            if (!APPLICATION.isLogin) {
                shopCarList.get(groupPosition).getCartList().removeAll(toBeDeleteGoods);
            }

        if (childs.size() == 0) {
            groups.remove(groupPosition);
                        shopCarList.remove(groupPosition);//删除组
        }
        if(!APPLICATION.isLogin){
            aCache.put(Constants.SHOPCAR, shopCarList);
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /*删除一个或者多个购物车信息*/
    public void deleteCart(String itemids) {
        showLoading();
        OkGo.post(BaseConstant.GoodsUrl + BaseConstant.DELETECART)
                .params("itemIds", itemids)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(this.getActivity(), false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        dismissLoading();
                        if (!"200".equals(baseResponseData.flag)) {
                            Toast.makeText(context, baseResponseData.msg, Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });

    }

    @Override
    public void checkGroup(int groupPosition, boolean isChecked) {
        ShopCarEntity group = groups.get(groupPosition);
        List<CarInfoEntity> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            childs.get(i).setChoosed(isChecked);
        }
        if (isAllCheck())
            allChekbox.setChecked(true);
        else
            allChekbox.setChecked(false);
        selva.notifyDataSetChanged();
        calculate();
    }

    @Override
    public void checkChild(int groupPosition, int childPosiTion,
                           boolean isChecked) {
        boolean allChildSameState = true;// 判断改组下面的所有子元素是否是同一种状态
        ShopCarEntity group = groups.get(groupPosition);
        List<CarInfoEntity> childs = children.get(group.getId());
        for (int i = 0; i < childs.size(); i++) {
            // 不全选中
            if (childs.get(i).isChoosed() != isChecked) {
                allChildSameState = false;
                break;
            }
        }
        //获取店铺选中商品的总金额
        if (allChildSameState) {
            group.setChoosed(isChecked);// 如果所有子元素状态相同，那么对应的组元素被设为这种统一状态
        } else {
            group.setChoosed(false);// 否则，组元素一律设置为未选中状态
        }

        if (isAllCheck()) {
            allChekbox.setChecked(true);// 全选
        } else {
            allChekbox.setChecked(false);// 反选
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    private boolean isAllCheck() {

        for (ShopCarEntity group : groups) {
            if (!group.isChoosed())
                return false;
        }
        return true;
    }

    /**
     * 全选与反选
     */
    private void doCheckAll() {
        for (int i = 0; i < groups.size(); i++) {
            groups.get(i).setChoosed(allChekbox.isChecked());
            ShopCarEntity group = groups.get(i);
            List<CarInfoEntity> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                childs.get(j).setChoosed(allChekbox.isChecked());
            }
        }
        selva.notifyDataSetChanged();
        calculate();
    }

    /**
     * 统计操作<br>
     * 1.先清空全局计数器<br>
     * 2.遍历所有子元素，只要是被选中状态的，就进行相关的计算操作<br>
     * 3.给底部的textView进行数据填充
     * 4.遍历所有子元素，被选中，添加到集合
     */
    private void calculate() {
        totalCount = 0;
        totalPrice = 0.00;
        checkedList = new ArrayList<CarInfoEntity>();
        for (int i = 0; i < groups.size(); i++) {
            ShopCarEntity group = groups.get(i);
            List<CarInfoEntity> childs = children.get(group.getId());
            for (int j = 0; j < childs.size(); j++) {
                CarInfoEntity product = childs.get(j);
                if (product.isChoosed()) {
                    checkedList.add(product);
                    totalCount++;
                    totalPrice += product.getMemberPrice() * product.getNum();
                }
            }
        }
        tvTotalPrice.setText("￥" + totalPrice);
        tvGoToPay.setText("去结算(" + totalCount + ")");
        //计算购物车的金额为0时候清空购物车的视图
        if (totalCount == 0) {
            setCartNum();
        } else {
            title.setText("购物车" + "(" + totalCount + ")");
        }
    }

    /*确认订单*/
    private void validateOrder(ArrayList<orderListItem> cartlistitems) {
        Gson gson = new Gson();
        String obj = gson.toJson(cartlistitems);
        LogUtils.e("确认订单参数", obj.toString());
        LogUtils.e("确认订单参数userId", AppShared.getInstance(context).getLoginInfo().id.toString());
        LogUtils.e("确认订单参数", obj.toString());
        LogUtils.e("确认订单参数", obj.toString());
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.VALIDATEORDER)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("items", obj)
                .params("type", "1")
                .execute(new DialogCallback<BaseResponse<EnorderEntity>>(this.getActivity(), false) {
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

    @OnClick({R.id.all_chekbox, R.id.tv_delete, R.id.tv_go_to_pay, R.id.subtitle, R.id.tv_share})
    public void onClick(View view) {
        AlertDialog alert;
        switch (view.getId()) {
            case R.id.all_chekbox:
                doCheckAll();
                break;
            case R.id.tv_delete:
                if (totalCount == 0) {
                    Toast.makeText(context, "请选择要移除的商品", Toast.LENGTH_LONG).show();
                    return;
                }
                alert = new AlertDialog.Builder(context).create();
                alert.setTitle("操作提示");
                alert.setMessage("您确定要将这些商品从购物车中移除吗？");
                alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                return;
                            }
                        });
                alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                doDelete();
                            }
                        });
                alert.show();
                break;
            case R.id.tv_go_to_pay:
                if (totalCount == 0) {
                    Toast.makeText(context, "您还没有选择商品哦", Toast.LENGTH_LONG).show();
                    return;
                }
                if (!APPLICATION.isLogin) {
                    Toast.makeText(context, "请您先登录", Toast.LENGTH_LONG).show();
                    goNext(LoginAct.class);
                    return;
                }
                paras.putSerializable(Constants.CHECKEDLIST, checkedList);
                ArrayList<orderListItem> orderListItems = new ArrayList<>();
                for (int i = 0; i < checkedList.size(); i++) {
                    orderListItem orderListItem = new orderListItem();
                    orderListItem.setId(checkedList.get(i).getItemId());
                    orderListItem.setNum(checkedList.get(i).getNum());
                    orderListItems.add(orderListItem);
                }
//                paras.putSerializable(Constants.CHECKEDLIST,orderListItems);
                validateOrder(orderListItems);
                break;
            case R.id.subtitle:
                calculate();
                if (flag == 0) {
                    llInfo.setVisibility(View.GONE);
                    tvGoToPay.setVisibility(View.GONE);
                    llShar.setVisibility(View.VISIBLE);
                    subtitle.setText("完成");
                } else if (flag == 1) {
                    llInfo.setVisibility(View.VISIBLE);
                    tvGoToPay.setVisibility(View.VISIBLE);
                    llShar.setVisibility(View.GONE);
                    subtitle.setText("编辑");
                }
                flag = (flag + 1) % 2;//其余得到循环执行上面2个不同的功能
                break;
            case R.id.tv_share:
//                if (totalCount == 0) {
//                    Toast.makeText(context, "请选择要分享的商品", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if (!APPLICATION.isLogin) {
                    Toast.makeText(context, "请您先登录", Toast.LENGTH_LONG).show();
                    goNext(LoginAct.class);
//                    loginDialog();
                    return;
                }
                showShare();
//                Toast.makeText(ShoppingCartFragment.this, "分享成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * sharesdk分享
     */
    private void showShare() {
//        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("爱心520商城");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://www.520zhiai.com/weixin/index.html");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("执爱爱心520商城，一款专注于正品的时尚购物平台，" +
                "新用户入会享会员价，加入爱心会员后全场商品享52元爱心价。" +
                "爱心520商城分享还可获赠爱心值，可抵现，可提取。"
        );
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://www.shcmdn.cn/group1/M00/00/11/rBBH51oeSxKAAHV7AAAoxBEksho485.png?logo@2x.png");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://www.520zhiai.com/weixin/index.html");
// 启动分享GUI
        oks.show(this.getActivity());
        // 启动分享GUI
        oks.show(context);
    }


//    @Override
//    public void groupEdit(int groupPosition) {
////        groups.get(groupPosition).setIsEdtor(true);
//        selva.notifyDataSetChanged();
//    }

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            //删除购物车后动态改变数量
//            setCartNum();
//        }
//    };


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        selva = null;
        groups.clear();
        totalPrice = 0;
        totalCount = 0;
        children.clear();
    }

    public void onFirstUserVisible() {
        llInfo.setVisibility(View.VISIBLE);
        tvGoToPay.setVisibility(View.VISIBLE);
        llShar.setVisibility(View.GONE);
        subtitle.setText("编辑");
        allChekbox.setChecked(false);// 反选
        if (APPLICATION.isLogin) {
            getCarlist();
        } else {
            shopCarList = (ArrayList<ShopCarEntity>) aCache.getAsObject(Constants.SHOPCAR);
            if (null != shopCarList && shopCarList.size() > 0) {
                initDatas();
                initEvents();
                setCartNum();
            } else {
//               本地购物车为空
                initDatas();
                initEvents();
//                cart_empty.setVisibility(View.VISIBLE);
//                subtitle.setVisibility(View.GONE);
//                llCart.setVisibility(View.GONE);
                setCartNum();
            }
        }
    }

    public void onUserVisible() {
        llInfo.setVisibility(View.VISIBLE);
        tvGoToPay.setVisibility(View.VISIBLE);
        llShar.setVisibility(View.GONE);
        subtitle.setText("编辑");
        allChekbox.setChecked(false);// 反选
        if (APPLICATION.isLogin) {
            getCarlist();
        } else {
            shopCarList = (ArrayList<ShopCarEntity>) aCache.getAsObject(Constants.SHOPCAR);
            if (null != shopCarList && shopCarList.size() > 0) {
                initDatas();
                initEvents();
                setCartNum();
            } else {
//               本地购物车为空
                initDatas();
                initEvents();
//                cart_empty.setVisibility(View.VISIBLE);
//                subtitle.setVisibility(View.GONE);
//                llCart.setVisibility(View.GONE);
                setCartNum();
            }
        }
    }

    public void onFirstUserInvisible() {
    }

    public void onUserInvisible() {
    }
}

