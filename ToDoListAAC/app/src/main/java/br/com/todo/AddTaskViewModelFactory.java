package br.com.todo;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.NewInstanceFactory;
import android.support.annotation.NonNull;

import br.com.todo.database.AppDatabase;

public class AddTaskViewModelFactory extends NewInstanceFactory {

    private final AppDatabase mDatabase;
    private final int mTaskId;

    public AddTaskViewModelFactory(AppDatabase mDatabase, int mTaskId) {
        this.mDatabase = mDatabase;
        this.mTaskId = mTaskId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddTaskViewModel(mDatabase, mTaskId);
    }
}
