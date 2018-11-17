package pl.edu.agh.to2.parser;

/**
 * Created by michal on 16.11.18.
 */
public class Command {
    private ECommands command;
    private int number;

    public Command(ECommands command) {
        this.command = command;
    }
    public Command(ECommands command, int number) {
        this.command = command;
        this.number = number;
    }
    public ECommands getCommand() { return command; }
    public int getNumber() { return number; }
}
