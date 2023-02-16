package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class Arm extends SubsystemBase{
    static WPI_VictorSPX cubemotor = new WPI_VictorSPX(Constants.cubemotorno);
    public Arm() {
    //跟class同名
        cubemotor.configFactoryDefault();
        //初始化
    }
    public void a_up(){
        cubemotor.set(ControlMode.PercentOutput, 0.4);
    }
    public void a_down(){
        cubemotor.set(ControlMode.PercentOutput, -0.4);
    }
    public void a_stop(){
        cubemotor.set(ControlMode.PercentOutput, 0);
    }
    public Object armout() {
        return null;
    } 
}
