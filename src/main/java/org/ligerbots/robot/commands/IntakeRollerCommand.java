package org.ligerbots.robot.commands;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeRollerCommand extends Command {
    XboxController controller;
    IntakeSubsystem intake;

    public IntakeRollerCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.intake);
        setInterruptible(true);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        controller = Robot.oi.xbox;
        intake = Robot.intake;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double speed = controller.getRawAxis(3) - controller.getRawAxis(2);

        intake.SetRollSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return false;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
        intake.SetRollSpeed(0);
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        intake.SetRollSpeed(0);
    }
}  