package io.egen.rules;

import io.egen.entity.Tires;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

@Rule
public class CarTrackerLowPriorityRule {

    private Tires tires;
    private Boolean engineCoolantLow;
    private Boolean checkEngineLightOn;
    private Boolean flagTire;
    private Boolean flagEngineCoolant;
    private Boolean flagEngineLight;

    @Condition
    public boolean checkInput() {

        Integer frontLeft = tires.getFrontLeft();
        Integer frontRight = tires.getFrontRight();
        Integer rearLeft = tires.getRearLeft();
        Integer rearRight = tires.getRearRight();

        if((frontLeft  < 32 )||( frontRight  < 32)|| (rearLeft  < 32)||( rearRight  < 32)){
            flagTire = Boolean.TRUE;
            return true;

        }else if((frontLeft  > 36 )||( frontRight > 36)|| (rearLeft > 36)||(rearRight > 36)){
            flagTire = Boolean.TRUE;
            return true;
        }else if(engineCoolantLow){
            flagEngineCoolant = Boolean.TRUE;
            return true;
        }else{
            flagEngineLight = Boolean.TRUE;
            return checkEngineLightOn;
        }



    }

    @Action
    public void messageToPrint() throws Exception {

        if(flagTire)
            System.out.println("Low priority! one of the tires doesn't have the required air in the tube.");

        if(flagEngineCoolant)
            System.out.println("Low priority! the coolant in the engine is low.");

        if(flagEngineLight)
            System.out.println("Low priority! the check engine light is on.");
    }

    public void setInput(Tires tires, Boolean engineCoolantLow, Boolean checkEngineLightOn) {
        this.tires = tires;
        this.engineCoolantLow = engineCoolantLow;
        this.checkEngineLightOn = checkEngineLightOn;
        this.flagTire = Boolean.FALSE;
        this.flagEngineCoolant = Boolean.FALSE;
        this.flagEngineLight = Boolean.FALSE;
    }

    @Priority
    public int getPriority(){
        return 3;
    }

}
