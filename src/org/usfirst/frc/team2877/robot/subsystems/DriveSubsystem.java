package org.usfirst.frc.team2877.robot.subsystems;

import org.usfirst.frc.team2877.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	WPI_TalonSRX leftLeader;
	WPI_TalonSRX leftFollower;
	WPI_TalonSRX rightLeader;
	WPI_TalonSRX rightFollower;
	//The rest of the talon declarations

	DifferentialDrive robotDrive;
	PIDController turningController;
	
	int[] talons;
	
	
	private class Position {
		double X, Y;
		double Angle;
	}
	
	Position m_pos;
	double m_originalPosition;
	public DriveSubsystem() {
	//initialization
		leftLeader = new WPI_TalonSRX(RobotMap.CT_ID_LEFT_LEADER);
		leftFollower = new WPI_TalonSRX(RobotMap.CT_ID_LEFT_FOLLOWER);
		rightLeader = new WPI_TalonSRX(RobotMap.CT_ID_RIGHT_LEADER);
		rightFollower = new WPI_TalonSRX(RobotMap.CT_ID_RIGHT_FOLLOWER);
		leftLeader.set(ControlMode.PercentOutput, 0.0);
		leftFollower.set(ControlMode.Follower, RobotMap.CT_ID_LEFT_LEADER);
		rightLeader.set(ControlMode.PercentOutput, 0.0);
		rightFollower.set(ControlMode.Follower, RobotMap.CT_ID_RIGHT_LEADER);
		
		robotDrive = new DifferentialDrive(leftLeader, rightLeader);
	}
	
	public void driveForward() {
		robotDrive.arcadeDrive(0.5, 0);
	}
	public void driveForward(double speed) {
		robotDrive.arcadeDrive(speed, 0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void stop() {
		// TODO Auto-generated method stub
		robotDrive.arcadeDrive(0.0, 0);
	}
    
}

