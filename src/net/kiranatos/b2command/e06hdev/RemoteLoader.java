package net.kiranatos.b2command.e06hdev;

import net.kiranatos.b2command.e06hdev.commands.Command;
import net.kiranatos.b2command.e06hdev.commands.ConcreteCommand;
import net.kiranatos.b2command.e06hdev.commands.MacroCommand;
import net.kiranatos.b2command.e06hdev.recivers.CeilingFan;
import net.kiranatos.b2command.e06hdev.recivers.GarageDoor;
import net.kiranatos.b2command.e06hdev.recivers.Hottub;
import net.kiranatos.b2command.e06hdev.recivers.Light;
import net.kiranatos.b2command.e06hdev.recivers.Stereo;
import net.kiranatos.b2command.e06hdev.recivers.TV;

/**
 * Сьерра К., Бейтс Б., Фримен Э., Фримен Э. - Паттерны проектирования (2011-656-1).
 * стр. 238 глава 6
*/

public class RemoteLoader {    
    public static void main(String[] args) {
        // Invokers:
        RemoteControl rmc = new RemoteControl();

        // Receivers (создание всех устройств)
        Light lightRoomOne = new Light("Living Room");
        Light lightRoomTwo = new Light("Kitchen");
        CeilingFan fan = new CeilingFan("Living Room");
        GarageDoor door = new GarageDoor();
        Stereo stereo = new Stereo();

        //Создание комманд для управления освещением
        ConcreteCommand.LightOnCommand livingRoomLightOn = new ConcreteCommand.LightOnCommand(lightRoomOne);
        ConcreteCommand.LightOffCommand livingRoomLightOff = new ConcreteCommand.LightOffCommand(lightRoomOne);
        ConcreteCommand.LightOnCommand kitchenRoomLightOn = new ConcreteCommand.LightOnCommand(lightRoomTwo);
        ConcreteCommand.LightOffCommand kitchenRoomLightOff = new ConcreteCommand.LightOffCommand(lightRoomTwo);

        //Создание комманд для управления потолочным вентилятором
        ConcreteCommand.CeilingFanOnCommand cellingFanOn = new ConcreteCommand.CeilingFanOnCommand(fan);
        ConcreteCommand.CeilingFanOffCommand cellingFanOff = new ConcreteCommand.CeilingFanOffCommand(fan);

        //Создание комманд для управления гаражной дверью
        ConcreteCommand.GarageDoorOpenCommand doorOpen = new ConcreteCommand.GarageDoorOpenCommand(door);
        ConcreteCommand.GarageDoorCloseCommand doorClose = new ConcreteCommand.GarageDoorCloseCommand(door);

        //Создание комманд для управления магнитофоном
        ConcreteCommand.StereoOnCommand stereoOn = new ConcreteCommand.StereoOnCommand(stereo);
        ConcreteCommand.StereoOffCommand stereoOff = new ConcreteCommand.StereoOffCommand(stereo);

        //Готовые команды связываются с ячейками пульта
        rmc.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        rmc.setCommand(1, kitchenRoomLightOn, kitchenRoomLightOff);
        rmc.setCommand(2, cellingFanOn, cellingFanOff);
        rmc.setCommand(3, stereoOn, stereoOff);

        System.out.println("============= Test #1 =============");
        System.out.println(rmc);

        rmc.onButtonWasPushed(0);
        rmc.offButtonWasPushed(0);
        rmc.onButtonWasPushed(1);
        rmc.offButtonWasPushed(1);
        rmc.onButtonWasPushed(2);
        rmc.offButtonWasPushed(2);
        rmc.onButtonWasPushed(3);
        rmc.offButtonWasPushed(3);

        System.out.println("\n============= Test #2 =============");
        System.out.println(rmc);
        rmc.onButtonWasPushed(0);
        rmc.offButtonWasPushed(0);        
        rmc.undoButtonWasPushed();
        rmc.offButtonWasPushed(0);
        rmc.onButtonWasPushed(0);        
        rmc.undoButtonWasPushed();

        System.out.println("\n============= Test #3 Вентилятор (переопределение кнопок 0-2) =============");
        ConcreteCommand.CeilingFanMediumCommand fanMedium = new ConcreteCommand.CeilingFanMediumCommand(fan);
        ConcreteCommand.CeilingFanHighCommand fanHigh = new ConcreteCommand.CeilingFanHighCommand(fan);
        ConcreteCommand.CeilingFanOffCommand fanOff = new ConcreteCommand.CeilingFanOffCommand(fan);

        rmc.setCommand(0, fanMedium, fanOff);
        rmc.setCommand(1, fanHigh, fanOff);
        
        System.out.println(rmc);        

        rmc.onButtonWasPushed(0);
        rmc.offButtonWasPushed(0);        
        rmc.undoButtonWasPushed();
        rmc.onButtonWasPushed(1);        
        rmc.undoButtonWasPushed();

        System.out.println("\n============= Test #4 Макрос (set many commands on one button) =============");
        // создание новых устройств
        Light lightRoomThree = new Light("Guest Room");
        TV tv = new TV();
        Stereo stereoSecond = new Stereo();
        Hottub hottub = new Hottub();

        //Создание комманд для управления освещением
        ConcreteCommand.LightOnCommand roomThreeLightOn = new ConcreteCommand.LightOnCommand(lightRoomThree);
        ConcreteCommand.LightOffCommand roomThreeLightOff = new ConcreteCommand.LightOffCommand(lightRoomThree);

        //Создание комманд для управления магнитофоном
        ConcreteCommand.StereoOnCommand stereoSecondOn = new ConcreteCommand.StereoOnCommand(stereoSecond);
        ConcreteCommand.StereoOffCommand stereoSecondOff = new ConcreteCommand.StereoOffCommand(stereoSecond);

        //Создание комманд для управления Телевизором
        ConcreteCommand.TVOnCommand tvOn = new ConcreteCommand.TVOnCommand(tv);
        ConcreteCommand.TVOffCommand tvOff = new ConcreteCommand.TVOffCommand(tv);

        //Создание комманд для управления Джакузи
        ConcreteCommand.HottubOnCommand hotTubOn = new ConcreteCommand.HottubOnCommand(hottub);
        ConcreteCommand.HottubOffCommand hotTubOff = new ConcreteCommand.HottubOffCommand(hottub);

        Command[] partyOn = {roomThreeLightOn, stereoSecondOn, tvOn, hotTubOn};
        Command[] partyOff = {roomThreeLightOff, stereoSecondOff, tvOff, hotTubOff};

        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        rmc.setCommand(0, partyOnMacro, partyOffMacro);

        System.out.println(rmc);
        System.out.println("--- Pushing Macro On ---");
        rmc.onButtonWasPushed(0);
        System.out.println("--- Pushing Macro Off ---");
        rmc.offButtonWasPushed(0);
    }    
}