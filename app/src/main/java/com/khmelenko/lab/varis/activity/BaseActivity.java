package com.khmelenko.lab.varis.activity;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Base activity
 *
 * @author Dmytro Khmelenko
 */
public abstract class BaseActivity extends AppCompatActivity {

  /**
   * Adds new fragment
   *
   * @param containerViewId ID of the container view for fragment
   * @param fragment        Fragment instance
   * @param fragmentTag     Fragment tag
   */
  protected void addFragment(final @IdRes int containerViewId,
                             final @NonNull Fragment fragment,
                             final @NonNull String fragmentTag) {
    if (!fragment.isAdded()) {
      getSupportFragmentManager()
          .beginTransaction()
          .add(containerViewId, fragment, fragmentTag)
          .disallowAddToBackStack()
          .commit();
    }
  }

  /**
   * Replaces fragment
   *
   * @param containerViewId    ID of the container view for fragment
   * @param fragment           Fragment instance
   * @param fragmentTag        Fragment tag
   * @param backStackStateName Name in back stack
   */
  protected void replaceFragment(final @IdRes int containerViewId,
                                 final @NonNull Fragment fragment,
                                 final @NonNull String fragmentTag,
                                 final @Nullable String backStackStateName) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerViewId, fragment, fragmentTag)
        .addToBackStack(backStackStateName)
        .commit();
  }

  /**
   * Replaces fragment
   *
   * @param containerViewId ID of the container view for fragment
   * @param fragment        Fragment instance
   * @param fragmentTag     Fragment tag
   */
  protected void replaceFragment(final @IdRes int containerViewId,
                                 final @NonNull Fragment fragment,
                                 final @NonNull String fragmentTag) {
    getSupportFragmentManager()
        .beginTransaction()
        .replace(containerViewId, fragment, fragmentTag)
        .commit();
  }

  /**
   * Detaches fragment
   *
   * @param fragment Fragment
   */
  protected void detachFragment(final Fragment fragment) {
    getSupportFragmentManager().beginTransaction().detach(fragment).commit();
  }
}
