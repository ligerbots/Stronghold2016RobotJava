package org.ligerbots.robot.commands;

import org.ligerbots.robot.Robot;
import org.ligerbots.robot.subsystems.IntakeSubsystem;
import org.ligerbots.robot.subsystems.IntakeSubsystem.RollerAction;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class IntakeShootSequence extends Command {
    IntakeSubsystem intake;
    BallState currentBallState;
    double untilNextState;

    XboxController xbox;
    
    static enum BallState {
        LATCHED, POSITION, SHOOTINGPOSITION, DONE
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
        xbox = Robot.oi.xbox;
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

                if (untilNextState > 40) {
                    currentBallState = BallState.SHOOTINGPOSITION;
                    // intake.SetIntakeArmDown();
                    intake.Roll(RollerAction.STOP);
                }
                else if (intake.InShooterPosition())
                    untilNextState = 0;
                break;
            case SHOOTINGPOSITION:
                break;
            case DONE:
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
      return currentBallState == BallState.SHOOTINGPOSITION || (untilNextState > 200);
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