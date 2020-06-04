package com.khmelenko.lab.varis.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.khmelenko.lab.varis.R;
import com.khmelenko.lab.varis.adapter.OnListItemListener;
import com.khmelenko.lab.varis.widget.BuildView;

/**
 * View holder for the Build data
 *
 * @author Dmytro Khmelenko
 */
public final class BuildViewHolder
    extends RecyclerView.ViewHolder implements View.OnClickListener {

  @BindView(R.id.item_build_card_view) View mParent;

  @BindView(R.id.item_build_data) public BuildView mBuildView;

  private final OnListItemListener mListener;

  public BuildViewHolder(final View itemView,
                         final OnListItemListener listener) {
    super(itemView);
    mListener = listener;
    ButterKnife.bind(this, itemView);
    itemView.setClickable(true);
    mParent.setOnClickListener(this);
  }

  @Override
  public void onClick(final View view) {
    if (mListener != null) {
      mListener.onItemSelected(getLayoutPosition());
    }
  }
}
