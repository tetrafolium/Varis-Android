package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Dao for commits
 *
 * @author Dmytro Khmelenko
 */
public final class Commit {

  @SerializedName("id") private long mId;

  @SerializedName("sha") private String mSha;

  @SerializedName("branch") private String mBranch;

  @SerializedName("message") private String mMessage;

  @SerializedName("committed_at") private String mCommitedAt;

  @SerializedName("author_name") private String mAuthorName;

  @SerializedName("author_email") private String mAuthorEmail;

  @SerializedName("committer_name") private String mCommitterName;

  @SerializedName("committer_email") private String mCommitterEmail;

  @SerializedName("compare_url") private String mCompareUrl;

  public long getId() { return mId; }

  public void setId(final long id) { mId = id; }

  public String getSha() { return mSha; }

  public void setSha(final String sha) { mSha = sha; }

  public String getBranch() { return mBranch; }

  public void setBranch(final String branch) { mBranch = branch; }

  public String getMessage() { return mMessage; }

  public void setMessage(final String message) { mMessage = message; }

  public String getCommitedAt() { return mCommitedAt; }

  public void setCommitedAt(final String commitedAt) {
    mCommitedAt = commitedAt;
  }

  public String getAuthorName() { return mAuthorName; }

  public void setAuthorName(final String authorName) {
    mAuthorName = authorName;
  }

  public String getAuthorEmail() { return mAuthorEmail; }

  public void setAuthorEmail(final String authorEmail) {
    mAuthorEmail = authorEmail;
  }

  public String getCommitterName() { return mCommitterName; }

  public void setCommitterName(final String committerName) {
    mCommitterName = committerName;
  }

  public String getCommitterEmail() { return mCommitterEmail; }

  public void setCommitterEmail(final String committerEmail) {
    mCommitterEmail = committerEmail;
  }

  public String getCompareUrl() { return mCompareUrl; }

  public void setCompareUrl(final String compareUrl) {
    mCompareUrl = compareUrl;
  }
}
