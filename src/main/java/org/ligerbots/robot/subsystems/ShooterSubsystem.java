package org.ligerbots.robot.subsystems;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

    DoubleSolenoid shooterSolenoid;

    public ShooterSubsystem() {
        shooterSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_SHOOTER_FIRE, RobotMap.PCM_SHOOTER_RETRACT);
    }

    public void Fire() {
        shooterSolenoid.set(Value.kForward);
    }

    public void Retract() {
        shooterSolenoid.set(Value.kReverse);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}