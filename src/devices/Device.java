package devices;

// Abstract class representing a generic home device
public abstract class Device {
    protected String name;
    protected boolean isOn;
    protected double powerConsumption;

    // Constructor 1
    public Device(String name) {
        this.name = name;
        this.isOn = false;
        this.powerConsumption = 0.0;
    }

    // Constructor 2 (overloaded)
    public Device(String name, double powerConsumption) {
        this.name = name;
        this.isOn = false;
        this.powerConsumption = powerConsumption;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(name + " turned ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(name + " turned OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public String getName() {
        return name;
    }

    public double getPowerConsumption() {
        return isOn ? powerConsumption : 0.0;
    }

    // Abstract method to be implemented by subclasses
    public abstract void displayStatus();
}