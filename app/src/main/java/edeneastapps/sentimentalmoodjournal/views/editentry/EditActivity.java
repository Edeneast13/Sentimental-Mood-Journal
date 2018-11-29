package edeneastapps.sentimentalmoodjournal.views.editentry;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edeneastapps.sentimentalmoodjournal.R;
import edeneastapps.sentimentalmoodjournal.application.MainApplication;
import edeneastapps.sentimentalmoodjournal.database.entry.Entry;
import edeneastapps.sentimentalmoodjournal.database.entry.EntryViewModel;
import edeneastapps.sentimentalmoodjournal.utils.Utils;

public class EditActivity extends AppCompatActivity {

    @BindView(R.id.edit_toolbar)
    ConstraintLayout mToolbar;

    @BindView(R.id.edit_layout)
    ConstraintLayout mLayout;

    @BindView(R.id.edit_entry_input)
    EditText mEntryContent;

    @BindView(R.id.edit_toolbar_title)
    TextView mToolbarTitle;

    @BindView(R.id.submit_button)
    Button mSubmitButton;

    @BindView(R.id.main_layout)
    ConstraintLayout mMainLayout;

    @Inject SharedPreferences mSharedPreferences;
    private EntryViewModel mEntryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ((MainApplication)getApplication()).getSettingsComponent().inject(this);
        ButterKnife.bind(this);

        setEntry(getEntry());
        Utils.configCardLayout(this, mLayout, R.color.cardBackground);
        Utils.configCardLayout(this, mSubmitButton, R.color.cardBackground);

        initViewModel();

        if (isDarkThemeActive()){
            setDarkTheme();
        }
    }

    void initViewModel(){
        mEntryViewModel = new EntryViewModel(getApplication());
    }

    Entry getEntry(){
        return getIntent().getParcelableExtra("entry");
    }

    void setEntry(Entry entry){
        mToolbarTitle.setText(entry.getTitle());
        mEntryContent.setText(entry.getContent());
    }

    void updateEntry(Entry entry, String updatedContent){
        entry.setContent(updatedContent);
        mEntryViewModel.updateEntry(entry);
    }

    boolean isDarkThemeActive(){
        return mSharedPreferences.getBoolean("themeSetting", false);
    }

    void setDarkTheme(){
        Resources resources = getResources();
        mToolbar.setBackgroundColor(resources.getColor(R.color.darkThemePrimary));
        mToolbarTitle.setTextColor(resources.getColor(R.color.primaryText));
        mMainLayout.setBackgroundColor(resources.getColor(R.color.darkThemeSecondary));
        Utils.configCardLayout(this, mLayout, R.color.darkThemePrimary);
        mEntryContent.setTextColor(getResources().getColor(R.color.primaryText));
        Utils.configCardLayout(this, mSubmitButton, R.color.darkThemePrimary);
        mSubmitButton.setTextColor(getResources().getColor(R.color.primaryText));
    }

    @OnClick(R.id.edit_back_button)
    void setBackButtonListener(){
        finish();
    }

    @OnClick(R.id.submit_button)
    void setSubmitButtonListener(){
        updateEntry(getEntry(), mEntryContent.getText().toString());
        finish();
    }
}
