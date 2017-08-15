package io.egen.rules;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

@Rule
public class CarTrackerMediumPriorityRule {

    private Integer maxFuelVolume;
    private Integer fuelVolume;

    @Condition
    public boolean checkInput() {

        Integer remMaxFuelVol = maxFuelVolume / 10 ;
        if(fuelVolume > remMaxFuelVol){
            return true;
        }
        return false;
    }

    @Action
    public void messageToPrint() throws Exception {

        System.out.println("Medium priority! your fuel tank is almost full.");
    }

    public void setInput(Integer maxFuelVolume,Integer fuelVolume) {
        this.maxFuelVolume = maxFuelVolume;
        this.fuelVolume = fuelVolume;
    }

    @Priority
    public int getPriority(){
        return 2;
    }

}
