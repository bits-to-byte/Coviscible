package com.example.coviscible.handler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.coviscible.MainActivity;
import com.example.coviscible.R;
import com.example.coviscible.model.Reminder;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewReminder {

    // activity
    Context context;
    // Date object for dob
    Date date;

    // dialog
    Dialog dialog;

    // editText
    EditText name;
    EditText description;
    EditText dateEnd;
    EditText floor;
    EditText roomNo;
    EditText bedNo;

    // button
    Button submit;
    Button cancel;

    // textView
    TextView id;

    // String
    String patientId;

    // firebase reference
    FirebaseFirestore mRef;

    int size;

    public NewReminder(Context context) {
        this.context = context;
        // this.activity = activity;
        setUp();
    }

    void setUp() {
        mRef = FirebaseFirestore.getInstance();
        dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.new_reminder_dialog);
        dialog
                .getWindow()
                .setLayout(
                        RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        name = dialog.findViewById(R.id.new_reminder_name);
        description = dialog.findViewById(R.id.new_reminder_desc);
        dateEnd = dialog.findViewById(R.id.new_patient_layout_dob_eT);
        cancel = dialog.findViewById(R.id.new_patient_layout_cancel_btn);
        submit = dialog.findViewById(R.id.new_patient_layout_submit_btn);

        (dateEnd)
                .setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                    pickDate();
                                }
                            }
                        });

        cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        destroy();
                    }
                });

        submit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        createNewReminder();
                    }
                });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    void pickDate() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(context);

        datePickerDialog.setOnDateSetListener(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateEnd.setText(day + "/" + month + "/" + year);
                        date = new Date(year, month, day);
                    }
                });

        datePickerDialog.show();
    }

    // COMPLETED(Danish) implement method
    void createNewReminder() {
        /*
         * 1. Create new patient object
         * 2. Initiate server
         * 3. Upload data
         */


        Reminder reminder = new Reminder();

        reminder.setName(name.getText().toString().trim());
        reminder.setDescription(description.getText().toString().trim());
        reminder.setDateAdded(dateEnd.getText().toString().trim());
        reminder.setStatus("Active");
        reminder.setId();

        final CollectionReference reminderRef =
                mRef.collection("User")
                        .document("Reminder")
                        .collection("medicine");

       reminderRef
                .document()
                .set(reminder.getId())
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    reminderRef
                                            .document(reminder.getId())
                                            .update("dateEnd", FieldValue.serverTimestamp())
                                            .addOnCompleteListener(
                                                    new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                            }
                                                        }
                                                    });

                                    Intent activityIntent = new Intent(context, MainActivity.class);
                                    PendingIntent contentIntent = PendingIntent.getActivity(
                                            context, 0, activityIntent, 0
                                    );
                                }
                            }
                        });
    }


    void toast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setAddress(String address) {
        this.description.setText(address);
    }

    public void setDate(String dob) {
        this.dateEnd.setText(dob);
    }

    public void init() {
        dialog.show();
    }

    public void destroy() {
        dialog.dismiss();
    }
}