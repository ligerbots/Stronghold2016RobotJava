package org.ligerbots.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
    DoubleSolenoid intakeArmSolenoid;
    WPI_TalonSRX rollers;

    DigitalInput inShooterSwitch;
    DigitalInput inDefenseSwitch;
    DigitalInput intakeUpSwitch;
    AnalogInput ballPositionSensor;

    public enum RollerAction {
        OUT, IN, STOP
    }

    public IntakeSubsystem() {
        setSubsystem("Intake");

        ballPositionSensor = new AnalogInput(RobotMap.AI_INTAKE_DISTANCE_SENSOR); //Can remove?
       
        intakeArmSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_INTAKE_DOWN, RobotMap.PCM_INTAKE_UP);
        
        inShooterSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE_BALL_IN_SHOOTER);
        
        inDefenseSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE_BALL_DEFENSES_POSITION);
        
        intakeUpSwitch = new DigitalInput(RobotMap.LIMIT_SWITCH_INTAKE_UP);
        
        rollers = new WPI_TalonSRX(RobotMap.CT_INTAKE_ROLLER);
    }

    public void Roll(RollerAction action) {
        switch (action) {
            case IN:
                rollers.set(ControlMode.PercentOutput, RobotMap.ROLLER_SPEED);
                break;

            case OUT:
                rollers.set(ControlMode.PercentOutput, -RobotMap.ROLLER_SPEED);
                break;

            case STOP:
                rollers.set(ControlMode.PercentOutput, 0);
                break;
        }
    }

    public void RollPercent(double percentage) {
        rollers.set(ControlMode.PercentOutput, percentage);
    }
    // Intake actions
    public void SetIntakeArmUp() {
        if (intakeArmSolenoid.get() == Value.kReverse) return; //If not already up
        intakeArmSolenoid.set(Value.kReverse);
    }

    public void SetIntakeArmDown() {
        if (intakeArmSolenoid.get() == Value.kForward) return; //If not already up
        intakeArmSolenoid.set(Value.kForward);
    }

    public DoubleSolenoid.Value GetIntakeArmValue() {
        return intakeArmSolenoid.get();
    }

    public void ToggleIntake() {
        if (GetIntakeArmValue() == Value.kForward)
            SetIntakeArmUp();
        else
            SetIntakeArmDown();
    }
    // Switches
    public boolean ShooterSwitchPressed() {
        return inShooterSwitch.get();
    }

    public boolean InShooterPosition() {
        // the switch may not be pressed if the ball is too high up
        // if the distance sensor value is greater than 1.55, the ball is at the distance of
        // the shooting position or closer, so assume the ball is at the shooting position
        double distanceSensorValue = ballPositionSensor.getVoltage();
        return (distanceSensorValue > 1.5) || inShooterSwitch.get();
    }

    public boolean InDefencesCrossingPosition() {
        return inDefenseSwitch.get();
    }

    public boolean IntakeReadyToFire() {
        return (GetIntakeArmValue() == Value.kReverse) && InShooterPosition();
    }


    @Override
    protected void initDefaultCommand() {
        
    }
}