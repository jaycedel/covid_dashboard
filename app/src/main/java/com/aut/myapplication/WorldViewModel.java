package com.aut.myapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WorldViewModel extends AndroidViewModel {

    private LiveData<WorldData> worldDataMutableLiveData;

    public WorldViewModel(@NonNull Application application){
        super(application);
        worldDataMutableLiveData = getWorldData();
    }

    public LiveData<WorldData> getWorldData() {
        return worldDataMutableLiveData;
    }
}
