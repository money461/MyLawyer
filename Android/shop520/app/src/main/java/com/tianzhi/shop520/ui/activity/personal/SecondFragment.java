package com.tianzhi.shop520.ui.activity.personal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianzhi.shop520.R;


/**
 * Created by thinkpad on 2017/10/20.
 */

public class SecondFragment extends Fragment {
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_fragment2, container, false);
        return view;
    }
}