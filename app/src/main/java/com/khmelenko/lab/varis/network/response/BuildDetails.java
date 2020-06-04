package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Build details
 *
 * @author Dmytro Khmelenko
 */
public final class BuildDetails {

  @SerializedName("build") private Build mBuild;

  @SerializedName("commit") private Commit mCommit;

  @SerializedName("jobs") private List<Job> mJobs;

  public Build getBuild() { return mBuild; }

  public void setBuild(final Build build) { mBuild = build; }

  public Commit getCommit() { return mCommit; }

  public void setCommit(final Commit commit) { mCommit = commit; }

  public List<Job> getJobs() { return mJobs; }

  public void setJobs(final List<Job> jobs) { mJobs = jobs; }
}
