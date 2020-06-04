package com.khmelenko.lab.varis.network.response;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Dao the Requests response
 *
 * @author Dmytro Khmelenko
 */
public class Requests {

@SerializedName("requests") private List<RequestData> mRequests;

@SerializedName("commits") private List<Commit> mCommits;

private transient List<Build> mBuilds;

public List<RequestData> getRequests() {
	return mRequests;
}

public void setRequests(final List<RequestData> requests) {
	mRequests = requests;
}

public List<Commit> getCommits() {
	return mCommits;
}

public void setCommits(final List<Commit> commits) {
	mCommits = commits;
}

public List<Build> getBuilds() {
	return mBuilds;
}

public void setBuilds(final List<Build> builds) {
	mBuilds = builds;
}
}
