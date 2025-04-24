package smarthome.devices;

public interface Controller {
    void turnOn();
    void turnOff();
    void adjustSetting(String setting, int... values); // varargs(if any)
}
