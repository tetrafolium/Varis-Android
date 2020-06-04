package com.khmelenko.lab.varis.builddetails;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.log.LogEntryComponent;

/**
 * Shows raw logs
 *
 * @author Dmytro Khmelenko
 */
public class RawLogFragment extends Fragment {

@BindView(R.id.raw_log_webview) WebView mWebView;

@BindView(R.id.progressbar) ProgressBar mProgressBar;

@BindView(R.id.empty_text) TextView mEmptyText;

private OnRawLogFragmentListener mListener;

public RawLogFragment() {

	// Required empty public constructor
}

/**
 * Creates an instance of the fragment
 *
 * @return Fragment instance
 */
public static RawLogFragment newInstance() {
	return new RawLogFragment();
}

@Override
public View onCreateView(final LayoutInflater inflater,
                         final ViewGroup container,
                         final Bundle savedInstanceState) {
	// Inflate the layout for this fragment
	View view = inflater.inflate(R.layout.fragment_raw_log, container, false);
	ButterKnife.bind(this, view);
	showProgress(true);
	showError(false);
	return view;
}

@Override
public void onAttach(final Context context) {
	super.onAttach(context);
	try {
		mListener = (OnRawLogFragmentListener)context;
	} catch (ClassCastException e) {
		throw new ClassCastException(context.toString() +
		                             " must implement OnRawLogFragmentListener");
	}
}

@Override
public void onDetach() {
	super.onDetach();
	mListener = null;
}

/**
 * Shows the loading progress of the fragment
 *
 * @param inProgress True, if the progress should be shown. False otherwise
 */
public void showProgress(final boolean inProgress) {
	if (inProgress) {
		mProgressBar.setVisibility(View.VISIBLE);
		mWebView.setVisibility(View.GONE);
	} else {
		mProgressBar.setVisibility(View.GONE);
		mWebView.setVisibility(View.VISIBLE);
	}
}

/**
 * Shows the error message
 *
 * @param isError True, if error text should be shown. False otherwise
 */
public void showError(final boolean isError) {
	if (isError) {
		mEmptyText.setVisibility(View.VISIBLE);
		mEmptyText.setText(R.string.build_details_empty);
	} else {
		mEmptyText.setVisibility(View.GONE);
	}
}

/**
 * Shows the log in the web view
 *
 * @param log Parsed log data
 */
public void showLog(final LogEntryComponent log) {
	showError(false);

	mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageFinished(final WebView view, final String url) {
			        super.onPageFinished(view, url);
			        if (mListener != null) {
			                mListener.onLogLoaded();
				}

			        showProgress(false);
			}
		});

	mWebView.loadData(log.toHtml(), "text/html", "utf-8");
}

/**
 * Interface for communication with this fragment
 */
public interface OnRawLogFragmentListener {

/**
 * Raised when raw log loaded
 */
void onLogLoaded();
}
}
