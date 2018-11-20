package edeneastapps.sentimentalmoodjournal;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        ButterKnife.bind(this);

        setEntry(getEntry());
        Utils.configCardLayout(this, mLayout, R.color.cardBackground);
        Utils.configCardLayout(this, mSubmitButton, R.color.cardBackground);

    }

    Entry getEntry(){
        return getIntent().getParcelableExtra("entry");
    }

    void setEntry(Entry entry){
        mToolbarTitle.setText(entry.getTitle());
        mEntryContent.setText(entry.getContent());
    }

    @OnClick(R.id.edit_back_button)
    void setBackButtonListener(){
        finish();
    }
}
