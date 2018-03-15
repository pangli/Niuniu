package com.zorro.core.preference;


import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

public abstract class AbstractPreferenceManager {

    private SharedPreferences sp;

    protected AbstractPreferenceManager(Context context, String name) {
        sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    protected void clear() {
        sp.edit().clear().apply();
    }

    protected String getString(String name, String defaultValue) {
        return sp.getString(name, defaultValue);
    }

    protected void putString(String name, String value) {
        sp.edit().putString(name, value).apply();
    }

    protected Set<String> getStringSet(String name, Set<String> defaultValue) {
        return sp.getStringSet(name, defaultValue);
    }

    protected void putStringSet(String name, Set<String> stringSet) {
        sp.edit().putStringSet(name, stringSet).apply();
    }

    protected int getInt(String name, int defaultValue) {
        return sp.getInt(name, defaultValue);
    }

    protected void putInt(String name, int value) {
        sp.edit().putInt(name, value).apply();
    }

    protected long getLong(String name, long defaultValue) {
        return sp.getLong(name, defaultValue);
    }

    protected void putLong(String name, long value) {
        sp.edit().putLong(name, value).apply();
    }

    protected void putFloat(String name, float value) {
        sp.edit().putFloat(name, value).apply();
    }

    protected boolean getBoolean(String name, boolean defaultValue) {
        return sp.getBoolean(name, defaultValue);
    }

    protected void putBoolean(String name, boolean value) {
        sp.edit().putBoolean(name, value).apply();
    }

}
