package org.telegram.messenger;

import android.content.SharedPreferences;
import java.util.HashSet;
import java.util.Set;

public class KeptDeleted {
    private static final Set<String> keys = new HashSet<>();
    private static boolean loaded;

    private static SharedPreferences prefs() {
        return ApplicationLoader.applicationContext.getSharedPreferences("kept_deleted", 0);
    }

    private static synchronized void load() {
        if (loaded) return;
        loaded = true;
        keys.addAll(prefs().getStringSet("keys", new HashSet<String>()));
    }

    public static synchronized void add(long dialogId, int id) {
        load();
        keys.add(dialogId + "_" + id);
        prefs().edit().putStringSet("keys", new HashSet<>(keys)).apply();
    }

    public static synchronized boolean contains(long dialogId, int id) {
        load();
        return keys.contains(dialogId + "_" + id);
    }
}