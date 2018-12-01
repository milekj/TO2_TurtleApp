package pl.edu.agh.to2.parser;

/**
 * Created by michal on 16.11.18.
 */
public enum ECommands {
    NP,
    WS,
    LW,
    PW,
    OPU,
    POD;

    public static ECommands getCommand(String txt) throws IllegalArgumentException {
        switch(txt) {
            case "pod"  : return ECommands.POD;
            case "opu"  : return ECommands.OPU;
            case "np"   : return ECommands.NP;
            case "ws"   : return ECommands.WS;
            case "lw"   : return ECommands.LW;
            case "pw"   : return ECommands.PW;
        }
        throw new IllegalArgumentException();
    }
    public static ECommandType getType(ECommands command) throws IllegalArgumentException {
        switch(command) {
            case POD    : return ECommandType.UNARY;
            case OPU    : return ECommandType.UNARY;
            case NP     : return ECommandType.BINARY;
            case WS     : return ECommandType.BINARY;
            case LW     : return ECommandType.BINARY;
            case PW     : return ECommandType.BINARY;
        }
        throw new IllegalArgumentException();
    }
}

