package net.wvffle.android.wafftodo.task;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import net.wvffle.android.wafftodo.R;
import net.wvffle.android.wafftodo.task_list.TaskListFragment;

import java.util.UUID;

public class TaskFragment extends Fragment {
    private Task task = new Task();
    private static final String ARG_TASK_ID = "waff-task-id";

    public TaskFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        assert getArguments() != null;
        UUID id = (UUID) getArguments().getSerializable(ARG_TASK_ID);

        Task task = TaskStorage.getInstance().getTask(id);
        assert task != null;
        this.task = task;
    }

    public static TaskFragment newInstance (UUID id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_TASK_ID, id);

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task, container, false);

        EditText name = view.findViewById(R.id.task_name);
        Button date = view.findViewById(R.id.task_date);
        CheckBox checkBox = view.findViewById(R.id.task_done);

        name.setText(task.getName());
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence name, int start, int before, int count) {
                task.setName(name.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        date.setText(task.getDate().toString());
        date.setEnabled(false);

        checkBox.setChecked(task.isDone());
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setDone(isChecked);
        });

        return view;
    }
}