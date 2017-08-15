package io.egen.rules;



import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Priority;
import org.easyrules.annotation.Rule;

@Rule
public class CarTrackerHighPriorityRule {


    private Integer engineRpm;
    private Integer readlineRpm;

    @Condition
    public boolean checkInput() {

        if(engineRpm > readlineRpm){
            return true;
        }

        return false;
    }

    @Action
    public void messageToPrint() throws Exception {

        System.out.println("High priority! your engineRpm is greater than the readlineRpm");
    }

    public void setInput(Integer engineRpm,Integer readlineRpm) {
        this.engineRpm = engineRpm;
        this.readlineRpm = readlineRpm;
    }

    @Priority
    public int getPriority(){
        return 1;
    }


}
