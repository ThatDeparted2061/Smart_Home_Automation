package devices;

// Concrete class for Fan device
public class Fan extends Device {
    private int speed; // 0-3 (0:off, 1:low, 2:medium, 3:high)

    public Fan(String name) {
        super(name);
        this.speed = 0;
        this.powerConsumption = 30.0;
    }

    public Fan(String name, double powerConsumption) {
        super(name, powerConsumption);
        this.speed = 0;
    }

    public void setSpeed(int speed) {
        if (speed >= 0 && speed <= 3) {
            this.speed = speed;
            this.isOn = speed > 0;
            System.out.println(name + " speed set to " + speed);
        }
    }

    // Overloaded method (varargs)
    public void setSpeed(int... speeds) {
        if (speeds.length > 0) {
            setSpeed(speeds[0]);
        }
    }

    @Override
    public void displayStatus() {
        System.out.println("Fan: " + name + " | Status: " + (isOn ? "ON" : "OFF") + 
                          " | Speed: " + speed +
                          " | Power: " + getPowerConsumption() + "W");
    }
}