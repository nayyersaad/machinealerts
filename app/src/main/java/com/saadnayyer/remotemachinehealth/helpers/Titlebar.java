package com.saadnayyer.remotemachinehealth.helpers;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.saadnayyer.remotemachinehealth.R;
import com.saadnayyer.remotemachinehealth.databinding.TitlebarBinding;

public class Titlebar extends RelativeLayout{
    TitlebarBinding binding;

    public Titlebar(Context context) {
        super(context);
        initLayout(context);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    private void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        binding = DataBindingUtil.inflate(inflater, R.layout.titlebar, this, true);
    }

    public void hideTitlebar() {
        binding.rlTitlebarMainLayout.setVisibility(View.GONE);
    }

    public void showTitlebar() {
        binding.rlTitlebarMainLayout.setVisibility(View.VISIBLE);
    }

    public void resetTitlebar() {
        binding.ibBackbtn.setVisibility(View.GONE);
        binding.ibNotification.setVisibility(View.GONE);
        binding.tvTitle.setVisibility(View.GONE);
    }

    public void setTitle(String title) {
        resetTitlebar();
        binding.tvTitle.setVisibility(VISIBLE);
        binding.tvTitle.setText(title.trim());
    }

    public ImageButton showBackButton() {
        showTitlebar();
        binding.ibBackbtn.setVisibility(View.VISIBLE);
        return binding.ibBackbtn;
    }

    public ImageButton showNotification() {
        showTitlebar();
        binding.ibNotification.setVisibility(View.VISIBLE);
        return binding.ibNotification;
    }
}
