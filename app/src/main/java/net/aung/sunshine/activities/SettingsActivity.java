package net.aung.sunshine.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import net.aung.sunshine.R;
import net.aung.sunshine.events.DataEvent;
import net.aung.sunshine.utils.SettingsUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by aung on 2/7/16.
 */
public class SettingsActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    public static Intent newIntent(Context context) {
        Intent intentToSettings = new Intent(context, SettingsActivity.class);
        return intentToSettings;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Add 'general' preferences, defined in the XML file
        addPreferencesFromResource(R.xml.pref_general);

        // For all preferences, attach an OnPreferenceChangeListener so the UI summary can be
        // updated when the preference changes.
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_unit_key)));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        //set the listener to watch for value change.
        preference.setOnPreferenceChangeListener(this);

        //trigger the listener immediately with the preference's current value.
        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if (preference instanceof ListPreference) {
            // For list preferences, look up the correct display value in
            // the preference's 'entries' list (since they have separate labels/values).
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            } else {
                preference.setSummary(listPreference.getEntries()[0]); //user hasn't pick any unit yet. default value.
            }
        } else {
            // For other preferences, set the summary to the value's simple string representation.
            preference.setSummary(stringValue);
        }

        if (preference.getKey().equals(getString(R.string.pref_location_key))) {
            String oldCity = SettingsUtils.retrieveUserLocation();
            if (!oldCity.equalsIgnoreCase(stringValue)) {
                SettingsUtils.saveUserLocation(stringValue);
                EventBus.getDefault().post(new DataEvent.PreferenceCityChangeEvent(stringValue));
            }
        }

        return true;
    }
}
