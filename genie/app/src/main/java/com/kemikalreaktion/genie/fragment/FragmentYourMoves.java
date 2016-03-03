package com.kemikalreaktion.genie.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.core.ActivityMain;

public class FragmentYourMoves extends Fragment {
    private static final String PAGE_TITLE = "Your Moves";

    public static FragmentYourMoves newInstance(String s) {
        FragmentYourMoves fragment = new FragmentYourMoves();
        Bundle args = new Bundle();
        args.putString(PAGE_TITLE, s);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentYourMoves() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_your_moves, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityMain) activity).setTitle(PAGE_TITLE);
    }
}
