package com.tianzhi.shop520.ui.activity.shop;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.base.BaseFragmentActivity;
import com.tianzhi.shop520.callback.DialogJsonCallback;
import com.tianzhi.shop520.entity.BaseResponseData;
import com.tianzhi.shop520.entity.JsonBean;
import com.tianzhi.shop520.entity.store.ReceiverInfo;
import com.tianzhi.shop520.util.AppShared;
import com.tianzhi.shop520.util.BaseConstant;
import com.tianzhi.shop520.util.GetJsonDataUtil;
import com.tianzhi.shop520.util.LogUtils;
import com.tianzhi.shop520.util.StringUtils;

import org.json.JSONArray;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

import static com.tianzhi.shop520.R.id.et_account;

/**
 * Created by thinkpad on 2017/10/20.
 */

public class AddAddressAct extends BaseFragmentActivity {

    @BindView(et_account)
    EditText etAccount;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_province)
    TextView tvProvince;
    @BindView(R.id.tv_detailed_address)
    TextView tvDetailedAddress;
    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;
    @BindView(R.id.tv_save)
    TextView tvSave;
    @BindView(R.id.et_address)
    EditText etAddress;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private Thread thread;
    private boolean isLoaded = false;
    private String receiverState;//省
    private String receiverCity;//市
    private String receiverDistrict;//区
    private String receiverId;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        LogUtils.e("", "Begin Parse Data");
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                    LogUtils.e("", "Parse Succeed");
                    isLoaded = true;

                    if (isLoaded) {
                        ShowPickerView();
                    }
                    break;

                case MSG_LOAD_FAILED:
                    LogUtils.e("", "Parse Failed");
                    break;

            }
        }
    };
    ReceiverInfo upDataReceiverInfo;//要修改的地址
    private String type;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.act_addaddress);
        initView();
    }

    @Override
    protected void initView() {
        setActivityTitle("添加地址");
        type = paras.getString("type");
        if("update".equals(type)){
            upDataReceiverInfo = (ReceiverInfo)paras.getSerializable("ReceiverInfo");
            LogUtils.e("要修改的地址",upDataReceiverInfo.toString());
            etAccount.setText(upDataReceiverInfo.getReceiverName());
            etPhone.setText(upDataReceiverInfo.getReceiverMobile());
            tvProvince.setText(upDataReceiverInfo.getReceiverState()+upDataReceiverInfo.getReceiverCity()
            +upDataReceiverInfo.getReceiverDistrict());
            receiverState = upDataReceiverInfo.getReceiverState();
            receiverCity = upDataReceiverInfo.getReceiverCity();
            receiverDistrict = upDataReceiverInfo.getReceiverDistrict();
            etAddress.setText(upDataReceiverInfo.getReceiverAddress());
            receiverId = upDataReceiverInfo.getId();
        }

    }

    @OnClick({R.id.tv_province, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_province:
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                boolean isOpen=imm.isActive();//isOpen若返回true，则表示输入法打开
                //判断隐藏软键盘是否弹出
                if(isOpen)
                {
                    //隐藏软键盘
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
//                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                }
                if (thread == null) {//如果已创建就不再重新创建子线程了
                    LogUtils.e("", "Begin Parse Data");
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 写子线程中的操作,解析省市区数据
                            initJsonData();
                        }
                    });
                    thread.start();
                } else {
                    if (isLoaded) {
                        ShowPickerView();
                    }
                }
                break;
            case R.id.tv_save:
                if(TextUtils.isEmpty(etAccount.getText().toString())){
                    toast("请输入收货人姓名");
                    return;
                }
                if(TextUtils.isEmpty(etPhone.getText().toString())){
                    toast("请输入收货人联系电话");
                    return;
                }
                if(!StringUtils.isPhoneNum(etPhone.getText().toString())){
                    toast("请输入正确的手机号码");
                    return;
                }
                if(TextUtils.isEmpty(etAddress.getText().toString())){
                    toast("请输入收货人详细地址");
                    return;
                }
                if(StringUtils.isAllNumber(etAddress.getText().toString())
                        ||StringUtils.isAllLetter(etAddress.getText().toString())){
                    toast("请输入正确的详细地址");
                    return;
                }
                if(TextUtils.isEmpty(tvProvince.getText().toString())){
                    toast("请选择地区");
                    return;
                }
                createReceiverInfo();
                break;
        }
    }

    private void ShowPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                receiverState = options1Items.get(options1).getPickerViewText();
                receiverCity = options2Items.get(options1).get(options2);
                receiverDistrict = options3Items.get(options1).get(options2).get(options3);
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                tvProvince.setText(tx);
//               toast(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    //解析数据
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {

                    for (int d = 0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }
            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    private void createReceiverInfo() {
        ReceiverInfo receiverInfo = new ReceiverInfo();
        if(!TextUtils.isEmpty(receiverId)){
            receiverInfo.setId(receiverId);
            receiverInfo.setIsDefault(upDataReceiverInfo.getIsDefault());
        }
        receiverInfo.setReceiverName(etAccount.getText().toString());
        receiverInfo.setReceiverMobile(etPhone.getText().toString());
        receiverInfo.setReceiverState(receiverState);
        receiverInfo.setReceiverCity(receiverCity);
        receiverInfo.setReceiverDistrict(receiverDistrict);
        receiverInfo.setReceiverAddress(etAddress.getText().toString());
        Gson gson = new Gson();
        String obj = gson.toJson(receiverInfo);
        showLoading();
        OkGo.post(BaseConstant.OrderUrl + BaseConstant.CREATERECEIVERINFO)
                .params("userId", AppShared.getInstance(context).getLoginInfo().id)
                .params("userToken", AppShared.getInstance(context).getLoginInfo().userToken)
                .params("type", type)
                .params("receiverInfo",obj )
                .execute(new DialogJsonCallback<BaseResponseData>(this,false) {
                    @Override
                    public void onSuccess(BaseResponseData baseResponseData, Call call, Response response) {
                      dismissLoading();
                        if("200".equals(baseResponseData.flag)){
                            toast(baseResponseData.msg);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        dismissLoading();
                    }
                });
    }
}