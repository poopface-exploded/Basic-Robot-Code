package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;

public class ArmSubsystem extends SubsystemBase{

    private final CANSparkMax motor1;
    private final CANSparkMax motor2;

    public ArmSubsystem() {
        this.motor1 = new CANSparkMax(PortConstants.ARM_PORT_1, MotorType.kBrushless);
        this.motor2 = new CANSparkMax(PortConstants.ARM_PORT_2, MotorType.kBrushless);
    }

    public void setSpeed1(double speed) {
        motor1.set(speed);
    }

    public void setSpeed2(double speed) {
        motor2.set(speed);
    }

    public void setSpeedAll(double speed1, double speed2) {
        setSpeed1(speed1);
        setSpeed2(speed2);
    }

    public void stopAllMotors() {
        motor1.set(0);
        motor2.set(0);
    }

}
