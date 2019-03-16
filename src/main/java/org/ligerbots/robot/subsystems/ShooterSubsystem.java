package org.ligerbots.robot.Subsystems;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.RobotMap;
import org.ligerbots.robot.Subsystems.LEDSubsystem.LEDState;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

    DoubleSolenoid shooterSolenoid;
    LEDSubsystem leds;

    public ShooterSubsystem() {
        leds = Robot.leds;
        shooterSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_SHOOTER_FIRE, RobotMap.PCM_SHOOTER_RETRACT);
    }

    public void Fire() {
        leds.SetLEDs(LEDState.SHOOT);
        shooterSolenoid.set(Value.kForward);
    }

    public void Retract() {
        shooterSolenoid.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}