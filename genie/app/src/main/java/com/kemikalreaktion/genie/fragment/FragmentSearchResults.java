package com.kemikalreaktion.genie.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kemikalreaktion.genie.R;
import com.kemikalreaktion.genie.util.Trick;
import com.kemikalreaktion.genie.util.TrickCatalog;

import java.util.ArrayList;

public class FragmentSearchResults extends Fragment  {
    private static final String TAG = "FragmentSearchResults";
    private static final String PAGE_TITLE = "Search Results for ";
    private ListView searchListView;
    private ArrayAdapter<Trick> searchArrayAdapter;
    private TextView noSearchResults;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_results, container, false);

        searchArrayAdapter = new ArrayAdapter<Trick>(
                ((AppCompatActivity) getActivity()).getSupportActionBar().getThemedContext(),
                R.layout.trick_row,
                R.id.trickRowText,
                new ArrayList<Trick>());

        searchListView = (ListView) rootView.findViewById(R.id.results_list_view);
        searchListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectTrick(position);
            }
        });
        searchListView.setAdapter(searchArrayAdapter);
        searchListView.setTextFilterEnabled(true);

        noSearchResults = (TextView) rootView.findViewById(R.id.search_no_results_text);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private void selectTrick(int position) {
        Toast.makeText(getActivity().getBaseContext(), TrickCatalog.findById(position) + " clicked!", Toast.LENGTH_LONG).show();
    }

    public void getSearchResults(String query) {
        searchArrayAdapter.clear();
        searchArrayAdapter.addAll(TrickCatalog.findWithName(query));
        searchArrayAdapter.notifyDataSetChanged();

        noSearchResults.setVisibility((searchArrayAdapter.isEmpty()) ? View.VISIBLE : View.GONE);
    }
}
