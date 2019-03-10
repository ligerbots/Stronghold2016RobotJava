package org.ligerbots.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

import org.ligerbots.robot.RobotMap;

public class CompressorSubsystem extends Subsystem {
    AnalogInput pressureSensor;
    Compressor compressor;

    public CompressorSubsystem() {
        setSubsystem("Compressor");
        //Init systems
        pressureSensor = new AnalogInput(RobotMap.AI_PRESSURE_SENSOR);
        compressor = new Compressor(RobotMap.PCM_CAN);
    }

    public double GetPSI() {
        double currentReading = pressureSensor.getVoltage();
        // formula from datasheet:
        // http://www.revrobotics.com/wp-content/uploads/2015/11/REV-11-1107-DS-00.pdf
        // Vcc assumed to be 5V
        double psi = 250 * (currentReading/5) - 25;
        return psi;
    }

    public void SetCompressor(boolean on) {
        compressor.setClosedLoopControl(on);
    }

    public boolean CompressorOn() {
        return compressor.getClosedLoopControl();
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}