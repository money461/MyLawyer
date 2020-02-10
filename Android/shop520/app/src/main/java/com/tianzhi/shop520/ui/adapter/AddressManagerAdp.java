package com.tianzhi.shop520.ui.adapter;

import android.graphics.Color;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tianzhi.shop520.R;
import com.tianzhi.shop520.entity.store.ReceiverInfo;

import java.util.List;

/**
 * Created by thinkpad on 2017/10/24.
 */

public class AddressManagerAdp  extends BaseQuickAdapter<ReceiverInfo, BaseViewHolder> {

    public AddressManagerAdp(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReceiverInfo item) {
        String address = item.getReceiverState()+item.getReceiverCity()
                +item.getReceiverDistrict()+item.getReceiverAddress();
        String address2 = item.getReceiverCity()
                +item.getReceiverDistrict()+item.getReceiverAddress();
        helper.setText(R.id.address_name, item.getReceiverName())
                .setText(R.id.address_phone, item.getReceiverMobile())
                .setText(R.id.address_address, item.getReceiverState().equals(item.getReceiverCity())?address2:address)
                .addOnClickListener(R.id.address_delect)
        .addOnClickListener(R.id.address_edit)
        .addOnClickListener(R.id.rl_isdefaul);
        if("1".equals(item.getIsDefault())){
            helper.setImageResource(R.id.iv_isdefaul, R.mipmap.ic_checked);
            helper.setTextColor(R.id.address_isdefaul, Color.parseColor("#FFD21E"));
        }else {
            helper.setImageResource(R.id.iv_isdefaul, R.mipmap.ic_uncheck);
            helper.setTextColor(R.id.address_isdefaul, Color.parseColor("#333333"));
        }

    }

}
