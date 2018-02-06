package frc.team1816.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1816.robot.Components;
import frc.team1816.robot.subsystems.Elevator;

public class LowerElevatorCommand extends Command {
    private Elevator elevator;

    public LowerElevatorCommand() {
        super ("lowerelevatorcommand");
        this.elevator = Components.getInstance().elevator;
        requires(elevator);
    }

    public void initialize() {
        elevator.setElevatorSpeed(-.8);
        System.out.println("Lowering Elevator");
    }

    @Override
    protected boolean isFinished() {
        if(elevator.getLowerLimit()) {
            elevator.setElevatorSpeed(0);
            System.out.println("stopped");
            return true;
        } else {
            return false;
        }
    }
}
