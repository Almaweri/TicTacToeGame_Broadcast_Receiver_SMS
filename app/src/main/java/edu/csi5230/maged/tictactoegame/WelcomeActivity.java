package edu.csi5230.maged.tictactoegame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener{

    private EditText mEditTextPhoneNumber;
    private Button mButtonJoin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mEditTextPhoneNumber = findViewById(R.id.edit_text_phone_number);
        mButtonJoin = findViewById(R.id.btn_join);
        mButtonJoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_join) {
            // Determine the other party's mobile number
            if (!TextUtils.isEmpty(mEditTextPhoneNumber.getText())) {

                if (TextUtils.isDigitsOnly(mEditTextPhoneNumber.getText())) {
                    //Pop-up dialog box to enter your name
                    View view = LayoutInflater.from(mContext).inflate(R.layout.view_welcome_dialog_layout, null);
                    final EditText editTextName = view.findViewById(R.id.edit_text_name);
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Input your name please.")
                            .setView(view)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // todo hold the dialog unit get response from the opponent
                                    // input content
                                    if (!TextUtils.isEmpty(editTextName.getText())) {
                                        // save your name to sharedPreferences
                                        long opponentPhoneNumber = Integer.valueOf(mEditTextPhoneNumber.getText().toString());
                                        // Save into sharedPreferences
                                        SharedPreferences sf = getSharedPreferences(Constants.SF_FILE_NAME, MODE_PRIVATE);
                                        sf.edit().putLong(Constants.PHONE_NUMBER, opponentPhoneNumber).apply();
                                        sf.edit().putString(Constants.NAME, editTextName.getText().toString()).apply();
                                        // send sms to opponent
                                        SmsUtils.sendMessage(opponentPhoneNumber, "0,-1," + editTextName.getText().toString());
                                    }
                                }
                            })
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // dismiss the dialog
                                }
                            })
                            .show();
                } else {
                    System.out.println("not all number Contains non-numbers");
                    // todo toast
                }
            } else {
                System.out.println("Please enter the phone number");
                // todo toast
            }
        }
    }
}
