package edeneastapps.sentimentalmoodjournal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewEntryActivity extends AppCompatActivity {

    @BindView(R.id.new_entry_input)
    TextView mEntryInput;

    @BindView(R.id.new_entry_done_button)
    ImageView mDoneButton;

    private boolean mIsChanged = false;
    EntryViewModel mEntryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);

        ButterKnife.bind(this);

        initViewModel();
        watchEntryInput();
    }

    void initViewModel(){
        mEntryViewModel = new EntryViewModel(getApplication());
    }

    @OnClick(R.id.new_entry_back_button)
    void setBackButtonListener(){
        if(!mIsChanged){
            super.onBackPressed();
        }
        else{
            //notify user changes were made
            notifyChange();
        }
    }

    @OnClick(R.id.new_entry_done_button)
    void setDoneButtonListener(){
        saveEntry();
    }

    void watchEntryInput(){
        mEntryInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(mEntryInput.getText().length() > 0){
                    mDoneButton.setVisibility(View.VISIBLE);
                    mIsChanged = !mIsChanged;
                }
                else{
                    mDoneButton.setVisibility(View.GONE);
                    mIsChanged = !mIsChanged;
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    void notifyChange(){
         new AlertDialog.Builder(NewEntryActivity.this)
                .setTitle("Are You Sure?")
                .setMessage("Changes were made to this journal entry. If go back now you will loss your progress!")
                .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewEntryActivity.super.onBackPressed();
                    }
                }).show();
    }

    void saveEntry(){
        Entry entry = new Entry();
        entry.setTitle("Test Title");
        entry.setCreationDate(returnCurrentTimeStamp());
        entry.setContent(mEntryInput.getText().toString());
        mEntryViewModel.addEntry(entry);
        super.onBackPressed();
    }

    String returnCurrentTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return String.valueOf(calendar.getTimeInMillis());
    }
}
