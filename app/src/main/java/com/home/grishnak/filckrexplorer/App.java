package com.home.grishnak.filckrexplorer;

import com.home.grishnak.filckrexplorer.Modules.ApplicationModule;

import dagger.ObjectGraph;

public class App extends android.app.Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new ApplicationModule(this));
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}
