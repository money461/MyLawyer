package com.tianzhi.shop520.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogCallback;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponse;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.order.EnorderEntity;
import com.tianzhi.shop520.entity.order.OrderEntity;
import com.tianzhi.shop520.entity.order.OrderItemList;
import com.tianzhi.shop520.entity.shop.orderListItem;
import com.tianzhi.shop520.ui.activity.order.EnsureOrderAct;
import com.tianzhi.shop520.ui.activity.order.OrderInfoAct;
import com.tianzhi.shop520.ui.activity.order.ShippingAct;
import com.tianzhi.shop520.ui.activity.shop.PayAct;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.LogUtils;

import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;


/**
 * Created by thinkpad on 2017/10/26.
 * 外层RecyclerView的Adapter
 */

public class OrderRclAdpter extends RecyclerView.Adapter<BaseHolder> {
    //条目样式
    private final int HORIZONTAL_VIEW = 1000;
    private final int VERTICAL_VIEW = 1001;
    private final int GRID_VIEW = 1002;
    private int HORIZONTAL_VIEW_X = 0;//水平RecyclerView滑动的距离
    private ArrayList<OrderEntity> orderList;
    Context mContext;
    int screenWidth;
    BaseFragmentActivity activity;
    AlertDialog alert;
    private EnorderEntity enorderEntity;//确认订单  接口返回
    private notifyDataInterface notifyDataInterface;
    public void setCheckInterface(notifyDataInterface checkInterface) {
        this.notifyDataInterface = checkInterface;
    }
    public OrderRclAdpter(Context context, ArrayList<OrderEntity> List, int screenWidth, BaseFragmentActivity activity) {
        this.mContext = context;
        this.orderList = List;
        this.screenWidth = screenWidth;
        this.activity = activity;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HORIZONTAL_VIEW:
                return new HorizontalViewHolder(R.layout.item_recyclerview, parent, viewType);
//        case GRID_VIEW:
//        return new GridViewHolder(R.layout.item_recyclerview, parent, viewType);
            case VERTICAL_VIEW:
                return new ItemViewHolder(R.layout.item_x2_imageview, parent, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if (holder instanceof HorizontalViewHolder) {
            holder.refreshData(orderList.get(position), position);
//        } else if (holder instanceof GridViewHolder) {
//        holder.refreshData(data.gridData, position);
        } else if (holder instanceof ItemViewHolder) {
            holder.refreshData(orderList.get(position), position);
        }

    }

    @Override
/**
 * 当Item出现时调用此方法
 */
    public void onViewAttachedToWindow(BaseHolder holder) {
        LogUtils.i("mengyuan", "onViewAttachedToWindow:" + holder.getClass().toString());
    }

    @Override
/**
 * 当Item被回收时调用此方法
 */
    public void onViewDetachedFromWindow(BaseHolder holder) {
        LogUtils.e("mengyuan", "onViewDetachedFromWindow:" + holder.getClass().toString());
        if (holder instanceof HorizontalViewHolder) {
            ((HorizontalViewHolder) holder).saveStateWhenDestory();
        }
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (orderList.get(position).getOrderItems().size() > 1)
            return HORIZONTAL_VIEW;
        return VERTICAL_VIEW;
    }

    //----------------------Holder----------------------------

    /**
     * 嵌套的水平RecyclerView
     * 当条目被回收时，下次加载会重新回到之前的x轴
     */
    private class HorizontalViewHolder extends BaseHolder<OrderEntity> {
        private RecyclerView hor_recyclerview;
        private ArrayList<OrderItemList> orderInfoList;
        private int scrollX;//纪录X移动的距离
        private boolean isLoadLastState = false;//是否加载了之前的状态
        TextView orderNum;
        TextView payPayment;
        TextView orderStatus;
        TextView tvCheckLogistics;//查看物流
        TextView tvCancelOrder;//取消订单
        TextView goodsDescription;
        TextView tvSureRevice;//确认收货
        TextView tvAngintoBuy;//再次购买
        TextView tvBtntoPay;//去付款
        TextView tv_remind_delivery;//提醒发货

        public HorizontalViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            hor_recyclerview = (RecyclerView) itemView.findViewById(R.id.item_recyclerview);
            orderNum = (TextView) itemView.findViewById(R.id.order_num);
            payPayment = (TextView) itemView.findViewById(R.id.pay_payment);
            orderStatus = (TextView) itemView.findViewById(R.id.order_status);
            tvCheckLogistics = (TextView) itemView.findViewById(R.id.tv_check_logistics);
            tvCancelOrder = (TextView) itemView.findViewById(R.id.tv_acncel_order);
            goodsDescription = (TextView) itemView.findViewById(R.id.goods_description);
            tvSureRevice = (TextView) itemView.findViewById(R.id.tv_sure_revice);
            tvAngintoBuy = (TextView) itemView.findViewById(R.id.tv_anginto_buy);
            tvBtntoPay = (TextView) itemView.findViewById(R.id.tv_btnto_pay);
            tv_remind_delivery = (TextView)itemView.findViewById(R.id.tv_remind_delivery);

            //为了保存移动距离，所以添加滑动监听
            hor_recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    //每次条目重新加载时，都会滑动到上次的距离
                    if (!isLoadLastState) {
                        isLoadLastState = true;
                        hor_recyclerview.scrollBy(HORIZONTAL_VIEW_X, 0);
                    }
                    //dx为每一次移动的距离，所以我们需要做累加操作
                    scrollX += dx;
                }
            });
        }
        //按钮全部隐藏
        private void setGone(){
            tvCheckLogistics.setVisibility(View.GONE);
            tvCancelOrder.setVisibility(View.GONE);
            tvSureRevice.setVisibility(View.GONE);
            tvAngintoBuy.setVisibility(View.GONE);
            tvBtntoPay.setVisibility(View.GONE);
            tv_remind_delivery.setVisibility(View.GONE);
        }
        @Override
        public void refreshData(final OrderEntity data, final int position) {
            this.orderInfoList = data.getOrderItems();
//            OrderListEntity orderListEntity = data.getOrderItems().get(0);
            //设置水平RecyclerView水平显示
            hor_recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
            //设置背景
//            hor_recyclerview.setBackgroundResource(R.color.white);
            //设置Adapter
            hor_recyclerview.setAdapter(new HorizontalAdapter(mContext, orderInfoList, screenWidth,data.getOrderId()));
            if ("1".equals(data.getStatus())) {
                orderStatus.setText("未付款");
                setGone();
                tvCancelOrder.setVisibility(View.VISIBLE);
                tvBtntoPay.setVisibility(View.VISIBLE);
            } else if ("2".equals(data.getStatus())) {
                orderStatus.setText("已付款待发货");
                setGone();
                tv_remind_delivery.setVisibility(View.VISIBLE);
            } else if ("3".equals(data.getStatus())) {
                orderStatus.setText("已发货");
                setGone();
                tvCheckLogistics.setVisibility(View.VISIBLE);
                tvSureRevice.setVisibility(View.VISIBLE);
            } else if ("4".equals(data.getStatus())) {
                orderStatus.setText("交易成功");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            } else if ("5".equals(data.getStatus())) {
                orderStatus.setText("交易关闭");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            } else if ("6".equals(data.getStatus())) {
                orderStatus.setText("已退款");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            }
            orderNum.setText("共计" + data.getOrderNum() + "件商品");
            payPayment.setText("￥" + data.getPayment() + "(含运费￥" + data.getPostFee() + ")");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("orderid", data.getOrderId());
                    intent.setClass(activity, OrderInfoAct.class);
                    mContext.startActivity(intent);
                }
            });
            /*查看物流*/
            tvCheckLogistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("com", data.getCom());
                    intent.putExtra("shippingCode", data.getShippingCode());
                    intent.setClass(activity, ShippingAct.class);
                    mContext.startActivity(intent);
                }
            });
            /*提醒发货*/
            tv_remind_delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"已提醒发货！",Toast.LENGTH_LONG).show();
                }
            });

            tvAngintoBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<orderListItem> orderListItems = new ArrayList<>();
                    for (int i = 0;i<data.getOrderItems().size();i++){
                        orderListItem orderListItem = new orderListItem();
                        orderListItem.setId(data.getOrderItems().get(i).getItemId());
                        orderListItem.setNum(data.getOrderItems().get(i).getNum());
                        orderListItems.add(orderListItem);
                    }
//                paras.putSerializable(Constants.CHECKEDLIST,orderListItems);
                    validateOrder(orderListItems);
                }
            });

            tvBtntoPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("orderId", data.getOrderId());
                    intent.setClass(activity, PayAct.class);
                    mContext.startActivity(intent);
                }
            });

            /*取消订单*/
            tvCancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("取消订单", "点击取消订单");
                    alert = new AlertDialog.Builder(mContext).create();
//                alert.setTitle("操作提示");
                    alert.setMessage("您确定要取消该订单吗？");
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
                                    cancelOrder( data);
                                }
                            });
                    alert.show();


                }
            });
        }

        /**
         * 在条目回收时调用，保存X轴滑动的距离
         */
        public void saveStateWhenDestory() {
            HORIZONTAL_VIEW_X = scrollX;
            isLoadLastState = false;
            scrollX = 0;
        }
    }

/*请求接口  取消订单*/
    private void cancelOrder(final OrderEntity data){
        activity.showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.ALERTORDERSTATUS)
                .params("userId", AppShared.getInstance(mContext).getLoginInfo().id)
                .params("orderId", data.getOrderId())
                .params("status", "0")
                .params("userToken", AppShared.getInstance(mContext).getLoginInfo().userToken)
                .execute(new DialogJsonCallback<BaseResponseData>(activity,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                        activity.dismissLoading();
                        if("200".equals(baseResponseData.flag)){
                            notifyDataInterface.notifyData();
                        }else {
                            Toast.makeText(mContext,baseResponseData.msg,Toast.LENGTH_LONG).show();
                        }
                    }

                });
    }


    public interface  notifyDataInterface{
        /**
         * 组选框状态改变触发的事件
         *
         * 刷新数据
         */
        void notifyData();
    }
    /**
     * 通用子条目hodler
     */
    private class ItemViewHolder extends BaseHolder<OrderEntity> {

        private ImageView imageview_item;
        TextView goodsName;
        TextView goodsPrice;
        TextView goodsNum;
        TextView orderNum;
        TextView payPayment;
        TextView orderStatus;
        TextView tvCheckLogistics;//查看物流
        TextView tvCancelOrder;//取消订单
        TextView goodsDescription;
        TextView tvSureRevice;//确认收货
        TextView tvAngintoBuy;//再次购买
        TextView tvBtntoPay;//去付款
        TextView tv_remind_delivery;//提醒发货
        public ItemViewHolder(int viewId, ViewGroup parent, int viewType) {
            super(viewId, parent, viewType);
            imageview_item = (ImageView) itemView.findViewById(R.id.imageview_item);
            goodsName = (TextView) itemView.findViewById(R.id.goods_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.goods_price);
            goodsNum = (TextView) itemView.findViewById(R.id.goods_num);
            orderNum = (TextView) itemView.findViewById(R.id.order_num);
            payPayment = (TextView) itemView.findViewById(R.id.pay_payment);
            orderStatus = (TextView) itemView.findViewById(R.id.order_status);
            tvCheckLogistics = (TextView) itemView.findViewById(R.id.tv_check_logistics);
            tvCancelOrder = (TextView) itemView.findViewById(R.id.tv_acncel_order);
            goodsDescription = (TextView) itemView.findViewById(R.id.goods_description);
            tvSureRevice = (TextView) itemView.findViewById(R.id.tv_sure_revice);
            tvAngintoBuy = (TextView) itemView.findViewById(R.id.tv_anginto_buy);
            tvBtntoPay = (TextView) itemView.findViewById(R.id.tv_btnto_pay);
            tv_remind_delivery = (TextView)itemView.findViewById(R.id.tv_remind_delivery);
            ViewGroup.LayoutParams layoutParams = imageview_item.getLayoutParams();
            layoutParams.width = layoutParams.height = screenWidth / 3;
            imageview_item.setLayoutParams(layoutParams);
        }
        //按钮全部隐藏
        private void setGone(){
            tvCheckLogistics.setVisibility(View.GONE);
            tvCancelOrder.setVisibility(View.GONE);
            tvSureRevice.setVisibility(View.GONE);
            tvAngintoBuy.setVisibility(View.GONE);
            tvBtntoPay.setVisibility(View.GONE);
            tv_remind_delivery.setVisibility(View.GONE);
        }

        @Override
        public void refreshData(final OrderEntity data, final int position) {
//            imageview_item.setBackgroundResource(data);
            OrderItemList orderListEntity = data.getOrderItems().get(0);
            Glide.with(mContext).load(orderListEntity.getHomepageUrl()).into(imageview_item);
            if ("1".equals(data.getStatus())) {
                orderStatus.setText("未付款");
                setGone();
                tvCancelOrder.setVisibility(View.VISIBLE);
                tvBtntoPay.setVisibility(View.VISIBLE);
            } else if ("2".equals(data.getStatus())) {
                orderStatus.setText("已付款待发货");
                setGone();
                tv_remind_delivery.setVisibility(View.VISIBLE);
            } else if ("3".equals(data.getStatus())) {
                orderStatus.setText("已发货");
                setGone();
                tvCheckLogistics.setVisibility(View.VISIBLE);
                tvSureRevice.setVisibility(View.VISIBLE);
            } else if ("4".equals(data.getStatus())) {
                orderStatus.setText("交易成功");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            } else if ("5".equals(data.getStatus())) {
                orderStatus.setText("交易关闭");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            } else if ("6".equals(data.getStatus())) {
                orderStatus.setText("已退款");
                setGone();
                tvAngintoBuy.setVisibility(View.VISIBLE);
            }

            goodsName.setText(orderListEntity.getItemTitle());
            goodsPrice.setText("￥"+orderListEntity.getPrice());
            orderNum.setText("共计" + data.getOrderNum() + "件商品");
            payPayment.setText("￥" + data.getPayment() + "(含运费￥" + data.getPostFee() + ")");
            goodsDescription.setText(orderListEntity.getDescription());
            goodsNum.setText("x"+orderListEntity.getNum());
            //订单详情
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("orderid", data.getOrderId());
                    intent.setClass(activity, OrderInfoAct.class);
                    mContext.startActivity(intent);
                }
            });
             /*查看物流*/
            tvCheckLogistics.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("com", data.getCom());
                    intent.putExtra("shippingCode", data.getShippingCode());
                    intent.setClass(activity, ShippingAct.class);
                    mContext.startActivity(intent);
                }
            });
             /*提醒发货*/
            tv_remind_delivery.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"提醒卖家发货信息发送成功！",Toast.LENGTH_LONG).show();
                }
            });
            /*去支付*/
            tvBtntoPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("orderId", data.getOrderId());
                    intent.setClass(activity, PayAct.class);
                    mContext.startActivity(intent);
                }
            });
/*取消订单*/
            tvCancelOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("取消订单", "点击取消订单");
                    alert = new AlertDialog.Builder(mContext).create();
//                alert.setTitle("操作提示");
                    alert.setMessage("您确定要取消该订单？");
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
                                    cancelOrder( data);
                                }
                            });
                    alert.show();
                }
            });
            /*再次购买*/
            tvAngintoBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ArrayList<orderListItem> orderListItems = new ArrayList<>();
                    for (int i = 0;i<data.getOrderItems().size();i++){
                        orderListItem orderListItem = new orderListItem();
                        orderListItem.setId(data.getOrderItems().get(i).getItemId());
                        orderListItem.setNum(data.getOrderItems().get(i).getNum());
                        orderListItems.add(orderListItem);
                    }
//                paras.putSerializable(Constants.CHECKEDLIST,orderListItems);
                    validateOrder(orderListItems);
                }
            });
        }
    }
    /*确认订单*/
    private void validateOrder( ArrayList<orderListItem> cartlistitems){
        Gson gson =new Gson();
        String obj = gson.toJson(cartlistitems);
        LogUtils.e("确认订单参数",obj.toString());
        LogUtils.e("确认订单参数userId",AppShared.getInstance(mContext).getLoginInfo().id.toString());
        LogUtils.e("确认订单参数",obj.toString());
        LogUtils.e("确认订单参数",obj.toString());
        activity.showLoading();
        OkGo.post(BaseConstant.OrderUrl+BaseConstant.VALIDATEORDER)
                .params("userId", AppShared.getInstance(mContext).getLoginInfo().id)
                .params("userToken",AppShared.getInstance(mContext).getLoginInfo().userToken)
                .params("items",obj)
                .params("type","1")
                .execute(new DialogCallback<BaseResponse<EnorderEntity>>(activity,false) {
                    @Override
                    public void onSuccess(BaseResponse<EnorderEntity> enorderEntityBaseResponse, Call call, Response response) {
                        super.onSuccess(enorderEntityBaseResponse, call, response);
                        activity.dismissLoading();
                        enorderEntity =  enorderEntityBaseResponse.data;
                        Intent intent = new Intent();
                        intent.putExtra("enorderEntity", (Serializable) enorderEntity);
                        intent.putExtra("type","1");
                        intent.setClass(activity, EnsureOrderAct.class);
                        mContext.startActivity(intent);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        activity.dismissLoading();
                    }
                });
    }
}