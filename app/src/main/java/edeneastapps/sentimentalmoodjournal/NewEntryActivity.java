package edeneastapps.sentimentalmoodjournal;

import android.content.Intent;
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
    int mSelectedMood = 2;
    EntryViewModel mEntryViewModel;

    //should enum this
    final int ANGER = 1;
    final int SAD = 2;
    final int CONFUSED = 3;
    final int CONTENT = 4;
    final int HAPPY = 5;

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

    @OnClick(R.id.angry_button)
    void setAngryButtonListener(){
        mSelectedMood = ANGER;
    }

    @OnClick(R.id.sad_button)
    void setSadButtonListener(){
        mSelectedMood = SAD;
    }

    @OnClick(R.id.confused_button)
    void setConfusedButtonListener(){
        mSelectedMood = CONFUSED;
    }

    @OnClick(R.id.content_button)
    void setContentButtonListener(){
        mSelectedMood = CONTENT;
    }

    @OnClick(R.id.happy_button)
    void setHappyButtonListener(){
        mSelectedMood = HAPPY;
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
                .setPositiveButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss())
                .setNegativeButton("Confirm", (dialogInterface, i) -> NewEntryActivity.super.onBackPressed()).show();
    }

    void saveEntry(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        Entry entry = new Entry();
        entry.setTimestamp(returnTimeCreated(calendar));
        entry.setDatestamp(returnDateCreated(calendar));
        entry.setTitle("Test Title");
        entry.setContent(mEntryInput.getText().toString());
        entry.setMood(mSelectedMood);
        mEntryViewModel.addEntry(entry);
        startNetworkService(entry);
        super.onBackPressed();
    }

    String returnDateCreated(Calendar calendar){
        return String.valueOf(calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.YEAR));
    }

    String returnTimeCreated(Calendar calendar){
        String time = String.valueOf(calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
        String amOrPm = calendar.get(Calendar.AM_PM) == 0 ? "am" : "pm";
        return time + " " + amOrPm;
    }

    void startNetworkService(Entry entry){
        Intent intent = new Intent(this, TwinWorldNetworkService.class);
        intent.setClass(this, TwinWorldNetworkService.class);
        intent.putExtra("entry", entry);
        startService(intent);
    }
}
