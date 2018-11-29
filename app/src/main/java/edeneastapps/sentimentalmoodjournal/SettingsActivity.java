package edeneastapps.sentimentalmoodjournal;

import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.reminder_layout)
    ConstraintLayout mReminderLayout;

    @BindView(R.id.theme_layout)
    ConstraintLayout mThemeLayout;

    @Inject SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((MainApplication)getApplication()).getSettingsComponent().inject(this);
        ButterKnife.bind(this);

        Utils.configCardLayout(this, mReminderLayout, R.color.cardBackground);
        Utils.configCardLayout(this, mThemeLayout, R.color.cardBackground);
    }

    @OnCheckedChanged(R.id.reminder_switch)
    void setReminderSwitchListener(){
        boolean reminderSetting = mSharedPreferences.getBoolean("reminderSetting", false);
        mSharedPreferences.edit().putBoolean("reminderSetting", !reminderSetting).apply();
    }

    @OnCheckedChanged(R.id.theme_switch)
    void setThemeSwitchListener(){
        boolean themeSetting = mSharedPreferences.getBoolean("reminderSetting", false);
        mSharedPreferences.edit().putBoolean("themeSetting", !themeSetting).apply();
    }

    @OnClick(R.id.settings_back_button)
    void setBackButtonListener(){
        finish();
    }
}
