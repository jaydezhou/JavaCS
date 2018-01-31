package net.jayde.study.kodejava.example.fundamental;

public class DummyRemoteControl implements RemoteController {
    private int x;
    private int y;

    public DummyRemoteControl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp(int n) {
        x = x + n;
    }

    public void moveRight(int n) {
        y = y + n;
    }

    public void moveDown(int n) {
        x = x - n;
    }

    public void moveLeft(int n) {
        y = y - n;
    }

    public int[] getPosition() {
        return new int[]{x, y};
    }

    public static void main(String[] args) {
        RemoteController control = new DummyRemoteControl(0, 0);
        control.moveDown(10);
        control.moveLeft(5);
        System.out.println("X = " + control.getPosition()[0]);
        System.out.println("Y = " + control.getPosition()[1]);
    }
}
