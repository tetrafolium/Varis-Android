package com.khmelenko.lab.varis.network.retrofit;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Types adapter for parsing JSON structures
 *
 * @author Dmytro Khmelenko
 */
public final class ItemTypeAdapterFactory implements TypeAdapterFactory {

    public <T> TypeAdapter<T> create(Gson gson, final TypeToken<T> type) {

        final TypeAdapter<T> delegate = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            public void write(JsonWriter out, T value) throws IOException {
                delegate.write(out, value);
            }

            public T read(JsonReader in) throws IOException {

                JsonElement jsonElement = elementAdapter.read(in);
                if (jsonElement.isJsonObject()) {
                    JsonObject jsonObject = jsonElement.getAsJsonObject();
                    // fetch repositories
                    if (jsonObject.has("repos") && jsonObject.get("repos").isJsonArray()) {
                        jsonElement = jsonObject.get("repos");
                    }

                    // fetch user
                    if (jsonObject.has("user") && jsonObject.get("user").isJsonObject()) {
                        jsonElement = jsonObject.get("user");
                    }

                    // NOTE Add more objects here, if needed
                }

                return delegate.fromJsonTree(jsonElement);
            }
        } .nullSafe();
    }
}
