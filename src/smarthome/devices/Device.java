package smarthome.devices;

public abstract class Device implements Controller {
    protected String name;
    protected boolean status;
    protected int energyConsumption;

    // Overloaded constructors
    public Device(String name) {
        this.name = name;
        this.status = false;
        this.energyConsumption = 0;
    }
    public Device(String name, int energyConsumption) {
        this.name = name;
        this.status = false;
        this.energyConsumption = energyConsumption;
    }

    // Overloaded methods
    public void displayStatus() {
        System.out.println(name + " is " + (status ? "ON" : "OFF"));
    }
    public void displayStatus(boolean showEnergy) {
        System.out.println(name + " is " + (status ? "ON" : "OFF") +
                (showEnergy ? (", Energy: " + energyConsumption + "W") : ""));
    }

    public String getName() { return name; }
    public boolean isOn() { return status; }
    public int getEnergyConsumption() { return energyConsumption; }
}
