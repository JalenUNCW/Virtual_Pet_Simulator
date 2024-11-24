package com.example.vp_simulator;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class AppState {
    private static final BooleanProperty hardcoreMode = new SimpleBooleanProperty(false);

    public static boolean isHardcoreMode() {
        return hardcoreMode.get();
    }

    public static void setHardcoreMode(boolean isHardcore) {
        hardcoreMode.set(isHardcore);
    }

    public static BooleanProperty hardcoreModeProperty() {
        return hardcoreMode;
    }

    private void handleToggleHardcoreModeAction() {
        AppState.setHardcoreMode(!AppState.isHardcoreMode());
    }

}

