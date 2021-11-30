package net.wvffle.android.wafftodo.task_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.wvffle.android.wafftodo.MainActivity;
import net.wvffle.android.wafftodo.R;
import net.wvffle.android.wafftodo.task.Task;
import net.wvffle.android.wafftodo.task.TaskStorage;

import java.util.List;

public class TaskListFragment extends Fragment {

    public static final String KEY_EXTRA_TASK_ID = "waff-task-id";
    private RecyclerView recyclerView;

    public TaskListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task_list, container, false);

        recyclerView = view.findViewById(R.id.task_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateView();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateView();
    }

    private void updateView () {
        TaskStorage storage = TaskStorage.getInstance();
        List<Task> tasks = storage.getTasks();

        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            recyclerView.setAdapter(new TaskAdapter(tasks));
            return;
        }

        adapter.notifyDataSetChanged();
    }

    private class TaskHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView date;
        private Task task = null;

        public TaskHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.task_list_item, parent, false));
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra(KEY_EXTRA_TASK_ID, task.getId());
                startActivity(intent);
            });

            name = itemView.findViewById(R.id.task_name);
            date = itemView.findViewById(R.id.task_date);
        }

        void bind (Task task) {
            this.task = task;
            name.setText(task.getName());
            date.setText(task.getDate().toString());
        }
    }

    private class TaskAdapter extends RecyclerView.Adapter<TaskHolder> {
        private final List<Task> taskList;

        public TaskAdapter (List<Task> tasks) {
            taskList = tasks;
        }

        @NonNull
        @Override
        public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new TaskHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
            holder.bind(taskList.get(position));
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }
    }
}