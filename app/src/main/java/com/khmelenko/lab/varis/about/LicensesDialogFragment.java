package com.khmelenko.lab.varis.about;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.webkit.WebView;
import com.khmelenko.lab.varis.R;

/**
 * Dialog with licenses
 *
 * @author Dmytro Khmelenko (d.khmelenko@gmail.com)
 */
public class LicensesDialogFragment extends DialogFragment {

public static LicensesDialogFragment newInstance() {
	return new LicensesDialogFragment();
}

@NonNull
@Override
public Dialog onCreateDialog(final Bundle savedInstanceState) {
	WebView view = (WebView)LayoutInflater.from(getActivity())
	               .inflate(R.layout.dialog_licenses, null);
	view.loadUrl("file:///android_asset/opensource_licenses.html");
	return new AlertDialog.Builder(getActivity())
	       .setTitle(getString(R.string.dialog_licenses_title))
	       .setView(view, 0, 25, 0, 0)
	       .create();
}
}
