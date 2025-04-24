package smarthome.devices;

public class Fan extends Device {
    private int speed;

    public Fan(String name) {
        super(name);
        this.speed = 1;
    }
    public Fan(String name, int energyConsumption, int speed) {
        super(name, energyConsumption);
        this.speed = speed;
    }

    @Override
    public void turnOn() { status = true; }
    @Override
    public void turnOff() { status = false; }
    @Override
    public void adjustSetting(String setting, int... values) {
        if ("speed".equalsIgnoreCase(setting) && values.length > 0) {
            this.speed = values[0];
        }
    }
}
