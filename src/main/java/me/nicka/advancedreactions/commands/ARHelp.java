package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.CommandBase;
import me.nicka.advancedreactions.Msg;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ARHelp {
    public ARHelp(){

        new CommandBase("arhelp"){
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments){
                Player p_sender = (Player) sender;

                Msg.send(p_sender, "&l--List of Reactions--");
                Msg.send(p_sender, "/highfive");
                Msg.send(p_sender, "/hug");
                Msg.send(p_sender, "/kiss");
                Msg.send(p_sender, "/kiss");
                Msg.send(p_sender, "/lick");
                Msg.send(p_sender, "/pet");
                Msg.send(p_sender, "/pinch");
                Msg.send(p_sender, "/punch");
                Msg.send(p_sender, "/slap");
                Msg.send(p_sender, "/stab");
                Msg.send(p_sender, "/stare");
                Msg.send(p_sender, "/arignore");

                return true;
            }

            @Override
            public String getUsage(){
                return "/arhelp";
            }
        };
    }
}
