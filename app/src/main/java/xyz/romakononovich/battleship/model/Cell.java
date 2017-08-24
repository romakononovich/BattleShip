package xyz.romakononovich.battleship.model;

/**
 * Created by romank on 15.08.17.
 */

public class Cell {
    private boolean isEmpty;
    private boolean isDestroyed;

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }
}
