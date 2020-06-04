package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;

/**
 * Dao for Log
 *
 * @author Dmytro Khmelenko
 */
public final class Log {

  @SerializedName("id") private long mId;

  @SerializedName("job_id") private long mJobId;

  @SerializedName("type") private String mType;

  @SerializedName("body") private String mBody;

  public long getId() { return mId; }

  public void setId(final long id) { mId = id; }

  public long getJobId() { return mJobId; }

  public void setJobId(final long jobId) { mJobId = jobId; }

  public String getType() { return mType; }

  public void setType(final String type) { mType = type; }

  public String getBody() { return mBody; }

  public void setBody(final String body) { mBody = body; }
}
