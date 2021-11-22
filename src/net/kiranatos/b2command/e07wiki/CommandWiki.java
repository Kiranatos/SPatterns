package net.kiranatos.b2command.e07wiki;

import java.util.HashMap;

// https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0_(%D1%88%D0%B0%D0%B1%D0%BB%D0%BE%D0%BD_%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F)

public class CommandWiki {
    public static void main(final String[] arguments) {
        Light lamp = new Light();

        Command switchOn = new SwitchOnCommand(lamp);
        Command switchOff = new SwitchOffCommand(lamp);

        Switch mySwitch = new Switch();
        mySwitch.register("on", switchOn);
        mySwitch.register("off", switchOff);

        mySwitch.execute("on");
        mySwitch.execute("off");
    }
}

/** The Command interface */
interface Command { void execute(); }

/** The Invoker class */
class Switch {
    private final HashMap<String, Command> commandMap = new HashMap<>();
    
    public void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }
    
    public void execute(String commandName) {
        Command command = commandMap.get(commandName);
        if (command == null) {
            throw new IllegalStateException("no command registered for " + commandName);
        }
        command.execute();
    }
}

/** The Receiver class */
class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

/** The Command for turning on the light - ConcreteCommand #1 */
class SwitchOnCommand implements Command {
    private final Light light;

    public SwitchOnCommand(Light light) {
        this.light = light;
    }

    @Override // Command
    public void execute() {
        light.turnOn();
    }
}

/** The Command for turning off the light - ConcreteCommand #2 */
class SwitchOffCommand implements Command {
    private final Light light;

    public SwitchOffCommand(Light light) {
        this.light = light;
    }

    @Override // Command
    public void execute() {
        light.turnOff();
    }
}