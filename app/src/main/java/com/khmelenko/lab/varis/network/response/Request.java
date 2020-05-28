package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Dao for Request response
 *
 * @author Dmytro Khmelenko
 */
public final class Request {

    @SerializedName("request")
    private RequestData mRequestData;

    @SerializedName("commit")
    private Commit mCommit;

    public RequestData getRequestData() {
        return mRequestData;
    }

    public void setRequestData(final RequestData request) {
        mRequestData = request;
    }

    public Commit getCommit() {
        return mCommit;
    }

    public void setCommit(final Commit commit) {
        mCommit = commit;
    }
}
