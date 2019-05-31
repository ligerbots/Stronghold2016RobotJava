package org.ligerbots.robot.commands;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.subsystems.IntakeSubsystem;
import org.ligerbots.robot.subsystems.ShooterSubsystem;
import org.ligerbots.robot.subsystems.WedgeSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class ShootCommand extends Command {
    public ShootCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    WedgeSubsystem wedge;
    IntakeSubsystem intake;
    ShooterSubsystem shooter;

    boolean done;
    int ticks;

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        wedge = Robot.wedge;
        intake = Robot.intake;
        shooter = Robot.shooter;

        done = false;
        ticks = 0;

        // if (!intake.IntakeReadyToFire())
        //     done = true;
        // else {
        //     //TODO - LEDS for shooting animation
        // }

        setInterruptible(false);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        ticks++;
        if (ticks == 1)
            shooter.Fire();
        else if (ticks == 25) {
            done = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return done;
    }
  
    // Called once after isFinished returns true
    @Override
    protected void end() {
        shooter.Retract();
    }
  
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}  