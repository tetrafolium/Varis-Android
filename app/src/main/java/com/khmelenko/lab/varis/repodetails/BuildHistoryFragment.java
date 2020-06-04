package com.khmelenko.lab.varis.repodetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.adapter.OnListItemListener;
import com.khmelenko.lab.varis.network.response.Build;
import com.khmelenko.lab.varis.network.response.BuildHistory;

/**
 * Repository Build history
 *
 * @author Dmytro Khmelenko
 */
public class BuildHistoryFragment
	extends Fragment implements OnListItemListener {

@BindView(R.id.list_refreshable_swipe_view)
SwipeRefreshLayout mSwipeRefreshLayout;

@BindView(R.id.list_refreshable_recycler_view)
RecyclerView mBuildHistoryRecyclerView;

@BindView(R.id.progressbar) ProgressBar mProgressBar;

@BindView(R.id.empty_text) TextView mEmptyText;

private BuildListAdapter mBuildListAdapter;
private BuildHistory mBuildHistory;

private BuildHistoryListener mListener;

/**
 * Creates new instance of the fragment
 *
 * @return Fragment instance
 */
public static BuildHistoryFragment newInstance() {
	return new BuildHistoryFragment();
}

public BuildHistoryFragment() {
	// Required empty public constructor
}

@Override
public View onCreateView(final LayoutInflater inflater,
                         final ViewGroup container,
                         final Bundle savedInstanceState) {
	View view =
		inflater.inflate(R.layout.fragment_list_refreshable, container, false);
	ButterKnife.bind(this, view);

	mBuildHistoryRecyclerView.setHasFixedSize(true);

	LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
	mBuildHistoryRecyclerView.setLayoutManager(layoutManager);

	mBuildListAdapter = new BuildListAdapter(mBuildHistory, this);
	mBuildHistoryRecyclerView.setAdapter(mBuildListAdapter);

	mSwipeRefreshLayout.setColorSchemeResources(R.color.swipe_refresh_progress);
	mSwipeRefreshLayout.setOnRefreshListener(
		()->mListener.onReloadBuildHistory());

	mProgressBar.setVisibility(View.VISIBLE);

	return view;
}

/**
 * Checks whether data existing or not
 */
private void checkIfEmpty() {
	mEmptyText.setText(R.string.repo_details_builds_empty);
	if (mBuildHistory == null || mBuildHistory.getBuilds().isEmpty()) {
		mEmptyText.setVisibility(View.VISIBLE);
	} else {
		mEmptyText.setVisibility(View.GONE);
	}
}

@Override
public void onAttach(final Context activity) {
	super.onAttach(activity);
	try {
		mListener = (BuildHistoryListener)activity;
	} catch (ClassCastException e) {
		throw new ClassCastException(activity.toString() +
		                             " must implement BuildHistoryListener");
	}
}

@Override
public void onDetach() {
	super.onDetach();
	mListener = null;
}

@Override
public void onItemSelected(final int position) {
	if (mListener != null) {
		Build build = mBuildHistory.getBuilds().get(position);
		mListener.onBuildSelected(build.getId());
	}
}

/**
 * Sets build history
 *
 * @param buildHistory Build history
 */
public void setBuildHistory(final BuildHistory buildHistory) {
	mSwipeRefreshLayout.setRefreshing(false);
	mProgressBar.setVisibility(View.GONE);

	if (buildHistory != null) {
		mBuildHistory = buildHistory;
		mBuildListAdapter.setBuildHistory(mBuildHistory);
		mBuildListAdapter.notifyDataSetChanged();
	}

	checkIfEmpty();
}

/**
 * Interface for communication with this fragment
 */
public interface BuildHistoryListener {

/**
 * Handles build selection
 *
 * @param buildNumber Build number
 */
void onBuildSelected(long buildNumber);

/**
 * Handles reload data request
 */
void onReloadBuildHistory();
}
}
