package edeneastapps.sentimentalmoodjournal.views.settings;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import edeneastapps.sentimentalmoodjournal.R;
import edeneastapps.sentimentalmoodjournal.application.MainApplication;
import edeneastapps.sentimentalmoodjournal.utils.Utils;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.reminder_layout)
    ConstraintLayout mReminderLayout;

    @BindView(R.id.theme_layout)
    ConstraintLayout mThemeLayout;

    @BindView(R.id.settings_layout)
    ConstraintLayout mMainLayout;

    @BindView(R.id.settings_toolbar)
    ConstraintLayout mToolbar;

    @BindView(R.id.settings_toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.reminder_summary)
    TextView mReminderSummary;

    @BindView(R.id.theme_summary)
    TextView mThemeSummary;

    @BindView(R.id.version_text)
    TextView mVersionText;

    @Inject SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ((MainApplication)getApplication()).getSettingsComponent().inject(this);
        ButterKnife.bind(this);

        Utils.configCardLayout(this, mReminderLayout, R.color.cardBackground);
        Utils.configCardLayout(this, mThemeLayout, R.color.cardBackground);

        if(isDarkThemeActive()){
            setDarkTheme();
        }
    }

    boolean isDarkThemeActive(){
        return mSharedPreferences.getBoolean("themeSetting", false);
    }

    void setDarkTheme(){
        Resources resources = getResources();
        mToolbar.setBackgroundColor(resources.getColor(R.color.darkThemePrimary));
        mMainLayout.setBackgroundColor(resources.getColor(R.color.darkThemeSecondary));
        mReminderSummary.setTextColor(resources.getColor(R.color.primaryText));
        mThemeSummary.setTextColor(resources.getColor(R.color.primaryText));
        Utils.configCardLayout(this, mThemeLayout, R.color.darkThemePrimary);
        Utils.configCardLayout(this, mReminderLayout, R.color.darkThemePrimary);
        mVersionText.setTextColor(resources.getColor(R.color.darkThemePrimary));
        mToolbarTitle.setTextColor(resources.getColor(R.color.primaryText));
    }

    void setLightTheme(){
        Resources resources = getResources();
        mToolbar.setBackgroundColor(resources.getColor(R.color.lightThemePrimary));
        mMainLayout.setBackgroundColor(resources.getColor(R.color.lightThemeSecondary));
        mReminderSummary.setTextColor(resources.getColor(R.color.alternateText2));
        mThemeSummary.setTextColor(resources.getColor(R.color.alternateText2));
        Utils.configCardLayout(this, mThemeLayout, R.color.lightThemePrimary);
        Utils.configCardLayout(this, mReminderLayout, R.color.lightThemePrimary);
        mVersionText.setTextColor(resources.getColor(R.color.alternateText2));
        mToolbarTitle.setTextColor(resources.getColor(R.color.alternateText));
    }

    @OnCheckedChanged(R.id.reminder_switch)
    void setReminderSwitchListener(){
        boolean reminderSetting = mSharedPreferences.getBoolean("reminderSetting", false);
        mSharedPreferences.edit().putBoolean("reminderSetting", !reminderSetting).apply();
    }

    @OnCheckedChanged(R.id.theme_switch)
    void setThemeSwitchListener(){
        boolean themeSetting = mSharedPreferences.getBoolean("themeSetting", false);
        mSharedPreferences.edit().putBoolean("themeSetting", !themeSetting).apply();
        if (!themeSetting){
            setDarkTheme();
        }
        else{
            setLightTheme();
        }
    }

    @OnClick(R.id.settings_back_button)
    void setBackButtonListener(){
        finish();
    }
}
