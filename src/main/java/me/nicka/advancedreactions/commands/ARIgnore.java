package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.CommandBase;
import me.nicka.advancedreactions.Msg;
import me.nicka.advancedreactions.files.IgnoreList;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ARIgnore {
    public ARIgnore(){
        new CommandBase("arignore", 1, 1, true){
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments){
                Player p_sender = (Player) sender;

                if(p_sender.getName().equalsIgnoreCase(arguments[0])){
                    Msg.send(p_sender, "&cYou cannot ignore yourself");
                    return true;
                }

                if(!(IgnoreList.get().contains("players."+p_sender.getName()))){
                    List<String> ignoreList = new ArrayList<>();
                    ignoreList.add(arguments[0]);
                    IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
                    Msg.send(sender, "&c&l"+arguments[0]+" is now being ignored. Do /arignore "+arguments[0]+" to remove listen to them again.");
                    IgnoreList.save();
                    return true;
                }

                List<String> ignoreList = IgnoreList.get().getStringList("players."+p_sender.getName());
                if(ignoreList.contains(arguments[0])){
                    ignoreList.remove(arguments[0]);
                    IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
                    IgnoreList.save();
                    Msg.send(sender, "&a&l"+arguments[0]+" is no longer being ignored.");
                    return true;
                }

                ignoreList.add(arguments[0]);
                IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
                Msg.send(sender, "&c&l"+arguments[0]+" is now being ignored. Do /arignore "+arguments[0]+" to remove listen to them again.");
                IgnoreList.save();
                return true;
            }

            @Override
            public String getUsage(){
                return "/arignore {PLAYER}";
            }
        };
    }
}
