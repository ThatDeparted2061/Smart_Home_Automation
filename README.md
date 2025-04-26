# Smart Home Automation System - Features

## 🏠 Room-Based Device Control

### Living Room
- 💡 Smart Light (On/Off + Brightness 0-100%)
- ❄️ Air Conditioner (Temp Control + Cool/Heat/Fan modes)

### Bedroom
- 💡 Smart Light (On/Off + Brightness 0-100%)
- 🌀 Ceiling Fan (On/Off + 3 Speed Levels)
- ❄️ Air Conditioner (Temp Control + Cool/Heat/Fan modes)

### Kitchen
- 💡 Smart Light (On/Off + Brightness 0-100%)
- 🍳 Exhaust Fan (On/Off + 3 Speed Levels)

### Whole Home Security
- 🚨 Alarm System (Arm/Disarm)
- 👀 Motion Detection
- 🤖 Automated Lighting Responses

## 👥 User Management

### Authentication
- 🔒 SHA-256 Password Hashing
- 📁 Secure Credential Storage
- Default Accounts:
  - Admin: `admin/admin123`
  - User: `user/user123`

### User Types
| Role | Capabilities | Special Access |
|------|-------------|----------------|
| Admin | All device controls | 👥 User Management ⚡ Energy Monitoring |
| Regular User | All device controls | You Ain't him Brdr |

### User Operations
- ➕ Add new users
- ➖ Remove users
- 📜 List all users
- 🔄 Change passwords
- ⚡ Toggle admin privileges

## 🤖 Automation Features

### Motion Detection
- Automatically triggers:
  - Living room lights 💡 (100% brightness)
  - Kitchen lights 💡 (100% brightness)
  - Alarm activation 🚨 (if armed)

### Energy Monitoring (Admin Only)
- 🔌 Real-time power tracking
- 📊 Per-device consumption
- ∑ Total home usage

## 🖥️ System Interface

### Menu Navigation
```mermaid
flowchart TD
    A[Main Menu] --> B[Living Room]
    A --> C[Bedroom]
    A --> D[Kitchen]
    A --> E[Security]
    A --> F[User Management]
    
    B --> B1[Light]
    B --> B2[AC]
    C --> C1[Light]
    C --> C2[Fan]
    C --> C3[AC]
    D --> D1[Light]
    D --> D2[Exhaust Fan]
    E --> E1[Arm]
    E --> E2[Alarm]
    E --> E3[Motion]
    F --> F1[Add User]
    F --> F2[Remove]
    F --> F3[List]
```
## Technical Architecture
```mermaid
classDiagram
    class Device{
        <<abstract>>
        +String name
        +boolean isOn
        +double powerConsumption
        +turnOn()
        +turnOff()
        +displayStatus()
    }
    
    class Light{
        +int brightness
        +setBrightness()
    }
    
    class Fan{
        +int speed
        +setSpeed()
    }
    
    class AC{
        +int temperature
        +String mode
        +setTemperature()
        +setMode()
    }
    
    class SecuritySystem{
        +boolean motionDetected
        +boolean alarmOn
        +detectMotion()
        +setAlarm()
    }
    
    class User{
        <<abstract>>
        +String username
        +controlDevice()
    }
    
    class RegularUser{
        +controlDevice()
    }
    
    class Admin{
        +checkEnergyConsumption()
        +userManagement()
    }
    
    Device <|-- Light
    Device <|-- Fan
    Device <|-- AC
    Device <|-- SecuritySystem
    User <|-- RegularUser
    User <|-- Admin
```
