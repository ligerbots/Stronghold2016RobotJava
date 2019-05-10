package org.ligerbots.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import org.ligerbots.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {
    WPI_TalonSRX rightLeader;
    WPI_TalonSRX leftLeader;
    WPI_TalonSRX rightFollower1;
    WPI_TalonSRX leftFollower1;
    WPI_TalonSRX rightFollower2;
    WPI_TalonSRX leftFollower2;

    DoubleSolenoid shifterSolenoid;
    AHRS navx;

    double cacheX, cacheY;
    
    public static DifferentialDrive diffDrive;

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
        cacheX = 0; cacheY = 0;
        //Solenoid for gear shifting
        shifterSolenoid = new DoubleSolenoid(RobotMap.PCM_CAN, RobotMap.PCM_SHIFTER_HIGH_GEAR, RobotMap.PCM_SHIFTER_LOW_GEAR);
        //NAVX
       
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

    public double getLeftOutput() {
        return leftLeader.getMotorOutputPercent();
    }

    public double getRightOutput() {
        return rightLeader.getMotorOutputPercent();
    }

    public void drive(double x, double y) { //foward left negative
        //TODO user friendly driving - scales power up over time
        // double outputX = x > 0 ? Math.min(cacheX-0.02, x) : Math.min(cacheX+0.02, x);
        // double outputY = y > 0 ? Math.min(cacheY-0.02, y) : Math.min(cacheY+0.02, y);
        // diffDrive.arcadeDrive(outputX, outputY);

        // cacheX = outputX; cacheY = outputY;
        double safeX = Math.min(x, 0.8);
        safeX = Math.max(safeX, -0.8);
        double safeY = Math.min(y, 0.8);
        safeY = Math.max(safeY, -0.8);
        diffDrive.arcadeDrive(safeX, safeY, true);
    }

    public void SendValuesToSmartDashboard() {
        SmartDashboard.putNumber("DriveTrain/LeftOutput", getLeftOutput());
        SmartDashboard.putNumber("DriveTrain/RightOutput", getRightOutput());
    }

    @Override
    protected void initDefaultCommand() {
        
    }
}