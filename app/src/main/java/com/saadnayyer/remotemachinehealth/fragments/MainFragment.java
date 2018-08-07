package com.saadnayyer.remotemachinehealth.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.saadnayyer.remotemachinehealth.activities.MainActivity;
import com.saadnayyer.remotemachinehealth.helpers.Titlebar;

public abstract class MainFragment<T> extends Fragment {
    MainActivity mainActivityContext;

    public abstract void setTitlebar(Titlebar titlebar);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mainActivityContext = (MainActivity) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mainActivityContext != null)
            setTitlebar(mainActivityContext.getTitlebar());
    }

}
