package org.ligerbots.robot.subsystems;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;
// import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class LEDSubsystem extends Subsystem {
    I2C ledI2C;
    
    public enum LEDState {
        OFF, SHOOT, RED, BLUE, AUTO
    }

    public LEDSubsystem() {
        ledI2C = new I2C(Port.kOnboard, 42);
    }

    public boolean SetLEDs(LEDState state) {
        byte[] bytes = new byte[10];

        switch (state) { //Set first byte to the state
            case OFF:
                bytes[0] = 'O';
                break;
            case SHOOT:
                bytes[0] = 'S';
                break;
            case RED:
                bytes[0] = 'R';
                break;
            case BLUE:
                bytes[0] = 'B';
                break;
            case AUTO:
                bytes[0] = 'A';
                break;
        }

        for (int i = 1; i < 10; i++) { //Repeat the state in the remaining bytes
            bytes[i] = bytes[0];
        }

        return ledI2C.writeBulk(bytes, 10);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}