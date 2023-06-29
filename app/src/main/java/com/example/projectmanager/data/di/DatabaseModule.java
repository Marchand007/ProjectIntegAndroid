package com.example.projectmanager.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.projectmanager.data.ProjectManagerDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    public static ProjectManagerDatabase provideDatabase(@ApplicationContext Context context) {
        return Room.databaseBuilder(context, ProjectManagerDatabase.class,"ProjectManager")
                .createFromAsset("database/ProjectManager.db")
                .build();
    }
}
