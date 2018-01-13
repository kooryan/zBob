package frc.team1816.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team1816.robot.Components;
import frc.team1816.robot.subsystems.Drivetrain;

public class DriveXInchesCommand extends Command {

    private Drivetrain drivetrain;
    double inches;
    double speed;
    double ticks;
    double tempInches;

    public DriveXInchesCommand(double inches, double speed) {
        super("drivexinchescommand");
        this.inches = inches;
        this.speed = speed;
        drivetrain = Components.getInstance().drivetrain;
        ticks = (int) (inches * Drivetrain.TICKS_PER_INCH);
        //drivetrain.getRightMain().setSelectedSensorPosition(0,0,10);
        //drivetrain.getLeftMain().setSelectedSensorPosition(0,0,10);
    }

    @Override
    protected void initialize() {
        System.out.println("Init");
        drivetrain.resetEncoders();
    }

    @Override
    protected void execute() {
        double velocity;
        double currentPosition = drivetrain.talonPositionRight();
        double currentInches = Drivetrain.INCHES_PER_REV * currentPosition;

        tempInches = inches - currentInches;
        System.out.println("Temp inches: " + tempInches);
        System.out.println("Current inches: " + currentInches);
        System.out.println("Current Position: " + currentPosition +"\n");

        velocity = speed;
        drivetrain.setDrivetrain(velocity, velocity);
    }

    @Override
    protected void end() {
        drivetrain.setDrivetrain(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected boolean isFinished() {
        if (tempInches <= 0) {
            System.out.println("Finished");
            return true;
        } else {
            return false;
        }
    }
}
