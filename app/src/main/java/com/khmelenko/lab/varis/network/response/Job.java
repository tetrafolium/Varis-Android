package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Repository job
 *
 * @author Dmytro Khmelenko
 */
public final class Job {

    @SerializedName("id")
    private long mId;

    @SerializedName("repository_id")
    private long mRepositoryId;

    @SerializedName("build_id")
    private long mBuildId;

    @SerializedName("commit_id")
    private long mCommitId;

    @SerializedName("log_id")
    private long mLogId;

    @SerializedName("state")
    private String mState;

    @SerializedName("number")
    private String mNumber;

    @SerializedName("started_at")
    private String mStartedAt;

    @SerializedName("finished_at")
    private String mFinishedAt;

    @SerializedName("queue")
    private String mQueue;

    @SerializedName("allow_failure")
    private boolean mAllowFailure;

    // TODO
    // @SerializedName("tags")

    // TODO
    // @SerializedName("annotation_ids")


    public long getId() {
        return mId;
    }

    public void setId(final long id) {
        mId = id;
    }

    public long getRepositoryId() {
        return mRepositoryId;
    }

    public void setRepositoryId(final long repositoryId) {
        mRepositoryId = repositoryId;
    }

    public long getBuildId() {
        return mBuildId;
    }

    public void setBuildId(final long buildId) {
        mBuildId = buildId;
    }

    public long getCommitId() {
        return mCommitId;
    }

    public void setCommitId(final long commitId) {
        mCommitId = commitId;
    }

    public long getLogId() {
        return mLogId;
    }

    public void setLogId(final long logId) {
        mLogId = logId;
    }

    public String getState() {
        return mState;
    }

    public void setState(final String state) {
        mState = state;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(final String number) {
        mNumber = number;
    }

    public String getStartedAt() {
        return mStartedAt;
    }

    public void setStartedAt(final String startedAt) {
        mStartedAt = startedAt;
    }

    public String getFinishedAt() {
        return mFinishedAt;
    }

    public void setFinishedAt(final String finishedAt) {
        mFinishedAt = finishedAt;
    }

    public String getQueue() {
        return mQueue;
    }

    public void setQueue(final String queue) {
        mQueue = queue;
    }

    public boolean isAllowFailure() {
        return mAllowFailure;
    }

    public void setAllowFailure(final boolean allowFailure) {
        mAllowFailure = allowFailure;
    }
}
