package frc.robot;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.frc.wpilibj.*;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class SwerveModule {

    private CANSparkMax turn;
    private CANSparkMax drive;
    private AnalogPotentiometer encoder;
    private CANPIDController cont;
    private final double conversion;
    public Rotation2d currentAngle;
    private final double MAX_SPEED;

    public SwerveModule(int turn, int drive, int encoder){
        this.turn = new CANSparkMax(turn, MotorType.kBrushless);
        this.drive = new CANSparkMax(turn, MotorType.kBrushless);
        this.cont = this.turn.getPIDController();
        this.encoder = new AnalogPotentiometer(0,360,90);
        MAX_SPEED = 3;
        cont.setOutputRange(-1, 1);
        cont.setP(0.05);
        this.currentAngle = new Rotation2d(Math.PI / 2 * -1);
        this.conversion = (Math.PI * 2) / 5.28;
    }

    public void setCurrentAngle(double encPosition){
        currentAngle = getConvertedAngle(encoderPosition);
    }

    public double getConvertedAngle(double encoderPosition){
        double tempAngle = 0;
        if (encoder.get() <= 180){
            tempAngle = Math.toRadians(encoder.get() * -1);
        }
        else{
            tempAngle = Math.toRadians(180 - (encoder.get() - 180));
        } 
        return tempAngle;
    }

    public double passToPid(){
        return getConvertedAngle(encoder.get());
    }

    public void setModule(Rotation2d angle1, double speed){
        turn.set(cont.calculate(this.passToPid(), angle1.Angle));
        setCurrentAngle(encoder.get());
    }

    public void reset(){
        
    }
}
