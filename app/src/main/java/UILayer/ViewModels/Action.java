package UILayer.ViewModels;

public class Action{
    public static final int SHOW_HOME = 0;
    public static final int SHOW_INVALID_LOGIN = 1;
    public static final int SHOW_NAME_TAKEN = 2;
    public static final int SHOW_INVALID_PASSWORD = 3;
    public static final int SHOW_REGISTER = 4;
    private final int action;

    public Action(int action){
        this.action = action;
    }

    public int getValue(){
        return action;
    }
}
