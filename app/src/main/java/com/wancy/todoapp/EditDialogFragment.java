package com.wancy.todoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import static com.wancy.todoapp.R.id.txtEdit;


public class EditDialogFragment extends DialogFragment {
    private EditText mEditText;
    private Item item;

    public EditDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    // 1. Defines the listener interface with a method passing back data result.
    public interface EditDialogListener {
        void onFinishEditDialog(Item item);
    }


    public static EditDialogFragment newInstance(Item item) {
        EditDialogFragment frag = new EditDialogFragment();
        frag.item = item;
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.edit_dialog, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Get field from view
        mEditText = (EditText) view.findViewById(txtEdit);
        // Fetch arguments from bundle and set title
        String title = "Edit Item";
        getDialog().setTitle(title);
        // Show soft keyboard automatically and request focus to field
        mEditText.setText(item.getContent());
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Button btnEdit = (Button) view.findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return input text back to activity through the implemented listener
                String str = mEditText.getText().toString();
                // if input is empty, click button will do nothing
                if (str == null || str.length() == 0) return;
                item.setContent(str);
                EditDialogListener listener =  (EditDialogListener) getActivity();
                listener.onFinishEditDialog(item);
                dismiss();
            }
        });
    }

}
