package com.khmelenko.lab.varis.auth;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.khmelenko.lab.varis.R;

/**
 * Security code fragment
 *
 * @author Dmytro Khmelenko
 */
public class SecurityCodeFragment extends Fragment {

  @BindView(R.id.auth_security_code) EditText mSecurityCode;

  private OnSecurityCodeAction mListener;

  /**
   * Creates new instance of the fragment
   *
   * @return Fragment instance
   */
  public static SecurityCodeFragment newInstance() {
    return new SecurityCodeFragment();
  }

  public SecurityCodeFragment() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(final LayoutInflater inflater,
                           final ViewGroup container,
                           final Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view =
        inflater.inflate(R.layout.fragment_security_code, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @OnClick(R.id.auth_confirm_btn)
  public void startConfirmation() {
    if (TextUtils.isEmpty(mSecurityCode.getText())) {
      mSecurityCode.setError(getString(R.string.auth_invalid_security_code));
    } else {
      if (mListener != null) {
        mListener.onSecurityCodeInput(mSecurityCode.getText().toString());
      }
    }
  }

  @Override
  public void onAttach(final Context activity) {
    super.onAttach(activity);
    try {
      mListener = (OnSecurityCodeAction)activity;
    } catch (ClassCastException e) {
      throw new ClassCastException(
          activity.toString() +
          " must implement OnFragmentInteractionListener");
    }
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  /**
   * Listener on security code
   */
  public interface OnSecurityCodeAction {

    /**
     * Calles on inputted security code
     *
     * @param securityCode Security code
     */
    void onSecurityCodeInput(String securityCode);
  }
}
