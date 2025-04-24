package smarthome.devices;

public class Light extends Device {
    private int brightness;

    // Overloaded constructors
    public Light(String name) {
        super(name);
        this.brightness = 50;
    }
    public Light(String name, int energyConsumption, int brightness) {
        super(name, energyConsumption);
        this.brightness = brightness;
    }

    
    @Override
    public void turnOn() { status = true; }
    
    @Override
    public void turnOff() { status = false; }
    
    @Override
    public void adjustSetting(String setting, int... values) {
        if ("brightness".equalsIgnoreCase(setting) && values.length > 0) {
            this.brightness = values[0];
        }
    }

    // Nested static class for diagnostics
    public static class Diagnostics {
        public static void runDiagnostics(Light light) {
            System.out.println("Diagnostics on " + light.getName() + ": OK");
        }
    }
}
