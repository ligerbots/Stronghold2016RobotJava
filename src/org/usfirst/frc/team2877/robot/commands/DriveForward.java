package org.usfirst.frc.team2877.robot.commands;

import org.usfirst.frc.team2877.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {
	private double distanceFinal = 10.0;
	private double distanceCurrent = 0.0;
	private double speed = 0.0;
    public DriveForward(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	distanceFinal = distance;
    }
    public DriveForward(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveSubsystem);
    	distanceFinal = distance;
    	this.speed = speed;
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	distanceCurrent = 0.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveSubsystem.driveForward(speed);
    	distanceCurrent += 2.0*speed;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (distanceCurrent >= distanceFinal) {
    		return true;
    	}
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	distanceFinal = 0.0;
    	distanceCurrent = 0.0;
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveSubsystem.stop();
    }
}
