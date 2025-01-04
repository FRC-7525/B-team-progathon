package frc.robot.subsystem.Manager;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystem.Claw.Claw;
import frc.robot.subsystem.Elevator.Elevator;
import frc.robot.subsystem.Wrist.Wrist;


public class Manager {
    XboxController controller;
    private Claw claw;
    private Wrist wrist;
    private Elevator elevator;
    private ManagerStates state;
    
    private ArrayList<Trigger> currentTriggers;

    public Manager() {
        controller = new XboxController(0);
        claw = new Claw();
        wrist = new Wrist();
        elevator = new Elevator();
        state = ManagerStates.IDLE;
        
        state.setTriggers(ManagerStates.IDLE, ManagerConstants.IDLE_TRIGGERS);
        state.setTriggers(ManagerStates.HOLDING, ManagerConstants.HOLDING_TRIGGERS);
        state.setTriggers(ManagerStates.SCORE_HIGH, ManagerConstants.SCORE_HIGH_TRIGGERS);
        state.setTriggers(ManagerStates.SCORE_MIDDLE, ManagerConstants.SCORE_MIDDLE_TRIGGERS);
        state.setTriggers(ManagerStates.INTAKE_LOW, ManagerConstants.INTAKE_LOW_TRIGGERS);
        state.setTriggers(ManagerStates.OUTAKE, ManagerConstants.OUTAKE_TRIGGERS);

        currentTriggers.addAll(state.getTriggers());
        SubsystemActions.setSubsystem(claw, wrist, elevator);
    }

    private void handleTriggers() {
        for (Trigger trigger : currentTriggers) {
            if (trigger.isTriggered()) {
                state = trigger.getTargetState();
            }
        }
        currentTriggers.clear();
        currentTriggers.addAll(state.getTriggers());
    }

    public void periodic() {
        handleTriggers();
        SubsystemActions.execute(state);
        SmartDashboard.putString("ManagerState", state.getStateString());
    
        claw.periodic();
        wrist.periodic();
        elevator.periodic();   
    }
}