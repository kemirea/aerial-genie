package com.kemikalreaktion.genie.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.core.ActivityMain;

/* Base fragment class for displaying each pole move */
public class BaseTrickPage extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String MOVE_NAME = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static BaseTrickPage newInstance(String moveName) {
        BaseTrickPage fragment = new BaseTrickPage();
        Bundle args = new Bundle();
        args.putString(MOVE_NAME, moveName);
        fragment.setArguments(args);
        return fragment;
    }

    public BaseTrickPage() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.base_move_page, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityMain) activity).setTitle(MOVE_NAME);
    }
}
