package devices;

// Concrete class for Light device
public class Light extends Device {
    private int brightness; // 0-100%

    public Light(String name) {
        super(name);
        this.brightness = 50;
        this.powerConsumption = 10.0; // Default power consumption
    }

    public Light(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.brightness = 50;
    }

    // Overloaded method 1
    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
            System.out.println(name + " brightness set to " + brightness + "%");
        }
    }

    // Overloaded method 2 (varargs)
    public void setBrightness(int... levels) {
        if (levels.length > 0) {
            setBrightness(levels[0]);
        }
    }

    @Override
    public void displayStatus() {
        System.out.println("Light: " + name + " | Status: " + (isOn ? "ON" : "OFF") + 
                          " | Brightness: " + brightness + "%" +
                          " | Power: " + getPowerConsumption() + "W");
    }
}