package com.kemikalreaktion.genie.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.core.ActivityMain;
import com.kemikalreaktion.genie.util.Trick;
import com.kemikalreaktion.genie.util.TrickCatalog;

public class FragmentBrowseMoves extends Fragment {
    private static final String PAGE_TITLE = "Browse Moves";
    private ListView browseListView;

    public static FragmentBrowseMoves newInstance(String s) {
        FragmentBrowseMoves fragment = new FragmentBrowseMoves();
        Bundle args = new Bundle();
        args.putString(PAGE_TITLE, s);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentBrowseMoves() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse_moves, container, false);

        browseListView = (ListView) rootView.findViewById(R.id.browse_list_view);
        browseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectTrick(position);
            }
        });
        browseListView.setAdapter(new ArrayAdapter<Trick>(
                ((ActionBarActivity) getActivity()).getSupportActionBar().getThemedContext(),
                R.layout.trick_row,
                R.id.trickRowText,
                TrickCatalog.getAllTricks()) {
        });
        browseListView.setTextFilterEnabled(true);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((ActivityMain) activity).setTitle(PAGE_TITLE);
    }

    private void selectTrick(int position) {
        Toast.makeText(getActivity().getBaseContext(), TrickCatalog.findById(position+1) + " selected!", Toast.LENGTH_SHORT).show();
    }
}
