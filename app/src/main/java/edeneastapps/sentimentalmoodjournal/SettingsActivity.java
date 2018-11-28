package edeneastapps.sentimentalmoodjournal;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.reminder_layout)
    ConstraintLayout mReminderLayout;

    @BindView(R.id.theme_layout)
    ConstraintLayout mThemeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ButterKnife.bind(this);

        Utils.configCardLayout(this, mReminderLayout, R.color.cardBackground);
        Utils.configCardLayout(this, mThemeLayout, R.color.cardBackground);
    }
}
