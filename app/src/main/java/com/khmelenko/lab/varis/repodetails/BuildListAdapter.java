package com.khmelenko.lab.varis.repodetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.adapter.OnListItemListener;
import com.khmelenko.lab.varis.adapter.viewholder.BuildViewHolder;
import com.khmelenko.lab.varis.network.response.Build;
import com.khmelenko.lab.varis.network.response.BuildHistory;
import com.khmelenko.lab.varis.network.response.Commit;

/**
 * Adapter for the list of builds
 *
 * @author Dmytro Khmelenko
 */
public final class BuildListAdapter
    extends RecyclerView.Adapter<BuildViewHolder> {

  private BuildHistory mBuildHistory;
  private final OnListItemListener mListener;

  public BuildListAdapter(final BuildHistory buildHistory,
                          final OnListItemListener listener) {
    mBuildHistory = buildHistory;
    mListener = listener;
  }

  @Override
  public BuildViewHolder onCreateViewHolder(final ViewGroup parent,
                                            final int viewType) {
    View v = LayoutInflater.from(parent.getContext())
                 .inflate(R.layout.item_build_view, parent, false);
    return new BuildViewHolder(v, mListener);
  }

  @Override
  public void onBindViewHolder(final BuildViewHolder holder,
                               final int position) {
    if (mBuildHistory != null) {
      Build build = mBuildHistory.getBuilds().get(position);
      Commit relatedCommit = null;
      for (Commit commit : mBuildHistory.getCommits()) {
        if (build.getCommitId() == commit.getId()) {
          relatedCommit = commit;
          break;
        }
      }
      holder.mBuildView.setState(build);
      holder.mBuildView.setCommit(relatedCommit);
    }
  }

  @Override
  public int getItemCount() {
    return mBuildHistory != null ? mBuildHistory.getBuilds().size() : 0;
  }

  /**
   * Sets build history
   *
   * @param buildHistory Build history
   */
  public void setBuildHistory(final BuildHistory buildHistory) {
    mBuildHistory = buildHistory;
  }
}
