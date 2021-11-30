package net.wvffle.android.wafftodo.task_list;

import androidx.fragment.app.Fragment;

import net.wvffle.android.wafftodo.utils.SingleFragmentActivity;

public class TaskListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new TaskListFragment();
    }
}