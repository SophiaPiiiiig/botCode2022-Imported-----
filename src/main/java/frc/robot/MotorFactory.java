/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * Config motor, using "Fluent Interface".
 * Must using all method before using configLikePrevious()
 */
public class MotorFactory {
    /**
     * Restoring motor config.
     */
    private static class MotorConfig {
        public static FeedbackDevice sensor;
        public static int sensorPosition;
        public static int pidSlot;
        public static int timeoutMs;
    }
    /**
     * Initializing motor
     * @param motor
     * @return motor
     */
    public static TalonFX init(final TalonFX motor) {
        motor.configFactoryDefault();
        return motor;
    }
    /**
     * Initializing motor
     * @param Victor_motor
     * @return motor
     */
    public static WPI_VictorSPX init(final WPI_VictorSPX Victor_motor) {
        Victor_motor.configFactoryDefault();
        return Victor_motor;
    }

    /**
     * Set follower.And initializing motor.
     * 
     * @param master
     * @param follower
     * @return master
     */
    public static TalonFX setFollower(final TalonFX master, final TalonFX follower) {
        MotorFactory.init(master);
        MotorFactory.init(follower);

        follower.follow(master);
        return master;
    }

    /**
     * Set follower.And initializing motor.
     * 
     * @param Victor_master
     * @param Victor_follower
     * @return master
     */
    public static WPI_VictorSPX setFollower(final WPI_VictorSPX Victor_master, final WPI_VictorSPX Victor_follower) {
        MotorFactory.init(Victor_master);
        MotorFactory.init(Victor_follower);

        Victor_follower.follow(Victor_master);
        return Victor_master;
    }

    /**
     * Set sensor type
     * 
     * @param motor
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static TalonFX setSensor(final TalonFX motor, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        motor.configSelectedFeedbackSensor(sensorType);
        return motor;
    }

    /**
     * Set sensor type
     * 
     * @param Victor_master
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static WPI_VictorSPX setSensor(final WPI_VictorSPX Victor_master, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        Victor_master.configSelectedFeedbackSensor(sensorType);
        return Victor_master;
    }

    /**
     * Set sensor type
     * 
     * @param TalonSRX_master
     * @param sensorType
     * @return motor {@link com.ctre.phoenix.motorcontrol.FeedbackDevice}
     */
    public static TalonSRX setSensor(final TalonSRX TalonSRX_master, final FeedbackDevice sensorType) {
        MotorConfig.sensor = sensorType;
        TalonSRX_master.configSelectedFeedbackSensor(sensorType);
        return  TalonSRX_master;
    }

    /**
     * Set sensor position.
     * 
     * @param leftMas
     * @param sensorPosition
     * @param pidSlot
     * @param timeoutMs
     * @return motor
     */
    public static WPI_VictorSPX setPosion(final WPI_VictorSPX leftMas, final int sensorPosition, final int pidSlot,
            final int timeoutMs) {
        MotorConfig.sensorPosition = sensorPosition;
        MotorConfig.pidSlot = pidSlot;
        MotorConfig.timeoutMs = timeoutMs;
        leftMas.setSelectedSensorPosition(sensorPosition, pidSlot, timeoutMs);
        return leftMas;
    }

    /**
     * Set sensor phase.
     * 
     * @param leftMas
     * @param phase
     * @return motor
     */
    public static WPI_VictorSPX setSensorPhase(final WPI_VictorSPX leftMas, final boolean phase) {
        leftMas.setSensorPhase(phase);
        return leftMas;
    }
    public static TalonSRX setSensorPhase(final TalonSRX motor, final boolean phase) {
        motor.setSensorPhase(phase);
        return motor;
    }

    /**
     * Set invertType
     * 
     * @param isleftmotorinvert
     * @param leftMas
     * @return motor
     */
    public static WPI_VictorSPX setInvert(final WPI_VictorSPX leftMas, final boolean isleftmotorinvert) {
        leftMas.setInverted(isleftmotorinvert);
        return leftMas;
    }
    public static TalonSRX setInvert(final TalonSRX motor, final boolean isleftmotorinvert){
        motor.setInverted(isleftmotorinvert);
        return motor;
    }

    /**
     * Set invertType
     * 
     * @param Victor_master
     * @param invertType
     * @return Victor_master
     */
    public static VictorSPX setInvert(final VictorSPX Victor_master, final InvertType invertType) {
        Victor_master.setInverted(invertType);
        return Victor_master;
    }


    /**
     * Set motor like previous.
     * 
     * @param leftFol 
     * @param sensorPhase
     * @param isleftmotorinvert  {@link MotorConfig}
     */
    public static void configLikePrevious(final WPI_VictorSPX leftFol, final boolean sensorPhase, final boolean isleftmotorinvert) {
        leftFol.configSelectedFeedbackSensor(MotorConfig.sensor);
        leftFol.setSelectedSensorPosition(MotorConfig.sensorPosition, MotorConfig.pidSlot, MotorConfig.timeoutMs);
        leftFol.setSensorPhase(sensorPhase);
        leftFol.setInverted(isleftmotorinvert);
    }

    /**
     * Set motor PID 設置馬達kP, 
     * 
     * 
     * 
     * 
     * @param leftMas 設置馬達
     * @param kP kP值(大約為5分之一kF) 調越大 對誤差的調整更靈敏
     * @param kF kF值 大約為1023(talon滿輸出)/全速運轉的速度單位(以falcon來說大約為21600) 
     * @param slotIdx 閉迴控制位置(0,1,2)
     */
    public static WPI_VictorSPX configPF(final WPI_VictorSPX leftMas,double kP,double kF,int slotIdx){
        leftMas.config_kP(slotIdx, kP);
        leftMas.config_kF(slotIdx, kF);
        return leftMas;
    }
    /**
     * Set motor PID 設置馬達kP, kF
     * 
     * @param motor 設置馬達
     * @param kP kP值(大約為5分之一kF) 調越大 對誤差的調整更靈敏
     * @param kF kF值 大約為1023(talon滿輸出)/全速運轉的速度單位(以falcon來說大約為21600) 
     * @param slotIdx 閉迴控制位置(0,1,2)
     */
    public static TalonSRX configPF(final TalonSRX motor, double kP, double kF, int slotIdx){
        motor.config_kP(slotIdx, kP);
        motor.config_kF(slotIdx, kF);
        return motor;
    }
    /**
     * Set motor P 設置馬達kP
     * @param motor 
     * @param kP kF * 1/5
     * @return
     */
    public static TalonFX configP(final TalonFX motor, double kP){
        motor.config_kP(0, kP);
        return motor;
    }
    /**
     * Set motor PID 設置馬達PID
     * @param motor
     * @param kP 
     * @param kI
     * @param kD
     * @return
     */
    public static TalonFX configPID(final TalonFX motor, double kP, double kI, double kD){
        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);
        return motor;
    }
    public static TalonFX configPIDF(final TalonFX motor, double kP, double kI, double kD, double kF){
        motor.config_kP(0, kP);
        motor.config_kI(0, kI);
        motor.config_kD(0, kD);
        motor.config_kF(0, kF);
        return motor;
    }

    /**
     * set motor PID 設置馬達PID
     * 
     * @param Victor_master 設置馬達

ˇˋ     * @param kP kP值(大約為5分之一kF) 調越大 對誤差的調整更靈敏
     * @param kF kF值 大約為1023(talon滿輸出)/全速運轉的速度單位(以falcon來說大約為21600) 
     * @param slotIdx 閉迴控制位置(0,1,2)
     */
    public static WPI_VictorSPX configPID(final WPI_VictorSPX Victor_master,double kP,double kF,int slotIdx){
        Victor_master.config_kP(slotIdx, kP);
        Victor_master.config_kF(slotIdx, kF);
        return Victor_master;
    }
    
    /** 
     * Config motor voltage.
     * @param rightMas
     * @param voltage
     * @return
     */
    public static WPI_VictorSPX voltageCompSaturation(WPI_VictorSPX rightMas, float voltage){
        rightMas.configVoltageCompSaturation(voltage);
        rightMas.enableVoltageCompensation(true);
        return rightMas;
    }

    /**
     * set motor limit 設置馬達限制
     * @param deadband 設置死區
     * @param percentOut 設置百分比輸出
     * @param percentOut2 
     * @param Ramp 設置完全加速時間
     * @param timeoutMs 設置報錯時間
     */
    public static TalonFX configmotorlimit(final TalonFX motor,double deadband,double percentOut,double percentOut2,double Ramp,int timeoutMs){
        motor.configFactoryDefault();
        motor.configNeutralDeadband(deadband);
        motor.configPeakOutputForward(percentOut,timeoutMs);
        motor.configPeakOutputReverse(percentOut2,timeoutMs);
        motor.configClosedloopRamp(Ramp);
        return motor;
    }
    /**
     * 
     * @param motor motor
     * @param time  seconds
     * @return      motor
     */
    public static TalonFX ramp(TalonFX motor, double time){
        motor.configClosedloopRamp(time, 10);
        return motor;
    }
}
