package br.com.todo;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import br.com.todo.database.AppDatabase;
import br.com.todo.database.TaskEntry;

public class AddTaskViewModel extends ViewModel {

    private LiveData<TaskEntry> task;

    public AddTaskViewModel(AppDatabase mDatabase, int mTaskId) {
        task = mDatabase.taskDao().findById(mTaskId);
    }

    public LiveData<TaskEntry> getTask() {
        return task;
    }
}
