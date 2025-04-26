package devices;

// Concrete class for Security System
public class SecuritySystem extends Device {
    private boolean motionDetected;
    private boolean alarmOn;

    // Nested interface
    public interface SecurityAlert {
        void triggerAlarm();
    }

    // Nested class implementing the interface
    public static class Alarm implements SecurityAlert {
        @Override
        public void triggerAlarm() {
            System.out.println("ALERT! ALERT! Intruder detected!");
        }
    }

    public SecuritySystem(String name) {
        super(name);
        this.motionDetected = false;
        this.alarmOn = false;
        this.powerConsumption = 20.0;
    }

    public void detectMotion() {
        this.motionDetected = true;
        if (isOn) {
            Alarm alarm = new Alarm();
            alarm.triggerAlarm();
        }
    }

    public void resetMotion() {
        this.motionDetected = false;
    }

    public void setAlarm(boolean status) {
        this.alarmOn = status;
        System.out.println(name + " alarm " + (status ? "activated" : "deactivated"));
    }

    @Override
    public void displayStatus() {
        System.out.println("Security: " + name + " | Status: " + (isOn ? "ON" : "OFF") + 
                          " | Motion: " + (motionDetected ? "Detected" : "None") +
                          " | Alarm: " + (alarmOn ? "ON" : "OFF") +
                          " | Power: " + getPowerConsumption() + "W");
    }
}