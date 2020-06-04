package com.khmelenko.lab.varis.storage;

import com.google.gson.Gson;
import com.khmelenko.lab.varis.network.response.Repo;
import com.khmelenko.lab.varis.network.response.User;
import com.khmelenko.lab.varis.util.FileUtils;
import java.util.List;

/**
 * Stores data to the cache
 *
 * @author Dmytro Khmelenko
 */
public class CacheStorage {

  private static final String USER_FILE = "UserData";
  private static final String REPOS_FILE = "ReposFile";

  private CacheStorage() {}

  public static CacheStorage newInstance() { return new CacheStorage(); }

  /**
   * Saves user to the cache
   *
   * @param user User
   */
  public void saveUser(final User user) {
    Gson gson = new Gson();
    String dataToStore = gson.toJson(user);
    FileUtils.writeInternalFile(USER_FILE, dataToStore);
  }

  /**
   * Restores user from the cache
   *
   * @return User
   */
  public User restoreUser() {
    String fileData = FileUtils.readInternalFile(USER_FILE);
    Gson gson = new Gson();
    User user = gson.fromJson(fileData, User.class);
    return user;
  }

  /**
   * Deletes the user from the cache
   */
  public void deleteUser() { FileUtils.deleteInternalFile(USER_FILE); }

  /**
   * Caches repositories data
   *
   * @param repos Repositories to cache
   */
  public void saveRepos(final List<Repo> repos) {
    StringBuilder builder = new StringBuilder();
    for (Repo repo : repos) {
      builder.append(repo.getSlug());
      builder.append(",");
    }
    FileUtils.writeInternalFile(REPOS_FILE, builder.toString());
  }

  /**
   * Restores the list of repositories
   *
   * @return Collection of repositories slugs
   */
  public String[] restoreRepos() {
    String fileData = FileUtils.readInternalFile(REPOS_FILE);
    return fileData.split(",");
  }

  /**
   * Deletes cached repositories
   */
  public void deleteRepos() { FileUtils.deleteInternalFile(REPOS_FILE); }
}
