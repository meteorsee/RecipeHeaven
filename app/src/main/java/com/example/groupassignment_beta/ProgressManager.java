package com.example.groupassignment_beta;

public class ProgressManager {
    private static ProgressManager instance;
    private int progress;

    private ProgressManager() {
        // Initialize progress to 0
        progress = 0;
    }

    public static synchronized ProgressManager getInstance() {
        if (instance == null) {
            instance = new ProgressManager();
        }
        return instance;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

}
