package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Dao for Build
 *
 * @author Dmytro Khmelenko
 */
public final class Build implements IBuildState {

    @SerializedName("id")
    private long mId;

    @SerializedName("repository_id")
    private long mRepositoryId;

    @SerializedName("commit_id")
    private long mCommitId;

    @SerializedName("number")
    private String mNumber;

    @SerializedName("pull_request")
    private boolean mPullRequest;

    @SerializedName("pull_request_number")
    private String mPullRequestNumber;

    @SerializedName("pull_request_title")
    private String mPullRequestTitle;

//    private Config mConfig;

    @SerializedName("state")
    private String mState;

    @SerializedName("started_at")
    private String mStartedAt;

    @SerializedName("finished_at")
    private String mFinishedAt;

    @SerializedName("duration")
    private long mDuration;

    @SerializedName("job_ids")
    private List<Long> mJobIds;

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public void setId(final long id) {
        mId = id;
    }

    @Override
    public long getRepositoryId() {
        return mRepositoryId;
    }

    @Override
    public void setRepositoryId(final long repositoryId) {
        mRepositoryId = repositoryId;
    }

    @Override
    public long getCommitId() {
        return mCommitId;
    }

    @Override
    public void setCommitId(final long commitId) {
        mCommitId = commitId;
    }

    @Override
    public String getNumber() {
        return mNumber;
    }

    @Override
    public void setNumber(final String number) {
        mNumber = number;
    }

    @Override
    public boolean isPullRequest() {
        return mPullRequest;
    }

    @Override
    public void setPullRequest(final boolean pullRequest) {
        mPullRequest = pullRequest;
    }

    public String getPullRequestNumber() {
        return mPullRequestNumber;
    }

    public void setPullRequestNumber(final String pullRequestNumber) {
        mPullRequestNumber = pullRequestNumber;
    }

    public String getPullRequestTitle() {
        return mPullRequestTitle;
    }

    public void setPullRequestTitle(final String pullRequestTitle) {
        mPullRequestTitle = pullRequestTitle;
    }

    @Override
    public String getState() {
        return mState;
    }

    @Override
    public void setState(final String state) {
        mState = state;
    }

    @Override
    public String getStartedAt() {
        return mStartedAt;
    }

    @Override
    public void setStartedAt(final String startedAt) {
        mStartedAt = startedAt;
    }

    @Override
    public String getFinishedAt() {
        return mFinishedAt;
    }

    @Override
    public void setFinishedAt(final String finishedAt) {
        mFinishedAt = finishedAt;
    }

    @Override
    public long getDuration() {
        return mDuration;
    }

    @Override
    public void setDuration(final long duration) {
        mDuration = duration;
    }

    @Override
    public List<Long> getJobIds() {
        return mJobIds;
    }

    @Override
    public void setJobIds(final List<Long> jobIds) {
        mJobIds = jobIds;
    }
}
