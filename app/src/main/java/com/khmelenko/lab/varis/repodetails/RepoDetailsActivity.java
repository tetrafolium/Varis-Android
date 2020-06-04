package com.khmelenko.lab.varis.repodetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.adapter.SmartFragmentStatePagerAdapter;
import com.khmelenko.lab.varis.builddetails.BuildDetailsActivity;
import com.khmelenko.lab.varis.mvp.MvpActivity;
import com.khmelenko.lab.varis.network.response.Branches;
import com.khmelenko.lab.varis.network.response.BuildHistory;
import com.khmelenko.lab.varis.network.response.Requests;
import dagger.android.AndroidInjection;
import javax.inject.Inject;

/**
 * Repository Details Activity
 *
 * @author Dmytro Khmelenko
 */
public final class RepoDetailsActivity extends MvpActivity<RepoDetailsPresenter>
    implements RepoDetailsView, BuildHistoryFragment.BuildHistoryListener,
               BranchesFragment.BranchesListener,
               PullRequestsFragment.PullRequestsListener {

  private static final int BUILD_DETAILS_REQUEST_CODE = 0;

  public static final String REPO_SLUG_KEY = "RepoSlug";
  public static final String RELOAD_REQUIRED_KEY = "ReloadRequiredKey";

  @Inject RepoDetailsPresenter mPresenter;

  private boolean mReloadRequired;
  private boolean mInitialLoad = true;
  private PagerAdapter mAdapterViewPager;

  /**
   * Custom adapter for view pager
   */
  private class PagerAdapter extends SmartFragmentStatePagerAdapter {
    private static final int ITEMS_COUNT = 3;
    private static final int INDEX_BUILD_HISTORY = 0;
    private static final int INDEX_BRANCHES = 1;
    private static final int INDEX_PULL_REQUESTS = 2;

    PagerAdapter(final FragmentManager fragmentManager) {
      super(fragmentManager);
    }

    @Override
    public int getCount() {
      return ITEMS_COUNT;
    }

    @Override
    public Fragment getItem(final int position) {
      switch (position) {
      case INDEX_BUILD_HISTORY:
        return BuildHistoryFragment.newInstance();
      case INDEX_BRANCHES:
        return BranchesFragment.newInstance();
      case INDEX_PULL_REQUESTS:
        return PullRequestsFragment.newInstance();
      default:
        return null;
      }
    }

    @Override
    public CharSequence getPageTitle(final int position) {
      switch (position) {
      case INDEX_BUILD_HISTORY:
        return getString(R.string.repo_details_tab_build_history);
      case INDEX_BRANCHES:
        return getString(R.string.repo_details_tab_branches);
      case INDEX_PULL_REQUESTS:
        return getString(R.string.repo_details_tab_pull_requests);
      default:
        return null;
      }
    }
  }

  @Override
  protected void onCreate(final Bundle savedInstanceState) {
    AndroidInjection.inject(this);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_details);
    ButterKnife.bind(this);

    initToolbar();

    // setting view pager
    ViewPager vpPager = findViewById(R.id.repo_details_view_pager);
    mAdapterViewPager = new PagerAdapter(getSupportFragmentManager());
    vpPager.setAdapter(mAdapterViewPager);
    vpPager.setOffscreenPageLimit(PagerAdapter.ITEMS_COUNT);

    TabLayout tabLayout = findViewById(R.id.repo_details_view_tabs);
    tabLayout.setupWithViewPager(vpPager);
  }

  @Override
  protected RepoDetailsPresenter getPresenter() {
    return mPresenter;
  }

  @Override
  protected void attachPresenter() {
    getPresenter().attach(this);

    String repoSlug = getIntent().getStringExtra(REPO_SLUG_KEY);
    String projectName = repoSlug.substring(repoSlug.indexOf("/") + 1);
    setTitle(projectName);

    getPresenter().setRepoSlug(repoSlug);

    if (mInitialLoad || mReloadRequired) {
      mInitialLoad = false;
      getPresenter().loadData();
    }
  }

  @Override
  public void showProgress() {
    // do nothing, each fragment of the page adapter is responsible for showing
    // progress
  }

  @Override
  public void hideProgress() {
    // do nothing, each fragment of the page adapter is responsible for hiding
    // progress
  }

  @Override
  public void updateBuildHistory(final BuildHistory buildHistory) {
    BuildHistoryFragment fragment =
        (BuildHistoryFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_BUILD_HISTORY);
    fragment.setBuildHistory(buildHistory);
  }

  @Override
  public void updateBranches(final Branches branches) {
    BranchesFragment fragment =
        (BranchesFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_BRANCHES);
    fragment.setBranches(branches);
  }

  @Override
  public void updatePullRequests(final Requests requests) {
    PullRequestsFragment fragment =
        (PullRequestsFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_PULL_REQUESTS);
    fragment.setPullRequests(requests);
  }

  @Override
  public void showBuildHistoryLoadingError(final String message) {
    BuildHistoryFragment fragment =
        (BuildHistoryFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_BUILD_HISTORY);
    fragment.setBuildHistory(null);

    String msg =
        getString(R.string.error_failed_loading_build_history, message);
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showBranchesLoadingError(final String message) {
    BranchesFragment fragment =
        (BranchesFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_BRANCHES);
    fragment.setBranches(null);

    String msg = getString(R.string.error_failed_loading_branches, message);
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void showPullRequestsLoadingError(final String message) {
    PullRequestsFragment fragment =
        (PullRequestsFragment)mAdapterViewPager.getRegisteredFragment(
            PagerAdapter.INDEX_PULL_REQUESTS);
    fragment.setPullRequests(null);

    String msg =
        getString(R.string.error_failed_loading_pull_requests, message);
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
  public void onBuildSelected(final long buildId) {
    goToBuildDetails(buildId);
  }

  @Override
  public void onReloadBuildHistory() {
    getPresenter().loadBuildsHistory();
  }

  @Override
  public void onBranchSelected(final long buildId) {
    goToBuildDetails(buildId);
  }

  @Override
  public void onReloadBranches() {
    getPresenter().loadBranches();
  }

  @Override
  public void onPullRequestSelected(final long buildId) {
    goToBuildDetails(buildId);
  }

  @Override
  public void onReloadPullRequests() {
    getPresenter().loadRequests();
  }

  /**
   * Navigates to the build details
   *
   * @param buildId Build ID
   */
  private void goToBuildDetails(final long buildId) {
    Intent intent = new Intent(this, BuildDetailsActivity.class);
    intent.putExtra(BuildDetailsActivity.EXTRA_BUILD_ID, buildId);
    intent.putExtra(BuildDetailsActivity.EXTRA_REPO_SLUG,
                    getPresenter().getRepoSlug());
    startActivityForResult(intent, BUILD_DETAILS_REQUEST_CODE);
  }

  @Override
  protected void onActivityResult(final int requestCode, final int resultCode,
                                  final Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      if (requestCode == BUILD_DETAILS_REQUEST_CODE) {
        mReloadRequired |= data.getBooleanExtra(
            BuildDetailsActivity.BUILD_STATE_CHANGED, false);
      }
    }
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent();
    intent.putExtra(RELOAD_REQUIRED_KEY, mReloadRequired);
    setResult(RESULT_OK, intent);

    super.onBackPressed();
  }
}
