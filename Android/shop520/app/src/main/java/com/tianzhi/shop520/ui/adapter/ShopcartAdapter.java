package com.tianzhi.shop520.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daimajia.swipe.SwipeLayout;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.callback.StringDialogCallback;
import com.tianzhi.shop520.entity.shop.CarInfoEntity;
import com.tianzhi.shop520.entity.shop.ShopCarEntity;
import com.tianzhi.shop520.ui.activity.store.GoodsInfoAct;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.Constants;
import com.tianzhi.shop520.util.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * 购物车数据适配器
 */
public class ShopcartAdapter extends BaseExpandableListAdapter {

    private List<ShopCarEntity> groups;
    private Map<String, List<CarInfoEntity>> children;
    private Context context;
    private CheckInterface checkInterface;
    private ModifyCountInterface modifyCountInterface;
    public int flag = 0;
//    private GroupEdtorListener mListener;
    private SwipeLayout currentExpandedSwipeLayout;
    private Activity activity;
    ArrayList<ShopCarEntity> shopCarList;
//    public GroupEdtorListener getmListener() {
//        return mListener;
//    }
//
//    public void setmListener(GroupEdtorListener mListener) {
//        this.mListener = mListener;
//    }

    int count = 0;

    /**
     * 构造函数
     *
     * @param groups   组元素列表
     * @param children 子元素列表
     * @param context
     */
    public ShopcartAdapter(List<ShopCarEntity> groups, Map<String, List<CarInfoEntity>> children, Context context, Activity activity,ArrayList<ShopCarEntity> shopCarList) {
        this.groups = groups;
        this.children = children;
        this.context = context;
        this.activity = activity;
        this.shopCarList = shopCarList;
    }

    public void setCheckInterface(CheckInterface checkInterface) {
        this.checkInterface = checkInterface;
    }

    public void setModifyCountInterface(ModifyCountInterface modifyCountInterface) {
        this.modifyCountInterface = modifyCountInterface;
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        String groupId = groups.get(groupPosition).getId();
        return children.get(groupId).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        List<CarInfoEntity> childs = children.get(groups.get(groupPosition).getId());
        return childs.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return getCombinedChildId(groupPosition, childPosition);
//        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        final GroupViewHolder gholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcart_group, null);
            gholder = new GroupViewHolder(convertView);
            convertView.setTag(gholder);
        } else {
            gholder = (GroupViewHolder) convertView.getTag();
        }
        final ShopCarEntity group = (ShopCarEntity) getGroup(groupPosition);

        gholder.tvSourceName.setText(group.getName());
        gholder.determineChekbox.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v)

            {
                group.setChoosed(((CheckBox) v).isChecked());
                checkInterface.checkGroup(groupPosition, ((CheckBox) v).isChecked());// 暴露组选接口
            }
        });
        gholder.determineChekbox.setChecked(group.isChoosed());
//        if (group.isEdtor()) {
//            gholder.tvStoreEdtor.setText("完成");
//        } else {
//            gholder.tvStoreEdtor.setText("编辑");
//        }
//        gholder.tvStoreEdtor.setOnClickListener(new GroupViewClick(groupPosition, gholder.tvStoreEdtor, group));
        notifyDataSetChanged();
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {

        final ChildViewHolder cholder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_shopcart_product, null);
            cholder = new ChildViewHolder(convertView);
            cholder.swip.setShowMode(SwipeLayout.ShowMode.PullOut);
            cholder.swip.addDrag(SwipeLayout.DragEdge.Right, cholder.swip.findViewWithTag("Bottom2"));
            convertView.setTag(cholder);
        } else {
            cholder = (ChildViewHolder) convertView.getTag();
        }
        cholder.swip.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onStartOpen(SwipeLayout layout) {
                if (currentExpandedSwipeLayout != null&&currentExpandedSwipeLayout!=layout)
                    currentExpandedSwipeLayout.close(true);
            }

            @Override
            public void onOpen(SwipeLayout layout) {
                currentExpandedSwipeLayout = layout;
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onClose(SwipeLayout layout) {
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {

            }
        });
//        if (groups.get(groupPosition).isEdtor() == true) {
//            cholder.llEdtor.setVisibility(View.VISIBLE);
//            cholder.rlNoEdtor.setVisibility(View.GONE);
//        } else {
//            cholder.llEdtor.setVisibility(View.GONE);
//            cholder.rlNoEdtor.setVisibility(View.VISIBLE);
//        }
        final CarInfoEntity goodsInfo = (CarInfoEntity) getChild(groupPosition, childPosition);

        if (goodsInfo != null) {
            cholder.tvIntro.setText(goodsInfo.getItemTitle());
            cholder.tvPrice.setText("￥" + goodsInfo.getPrice() + "");
            cholder.etNum.setText(goodsInfo.getNum() + "");
            cholder.tv_Vipprice.setText("￥"+goodsInfo.getMemberPrice());
            Glide.with(context).load(goodsInfo.getHomepageUrl()).into((ImageView)cholder.ivAdapterListPic);

//            cholder.ivAdapterListPic.setImageResource(goodsInfo.getGoodsImg());
            SpannableString spanString = new SpannableString("￥" + String.valueOf(goodsInfo.getMemberPrice()));
            StrikethroughSpan span = new StrikethroughSpan();
            spanString.setSpan(span, 0, String.valueOf(goodsInfo.getMemberPrice()).length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            //避免无限次的appand
            if (cholder.tvDiscountPrice.getText().toString().length() > 0) {
                cholder.tvDiscountPrice.setText("");
            }
            cholder.tvDiscountPrice.append(spanString);
            cholder.checkBox.setChecked(goodsInfo.isChoosed());
            cholder.checkBox.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    goodsInfo.setChoosed(((CheckBox) v).isChecked());
                    cholder.checkBox.setChecked(((CheckBox) v).isChecked());
                    checkInterface.checkChild(groupPosition, childPosition, ((CheckBox) v).isChecked());// 暴露子选接口
                }
            });
            cholder.btAdd.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doIncrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露增加接口
                }
            });
            cholder.btReduce.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifyCountInterface.doDecrease(groupPosition, childPosition, cholder.etNum, cholder.checkBox.isChecked());// 暴露删减接口
                }
            });
            cholder.goodsItem.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context,GoodsInfoAct.class);
                    intent.putExtra(Constants.GOODSID,goodsInfo.getItemId());
                    context.startActivity(intent);
                }
            });

            /********************方案一：弹出软键盘修改数量，应为又不知名的bug会使然键盘强行关闭***********************/
            /****在清单文件的activity下设置键盘：
             android:windowSoftInputMode="adjustUnspecified|stateHidden|adjustPan"
             android:configChanges="orientation|keyboardHidden"****/
          /*  cholder.etNum.addTextChangedListener(new GoodsNumWatcher(goodsInfo));//监听文本输入框的文字变化，并且刷新数据
            notifyDataSetChanged();*/
            /********************方案一***************************************************************************/
            /********************方案二：让软键盘不能弹出，文本框不可编辑弹出dialog修改***********************/
//            cholder.etNum.setOnFocusChangeListener(new android.view.View.
//                    OnFocusChangeListener() {
//                @Override
//                public void onFocusChange(View v, boolean hasFocus) {//监听焦点的变化
//                    if (hasFocus) {//获取到焦点也就是文本框被点击修改了
//                        // 1，先强制键盘不弹出
//                        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//                        // 2.显示弹出dialog进行修改
//                        showDialog(goodsInfo,cholder.etNum);
            //3.清除焦点防止不断弹出dialog和软键盘
//                        cholder.etNum.clearFocus();
            // 4. 数据刷型
//                        ShopcartAdapter.this.notifyDataSetChanged();
//                    }
//                }
//            });
            /********************方案二***********************/
            //删除 购物车
            cholder.tvGoodsDelete.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
//                    modifyCountInterface.childDelete(groupPosition, childPosition);
                    AlertDialog alert = new AlertDialog.Builder(context).create();
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
                                    modifyCountInterface.childDelete(groupPosition, childPosition);
//                                    /*调用删除接口*/
//                                    if(APPLICATION.isLogin){
//                                        String deleteItemid = goodsInfo.getItemId();
//                                        delect(deleteItemid);
//                                    }else {
//                                        //删除本地数据
//                                        for (int i = 0;i<shopCarList.size();i++){
//                                            if (goodsInfo.getItemId().equals(shopCarList.get(i).getId())) {
//                                                shopCarList.remove(i);//删除组
//                                            }
//                                        }
//
//                                    }
                                }
                            });
                    alert.show();
                }
            });

        }

        return convertView;
    }
    private void delect(String deleteItemid){
        OkGo.post(BaseConstant.GoodsUrl+BaseConstant.DELETECART)
                .params("userId",AppShared.getInstance(context).getLoginInfo().id)
                .params("itemIds",deleteItemid)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .execute(new StringDialogCallback(activity) {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        LogUtils.e("左滑删除",s);
                    }
                });
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;

    }


    /**
     * 复选框接口
     */
    public interface CheckInterface {
        /**
         * 组选框状态改变触发的事件
         *
         * @param groupPosition 组元素位置
         * @param isChecked     组元素选中与否
         */
        void checkGroup(int groupPosition, boolean isChecked);

        /**
         * 子选框状态改变时触发的事件
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param isChecked     子元素选中与否
         */
        void checkChild(int groupPosition, int childPosition, boolean isChecked);
    }

    /**
     * 改变数量的接口
     */
    public interface ModifyCountInterface {
        /**
         * 增加操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doIncrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删减操作
         *
         * @param groupPosition 组元素位置
         * @param childPosition 子元素位置
         * @param showCountView 用于展示变化后数量的View
         * @param isChecked     子元素选中与否
         */
        void doDecrease(int groupPosition, int childPosition, View showCountView, boolean isChecked);

        /**
         * 删除子item
         *
         * @param groupPosition
         * @param childPosition
         */
        void childDelete(int groupPosition, int childPosition);
    }

//    /**
//     * 监听编辑状态
//     */
//    public interface GroupEdtorListener {
//        void groupEdit(int groupPosition);
//    }

//    /**
//     * 使某个组处于编辑状态
//     * <p/>
//     * groupPosition组的位置
//     */
//    class GroupViewClick implements OnClickListener {
//        private int groupPosition;
//        private Button edtor;
//        private ShopCarEntity group;
//
//        public GroupViewClick(int groupPosition, Button edtor, ShopCarEntity group) {
//            this.groupPosition = groupPosition;
//            this.edtor = edtor;
//            this.group = group;
//        }
//
//        @Override
//        public void onClick(View v) {
//            int groupId = v.getId();
//            if (groupId == edtor.getId()) {
//                if (group.isEdtor()) {
//                    group.setIsEdtor(false);
//                } else {
//                    group.setIsEdtor(true);
//
//                }
//                notifyDataSetChanged();
//            }
//        }
//    }

    /**
     * 组元素绑定器
     */
    static class GroupViewHolder {
        @BindView(R.id.determine_chekbox)
        CheckBox determineChekbox;
        @BindView(R.id.tv_source_name)
        TextView tvSourceName;
//        @BindView(R.id.tv_store_edtor)
//        Button tvStoreEdtor;

        GroupViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * 子元素绑定器
     */
    static class ChildViewHolder {
        @BindView(R.id.check_box)
        CheckBox checkBox;
        @BindView(R.id.iv_adapter_list_pic)
        ImageView ivAdapterListPic;
        @BindView(R.id.tv_intro)
        TextView tvIntro;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_discount_price)
        TextView tvDiscountPrice;
        @BindView(R.id.rl_no_edtor)
        LinearLayout rlNoEdtor;
        @BindView(R.id.bt_reduce)
        Button btReduce;
        @BindView(R.id.et_num)
        TextView etNum;
        @BindView(R.id.bt_add)
        Button btAdd;
        @BindView(R.id.ll_change_num)
        RelativeLayout llChangeNum;
        @BindView(R.id.tv_goods_delete)
        TextView tvGoodsDelete;
        @BindView(R.id.swip)
        SwipeLayout swip;
        @BindView(R.id.goods_item)
        LinearLayout goodsItem;
        @BindView(R.id.tv_Vipprice)
        TextView tv_Vipprice;


        ChildViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    /**
     * 购物车的数量修改编辑框的内容监听
     */
   /* class GoodsNumWatcher implements TextWatcher {
        GoodsInfo goodsInfo;

        public GoodsNumWatcher(GoodsInfo goodsInfo) {
            this.goodsInfo = goodsInfo;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (!TextUtils.isEmpty(s.toString())) {//当输入的数字不为空时，更新数字
                goodsInfo.setCount(Integer.valueOf(s.toString().trim()));
            }
        }

    }*/

    /**
     * 显示修改购物车商品数量的dialog
     *
     * @param goodinfo item的商品信息实体
     * @param edittext 购物车item的数量文本框
     */
//    private void showDialog(final GoodsInfo goodinfo, final EditText edittext) {
//        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//        View alertDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_change_num, null, false);
//        final AlertDialog alertDialog = alertDialogBuilder.create();
//        alertDialog.setView(alertDialogView);
//        count = goodinfo.getCount();
//        final EditText editText = (EditText) alertDialogView.findViewById(R.id.et_num);
//        editText.setText("" + goodinfo.getCount());//设置dialog的数量初始值
//        //自动弹出软键盘
//        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//            public void onShow(DialogInterface dialog) {
//                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
//                imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
//            }
//        });
//        final Button btadd = (Button) alertDialogView.findViewById(R.id.bt_add);
//        final Button btreduce = (Button) alertDialogView.findViewById(R.id.bt_reduce);
//        final TextView cancle = (TextView) alertDialogView.findViewById(R.id.tv_cancle);
//        final TextView sure = (TextView) alertDialogView.findViewById(R.id.tv_sure);
//        cancle.setOnClickListener(new OnClickListener() { //取消按钮
//            @Override
//            public void onClick(View v) {
//                alertDialog.dismiss();
//            }
//        });
//        sure.setOnClickListener(new OnClickListener() {//确定按钮
//            @Override
//            public void onClick(View v) {
//                goodinfo.setCount(count);//重新设置数量
//                edittext.setText(count + "");//购物车界面的文本框显示同步
//                alertDialog.dismiss();
//            }
//        });
//        btadd.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                count++;   //点一下量加1
//                editText.setText("" + count);//动态显示dialog的文本框的数据
//
//            }
//        });
//        btreduce.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (count > 1) {//数量大雨1时操作
//                    count--; //点一下减1
//                    editText.setText("" + count);
//                }
//            }
//        });
//        alertDialog.show();
//    }
}