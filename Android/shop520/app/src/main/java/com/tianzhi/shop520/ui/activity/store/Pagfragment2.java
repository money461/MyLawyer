package com.tianzhi.shop520.ui.activity.store;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tianzhi.shop520.R;

/**
 * Created by thinkpad on 2017/10/26.
 */

public class Pagfragment2 extends Fragment {
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fram_pag1, container, false);
        return view;
    }
}