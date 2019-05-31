package org.ligerbots.robot.commands;

import org.ligerbots.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command {
    XboxController controller;
    public JoystickDriveCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        setInterruptible(true);

        controller = Robot.oi.xbox;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double driveX, driveY;
        driveX = controller.getRawAxis(1);
        driveY = -controller.getRawAxis(4);
        Robot.drivetrain.drive(driveX, driveY);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return false; //Always want joystick control
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}  