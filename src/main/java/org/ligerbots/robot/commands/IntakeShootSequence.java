package org.ligerbots.robot.Commands;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.Subsystems.IntakeSubsystem;
import org.ligerbots.robot.Subsystems.IntakeSubsystem.RollerAction;

import edu.wpi.first.wpilibj.command.Command;

public class IntakeShootSequence extends Command {
    IntakeSubsystem intake;
    BallState currentBallState;
    double untilNextState;
    
    static enum BallState {
        LATCHED, POSITION, SHOOTINGPOSITION
    }

    public IntakeShootSequence() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        intake = Robot.intake;
        currentBallState = BallState.LATCHED;
        untilNextState = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        untilNextState++;
        switch (currentBallState) {
            case LATCHED:
                if (untilNextState > 40)
                    intake.SetIntakeArmDown();
                intake.Roll(RollerAction.IN);
                if (intake.InDefencesCrossingPosition()) {
                    System.out.println("Switching");
                    currentBallState = BallState.POSITION;
                    untilNextState = 0;
                    intake.SetIntakeArmUp();
                }
                break;
            case POSITION:
                intake.Roll(RollerAction.IN);
                System.out.println("In position.");
                if (intake.InShooterPosition()) {
                    currentBallState = BallState.SHOOTINGPOSITION;
                    // intake.SetIntakeArmDown();
                    intake.Roll(RollerAction.STOP);
                }
                break;
            case SHOOTINGPOSITION:
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return currentBallState == currentBallState.SHOOTINGPOSITION;
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