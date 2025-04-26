package devices;

// Concrete class for Air Conditioner device
public class AC extends Device {
    private int temperature;
    private String mode; // "cool", "heat", "fan"

    public AC(String name) {
        super(name);
        this.temperature = 24;
        this.mode = "cool";
        this.powerConsumption = 100.0;
    }

    public AC(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.temperature = 24;
        this.mode = "cool";
    }

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println(name + " temperature set to " + temp + "°C");
    }

    public void setMode(String mode) {
        this.mode = mode;
        System.out.println(name + " mode set to " + mode);
    }

    @Override
    public void displayStatus() {
        System.out.println("AC: " + name + " | Status: " + (isOn ? "ON" : "OFF") + 
                          " | Temp: " + temperature + "°C" +
                          " | Mode: " + mode +
                          " | Power: " + getPowerConsumption() + "W");
    }
}