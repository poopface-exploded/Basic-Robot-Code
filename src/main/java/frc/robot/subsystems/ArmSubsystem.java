package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PortConstants;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADIS16470_IMU;

public class ArmSubsystem extends SubsystemBase{

    //define motors, encoders, switches, etc.
    private final CANSparkMax motor1;
    private final CANSparkMax motor2;

    private final RelativeEncoder motor1Encoder;
    private final RelativeEncoder motor2Encoder;

    private final DigitalInput motor1LimitSwitch = new DigitalInput(PortConstants.LIMIT_SWITCH_PORT_1);
    private final DigitalInput motor2LimitSwitch = new DigitalInput(PortConstants.LIMIT_SWITCH_PORT_2);

    private static final ADIS16470_IMU gyro = new ADIS16470_IMU();

    private static final NetworkTableInstance INSTANCE = NetworkTableInstance.getDefault();

    public ArmSubsystem() {
        this.motor1 = new CANSparkMax(PortConstants.ARM_PORT_1, MotorType.kBrushless);
        this.motor2 = new CANSparkMax(PortConstants.ARM_PORT_2, MotorType.kBrushless);

        this.motor1Encoder = this.motor1.getEncoder();
        this.motor2Encoder = this.motor2.getEncoder();
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

    public void printEncoderValues() {
        System.out.println(motor1Encoder);
        System.out.println(motor2Encoder);
    }

    public void printTable1Entry2() {
        //print something when entry @ table 1 and 2 are true
        if (INSTANCE.getTable("table1").getEntry("entry2").getBoolean(true)) {
            System.out.println("halp what was i supposed to print out (i thinbk i may have done something wrong)");
        } 
    }

    public void resetIfGyroBetween() {
        //reset motors to default when gyro is within a degree of 70 degrees
        if (gyro.getAngle() > 69.0 || gyro.getAngle() < 70.0) {
            motor1Encoder.setPosition(ArmConstants.motor1DefaultPosition);
            motor2Encoder.setPosition(ArmConstants.motor2DefaultPosition);
        }
    }

    @Override
    public void periodic() {

        // reset motors to default values when limit switch(es) are pressed
        if (motor1LimitSwitch.get() == true) {
            motor1Encoder.setPosition(ArmConstants.motor1DefaultPosition);
        }

        if (motor2LimitSwitch.get() == true) {
            motor2Encoder.setPosition(ArmConstants.motor2DefaultPosition);
        }

    }

    @Override
    public void simulationPeriodic() {
        
    }

}
