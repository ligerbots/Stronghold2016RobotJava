package org.ligerbots.robot.commands;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class GearShiftCommand extends Command {
    public enum ShiftDirection {
        TOGGLE, UP, DOWN
    }

    ShiftDirection action;
    DriveTrain drivetrain;

    public GearShiftCommand(ShiftDirection direction) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        action = direction;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        drivetrain = Robot.drivetrain;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        switch (action) {
            case TOGGLE:
                drivetrain.ToggleGear();
                break;
            case UP:
                drivetrain.ShiftUp();
                break;
            case DOWN:
                drivetrain.ShiftDown();
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return true;
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