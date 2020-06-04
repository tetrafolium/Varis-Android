package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Dao for Request
 *
 * @author Dmytro Khmelenko
 */
public final class RequestData {

    @SerializedName("id")
    private long mId;

    @SerializedName("commit_id")
    private long mCommitId;

    @SerializedName("repository_id")
    private long mRepositoryId;

    @SerializedName("created_at")
    private String mCreated;

    @SerializedName("owner_id")
    private long mOwnerId;

    @SerializedName("owner_type")
    private String mOwnerType;

    @SerializedName("event_type")
    private String mEventType;

    @SerializedName("base_commit")
    private String mBaseCommit;

    @SerializedName("head_commit")
    private String mHeadCommit;

    @SerializedName("result")
    private String mResult;

    @SerializedName("message")
    private String mMessage;

    @SerializedName("pull_request")
    private boolean mPullRequest;

    @SerializedName("pull_request_number")
    private String mPullRequestNumber;

    @SerializedName("pull_request_title")
    private String mPullRequestTitle;

    @SerializedName("branch")
    private String mBranch;

    @SerializedName("tag")
    private String mTag;

    @SerializedName("build_id")
    private long mBuildId;

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

    public long getCommitId() {
        return mCommitId;
    }

    public void setCommitId(final long commitId) {
        mCommitId = commitId;
    }

    public String getCreated() {
        return mCreated;
    }

    public void setCreated(final String created) {
        mCreated = created;
    }

    public long getOwnerId() {
        return mOwnerId;
    }

    public void setOwnerId(final long ownerId) {
        mOwnerId = ownerId;
    }

    public String getOwnerType() {
        return mOwnerType;
    }

    public void setOwnerType(final String ownerType) {
        mOwnerType = ownerType;
    }

    public String getEventType() {
        return mEventType;
    }

    public void setEventType(final String eventType) {
        mEventType = eventType;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(final String result) {
        mResult = result;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(final String message) {
        mMessage = message;
    }

    public boolean isPullRequest() {
        return mPullRequest;
    }

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

    public String getBranch() {
        return mBranch;
    }

    public void setBranch(final String branch) {
        mBranch = branch;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(final String tag) {
        mTag = tag;
    }

    public String getBaseCommit() {
        return mBaseCommit;
    }

    public void setBaseCommit(final String baseCommit) {
        mBaseCommit = baseCommit;
    }

    public String getHeadCommit() {
        return mHeadCommit;
    }

    public void setHeadCommit(final String headCommit) {
        mHeadCommit = headCommit;
    }

    public long getBuildId() {
        return mBuildId;
    }

    public void setBuildId(final long buildId) {
        mBuildId = buildId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof RequestData)) return false;
        RequestData that = (RequestData) o;
        return mPullRequestNumber.equals(that.mPullRequestNumber);
    }

    @Override
    public int hashCode() {
        return mPullRequestNumber.hashCode();
    }
}
