package com.khmelenko.lab.varis.repositories.search;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.repodetails.RepoDetailsActivity;
import com.khmelenko.lab.varis.mvp.MvpActivity;
import com.khmelenko.lab.varis.network.response.Repo;
import com.khmelenko.lab.varis.repositories.ReposFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

/**
 * Provides activity with search results
 *
 * @author Dmytro Khmelenko
 */
public final class SearchResultsActivity extends MvpActivity<SearchResultsPresenter> implements
    SearchResultsView,
    ReposFragment.ReposFragmentListener {

    @Inject
    SearchResultsPresenter mPresenter;

    private ReposFragment mFragment;

    private String mSearchQuery;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        ButterKnife.bind(this);

        mFragment = (ReposFragment) getFragmentManager().findFragmentById(R.id.search_fragment_repos);

        initToolbar();

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(final Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected SearchResultsPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    protected void attachPresenter() {
        getPresenter().attach(this);
    }

    /**
     * Handles received intent
     *
     * @param intent Intent
     */
    private void handleIntent(final Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mSearchQuery = query;
            showProgress();
            getPresenter().startRepoSearch(query);
        }
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
    public void onRepositorySelected(final Repo repo) {
        Intent intent = new Intent(SearchResultsActivity.this, RepoDetailsActivity.class);
        intent.putExtra(RepoDetailsActivity.REPO_SLUG_KEY, repo.getSlug());
        startActivity(intent);
    }

    @Override
    public void onRefreshData() {
        getPresenter().startRepoSearch(mSearchQuery);
    }

    @Override
    public void showProgress() {
        mFragment.setLoadingProgress(true);
    }

    @Override
    public void hideProgress() {
        mFragment.setLoadingProgress(false);
    }

    @Override
    public void setSearchResults(final List<Repo> repos) {
        mFragment.setRepos(repos);
    }

    @Override
    public void showLoadingError(final String message) {
        mFragment.handleLoadingFailed(message);
    }
}
