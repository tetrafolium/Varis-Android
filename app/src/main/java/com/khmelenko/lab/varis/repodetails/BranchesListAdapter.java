package com.khmelenko.lab.varis.repodetails;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.adapter.OnListItemListener;
import com.khmelenko.lab.varis.adapter.viewholder.BuildViewHolder;
import com.khmelenko.lab.varis.network.response.Branch;
import com.khmelenko.lab.varis.network.response.Branches;
import com.khmelenko.lab.varis.network.response.Commit;

/**
 * List adapter for branches
 *
 * @author Dmytro Khmelenko
 */
public final class BranchesListAdapter
    extends RecyclerView.Adapter<BuildViewHolder> {

  private Branches mBranches;
  private final OnListItemListener mListener;

  public BranchesListAdapter(final Branches branches,
                             final OnListItemListener listener) {
    mBranches = branches;
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
    if (mBranches != null) {
      Branch branch = mBranches.getBranches().get(position);
      Commit relatedCommit = null;
      for (Commit commit : mBranches.getCommits()) {
        if (branch.getCommitId() == commit.getId()) {
          relatedCommit = commit;
          break;
        }
      }

      holder.mBuildView.setState(branch);
      holder.mBuildView.setCommit(relatedCommit);
    }
  }

  @Override
  public int getItemCount() {
    return mBranches != null ? mBranches.getBranches().size() : 0;
  }

  /**
   * Sets branches
   *
   * @param branches Branches
   */
  public void setBranches(final Branches branches) { mBranches = branches; }
}
