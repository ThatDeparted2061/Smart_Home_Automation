## Requirement Implementation Rubrics

| #  | Requirement Category       | Implementation Status | Evidence Location | Key Components |
|----|----------------------------|-----------------------|-------------------|----------------|
| I  | Overloaded methods         | ✅ 2+ Implemented     | `Light.java`, `Fan.java` | `setBrightness()`, `setSpeed()` with different parameters |
| II | Overloaded constructors    | ✅ 2+ Implemented     | `Device.java`, `User.java` | Multiple constructors in base classes |
| III| Vararg overloading         | ✅ 2 Implemented      | `Light.java`, `Fan.java` | `setBrightness(int...)`, `setSpeed(int...)` |
| IV | Nested classes             | ✅ 1 Implemented      | `SecuritySystem.java` | `Alarm` nested class |
| V  | Abstract class             | ✅ 1 Implemented      | `Device.java` | `abstract class Device` |
| VI | Interface                  | ✅ 1 Implemented      | `SecurityAlert.java` | `interface SecurityAlert` |
| VII| Hierarchical Inheritance   | ✅ 1 Implemented      | `User.java` hierarchy | `User` → `Admin`/`RegularUser` |
| VIII| Multiple Inheritance      | ✅ 1 Implemented      | `SecuritySystem.java` | `Alarm` implements interface while nested |
| IX | Wrappers                   | ✅ Used               | `Main.java` | `Integer.parseInt()` for menu inputs |
| X  | Package                    | ✅ Implemented        | All files | `devices/`, `users/`, `utils/` packages |
| XI | Exception handling         | ✅ 2+ Cases           | `Authentication.java`, `Main.java` | File I/O, NumberFormatException |
| XII| I/O Operations             | ✅ File + Scanner     | `Authentication.java` | File handling + `Scanner` input |
| XIII| Multithreading           | ✅ Implemented        | `SecuritySystem.java` | Alarm triggers as separate event |
