package frc.robot.commands;

import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TestArmCommand extends CommandBase {
    
    private final ArmSubsystem arm;

    int ticks = 0;

    public TestArmCommand(ArmSubsystem arm) {
        this.arm = arm;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        System.out.println("beginning arm test command");
        ticks = 0;
    }

    @Override
    public void execute() {
        while (ticks <= 600) {
            ticks += 1;
            arm.setSpeed1(Math.sin(ticks));
            arm.setSpeed2(Math.cos(ticks));
        }

        arm.stopAllMotors();

    }    

}
