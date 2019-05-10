package org.ligerbots.robot.Commands;

import org.ligerbots.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickDriveCommand extends Command {
    XboxController controller;
    double cacheX;
    double cacheY;
    double lastX;
    double lastY;
    double time;

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
        cacheX = 0;
        cacheY = 0;
        time = 0.2;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double driveX, driveY;
        driveX = controller.getRawAxis(1) * time;
        driveY = -controller.getRawAxis(4) * time;
        if ((Math.abs(controller.getRawAxis(1)) < 0.1) && (Math.abs(controller.getRawAxis(4)) < 0.2)) {
            time = 0.2;
        }
        else {
            time = Math.min(time+0.01, 0.8);
        }
        //Robot.drivetrain.drive(controller.getRawAxis(1), -controller.getRawAxis(4));
        
        System.out.println(time);
        Robot.drivetrain.drive(driveX, driveY);
        lastX = controller.getRawAxis(1);
        lastY = -controller.getRawAxis(4);
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