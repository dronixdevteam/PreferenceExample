package com.ExamplePref;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class ShowSettingsActivity extends Activity {

 @Override
 protected void onCreate(Bundle savedInstanceState) {

  super.onCreate(savedInstanceState);
  setContentView(R.layout.show_settings_layout);

  SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);

  StringBuilder builder = new StringBuilder();

  builder.append("\n" + sharedPrefs.getBoolean("perform_updates", false));
  builder.append("\n" + sharedPrefs.getString("Dronix advanced menu", "-1"));
  builder.append("\n" + sharedPrefs.getString("Remove Data Icon", "NULL"));

  TextView settingsTextView = (TextView) findViewById(R.id.settings_text_view);
  settingsTextView.setText(builder.toString());

 }

}

