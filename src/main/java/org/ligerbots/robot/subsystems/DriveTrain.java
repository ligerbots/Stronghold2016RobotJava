package org.ligerbots.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends Subsystem {
    WPI_TalonSRX rightLeader;
    WPI_TalonSRX leftLeader;
    WPI_TalonSRX rightFollower1;
    WPI_TalonSRX leftFollower1;
    WPI_TalonSRX rightFollower2;
    WPI_TalonSRX leftFollower2;

    DoubleSolenoid shifterSolenoid;
    DifferentialDrive diffDrive;

    public DriveTrain() {
        //Initialize all motors
        rightLeader = new WPI_TalonSRX(RobotMap.CT_DRIVE_RIGHT1);
        rightFollower1 = new WPI_TalonSRX(RobotMap.CT_DRIVE_RIGHT2);
        rightFollower2 = new WPI_TalonSRX(RobotMap.CT_DRIVE_RIGHT3);
        leftLeader = new WPI_TalonSRX(RobotMap.CT_DRIVE_LEFT1);
        leftFollower1 = new WPI_TalonSRX(RobotMap.CT_DRIVE_LEFT2);
        leftFollower2 = new WPI_TalonSRX(RobotMap.CT_DRIVE_LEFT3);
        //Set master/slave
        rightFollower1.follow(rightLeader);
        rightFollower2.follow(rightLeader);
        leftFollower1.follow(leftLeader);
        leftFollower2.follow(leftLeader);
        //Initialize drive method
        diffDrive = new DifferentialDrive(leftLeader, rightLeader);
        diffDrive.setSubsystem("DriveTrain");
        //Solenoid for gear shifting
        shifterSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_SHIFTER_HIGH_GEAR, RobotMap.PCM_SHIFTER_LOW_GEAR);
    }

    //Functionality methods
    public void ShiftUp() {
        shifterSolenoid.set(Value.kForward);
    }

    public void ShiftDown() {
        shifterSolenoid.set(Value.kReverse);
    }

    public Boolean IsShiftedUp() {
        return shifterSolenoid.get() == Value.kForward;
    }

    public void ToggleGear() {
        if (IsShiftedUp())
            ShiftDown();
        else
            ShiftUp();
    }

    public void drive(double x, double y) {
        //TODO user friendly driving - scales power up over time
        diffDrive.arcadeDrive(x, y);
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}