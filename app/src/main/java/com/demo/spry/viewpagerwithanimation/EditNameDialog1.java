package com.demo.spry.viewpagerwithanimation;

/**
 * Created by sprydev5 on 23/12/15.
 */
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by sprydev5 on 23/12/15.
 */
public class EditNameDialog1 extends DialogFragment {
    private Button btnSubmit;
    private EditText mEditText;

    public interface EditNameDialogListener {
        void onFinishEditDialog1(String inputText);
    }

    public EditNameDialog1() {
        // Empty constructor required for DialogFragment
    }

    public static EditNameDialog1 newInstance(String title) {
        EditNameDialog1 frag = new EditNameDialog1();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_name1, container);
        mEditText = (EditText) view.findViewById(R.id.txt_your_name);
        btnSubmit = (Button) view.findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditNameDialogListener listener = (EditNameDialogListener) getActivity();
                listener.onFinishEditDialog1(mEditText.getText().toString());
                dismiss();
            }
        });
        String title = getArguments().getString("title", "Enter Loaction");
        getDialog().setTitle(title);
        // Show soft keyboard automatically
        mEditText.requestFocus();
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return view;
    }
}


