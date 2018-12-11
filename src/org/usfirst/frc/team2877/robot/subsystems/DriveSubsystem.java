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
	WPI_TalonSRX leftFollower1;
	WPI_TalonSRX leftFollower2;
	WPI_TalonSRX rightLeader;
	WPI_TalonSRX rightFollower1;
	WPI_TalonSRX rightFollower2;
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
		leftFollower1 = new WPI_TalonSRX(RobotMap.CT_ID_LEFT_FOLLOWER1);
		leftFollower2 = new WPI_TalonSRX(RobotMap.CT_ID_LEFT_FOLLOWER2);
		rightLeader = new WPI_TalonSRX(RobotMap.CT_ID_RIGHT_LEADER);
		rightFollower1 = new WPI_TalonSRX(RobotMap.CT_ID_RIGHT_FOLLOWER1);
		rightFollower2 = new WPI_TalonSRX(RobotMap.CT_ID_RIGHT_FOLLOWER2);
		leftLeader.set(ControlMode.PercentOutput, 0.0);
		leftFollower1.set(ControlMode.Follower, RobotMap.CT_ID_LEFT_LEADER);
		leftFollower2.set(ControlMode.Follower, RobotMap.CT_ID_LEFT_LEADER);
		rightLeader.set(ControlMode.PercentOutput, 0.0);
		rightFollower1.set(ControlMode.Follower, RobotMap.CT_ID_RIGHT_LEADER);
		rightFollower2.set(ControlMode.Follower, RobotMap.CT_ID_RIGHT_LEADER);
		robotDrive = new DifferentialDrive(leftLeader, rightLeader);
	}
	
	public void SetInitialPosition(double x, double y) {
		m_pos.X = x;
		m_pos.Y = y;
	}

	public void updatePosition() {
		double distance = (getRightEncoderPosition() / TICKS_PER_INCH) 
		- (m_originalDistance + m_prevDistance);
		m_prevDistance += distance;
		m_pos.Angle = navXSubsystem.GetYaw();
		double xMoved = Math.cos(m_pos.Angle) * distance;
		double yMoved = Math.sqrt(distance * distance - xMoved * xMoved);
		m_pos.X += xMoved;
		m_pos.Y += yMoved;
		if (Robot.ticks % 300 == 0)
		// printl("POSITION: X=%5.2f Y=%5.2f Angle=%5.2f\n", m_pos.X, m_pos.Y, m_pos.Angle);
	}

	public void drive(double x, double y) {
		robotDrive.arcadeDrive(x, y);
		updatePosition();
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

