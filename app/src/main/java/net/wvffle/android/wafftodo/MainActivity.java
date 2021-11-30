package net.wvffle.android.wafftodo;

import androidx.fragment.app.Fragment;

import net.wvffle.android.wafftodo.task.TaskFragment;
import net.wvffle.android.wafftodo.task_list.TaskListFragment;
import net.wvffle.android.wafftodo.utils.SingleFragmentActivity;

import java.util.UUID;

public class MainActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        UUID id = (UUID) getIntent().getSerializableExtra(TaskListFragment.KEY_EXTRA_TASK_ID);
        return TaskFragment.newInstance(id);
    }
}