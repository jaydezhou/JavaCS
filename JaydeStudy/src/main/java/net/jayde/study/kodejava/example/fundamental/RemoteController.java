package net.jayde.study.kodejava.example.fundamental;

public interface RemoteController {
    void moveUp(int n);

    void moveRight(int n);

    void moveDown(int n);

    void moveLeft(int n);

    int[] getPosition();
}
