package net.jayde.study.kodejava.example.fundamental;

public class RemoteControl {
    private String channelName;
    private int channelNum;
    private int minVolume;
    private int maxVolume;

    RemoteControl() {

    }

    RemoteControl(String channelName, int channelNum) {
        // 
        // use the this keyword to call another constructor in the 
        // same class
        //
        this(channelName, channelNum, 0, 0);
    }

    RemoteControl(String channelName, int channelNum, int minVol, int maxVol) {
        this.channelName = channelName;
        this.channelNum = channelNum;
        this.minVolume = minVol;
        this.maxVolume = maxVol;
    }

    public void changeVolume(int x, int y) {
        this.minVolume = x;
        this.maxVolume = y;
    }

    public static void main(String[] args) {
        RemoteControl remote = new RemoteControl("ATV", 10);

        // 
        // when the following line is executed, the this variable in
        // changeVolume() is refer to remote object.
        //
        remote.changeVolume(0, 25);
    }
}
