package com.khmelenko.lab.varis.auth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.mvp.MvpActivity;
import com.khmelenko.lab.varis.mvp.MvpPresenter;
import com.khmelenko.lab.varis.util.PresenterKeeper;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Authentication activity
 *
 * @author Dmytro Khmelenko
 */
public final class AuthActivity extends MvpActivity<AuthPresenter> implements
                                                                   AuthView,
                                                                   AuthFragment.OnLoginActionListener,
                                                                   SecurityCodeFragment.OnSecurityCodeAction {

    private static final String AUTH_FRAGMENT_TAG = "AuthFragment";
    private static final String SECURITY_CODE_FRAGMENT_TAG = "SecurityCodeFragment";

    private ProgressDialog mProgressDialog;

    @Inject
    AuthPresenter mPresenter;

    @Inject
    PresenterKeeper<MvpPresenter> mPresenterKeeper;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);

        initToolbar();
    }

    /**
     * Shows login section
     */
    private void showLoginSection() {
        AuthFragment authFragment = (AuthFragment) getSupportFragmentManager().findFragmentByTag(AUTH_FRAGMENT_TAG);
        if (authFragment == null) {
            authFragment = AuthFragment.newInstance(mPresenter.getServerUrl());
            addFragment(R.id.auth_container, authFragment, AUTH_FRAGMENT_TAG);
        }
    }

    /**
     * Shows the input for security code
     */
    private void showSecurityCodeInput() {
        SecurityCodeFragment securityCodeFragment = (SecurityCodeFragment) getSupportFragmentManager().findFragmentByTag(SECURITY_CODE_FRAGMENT_TAG);
        if (securityCodeFragment == null) {
            securityCodeFragment = SecurityCodeFragment.newInstance();
            replaceFragment(R.id.auth_container, securityCodeFragment, SECURITY_CODE_FRAGMENT_TAG);
        }
    }

    @Override
    protected AuthPresenter getPresenter() {
        MvpPresenter presenter = mPresenterKeeper.get(AuthPresenter.class);
        if (presenter != null) {
            mPresenter = (AuthPresenter) presenter;
        }
        return mPresenter;
    }

    @Override
    protected void attachPresenter() {
        getPresenter().attach(this);
    }

    @Override
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterKeeper.put(AuthPresenter.class, mPresenter);
    }

    /**
     * Initializes toolbar
     */
    private void initToolbar() {
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    @Override
    public void onLogin(final String userName, final String password) {
        showProgress();
        getPresenter().login(userName, password);
    }

    @Override
    public void onChangeServer(final String newServer) {
        getPresenter().updateServer(newServer);
    }

    @Override
    public void onSecurityCodeInput(final String securityCode) {
        showProgress();
        getPresenter().twoFactorAuth(securityCode);
    }

    @Override
    public void showProgress() {
        mProgressDialog = ProgressDialog.show(this, "", getString(R.string.loading_msg));
    }

    @Override
    public void hideProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void finishView() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void showErrorMessage(final String message) {
        String msg = getString(R.string.error_failed_auth, message);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTwoFactorAuth() {
        showSecurityCodeInput();
    }

    @Override
    public void setInputView(final boolean securityCodeInput) {
        if (securityCodeInput) {
            showSecurityCodeInput();
        } else {
            showLoginSection();
        }
    }
}
