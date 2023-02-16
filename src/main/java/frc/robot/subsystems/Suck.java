package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Suck extends SubsystemBase{
    static WPI_VictorSPX conemotor = new WPI_VictorSPX(Constants.conemotorno);
    public Suck() {
    //跟class同名
        conemotor.configFactoryDefault();
        //初始化
    }
    public void suck(){
        conemotor.set(ControlMode.PercentOutput, 0.3);
    }
    public void shoot(){
        conemotor.set(ControlMode.PercentOutput, -0.3);
    }
    public void stop(){
        conemotor.set(ControlMode.PercentOutput, 0);
    } 
}
