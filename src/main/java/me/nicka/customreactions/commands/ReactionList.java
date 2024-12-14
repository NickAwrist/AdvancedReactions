package me.nicka.customreactions.commands;

import me.nicka.customreactions.CustomReactions;
import me.nicka.customreactions.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ReactionList implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)){
            Msg.send(commandSender, "&cYou must be a player to use this command.");
            return true;
        }

        Player sender = (Player) commandSender;

        // Check if the player has proper permissions
        if(!sender.hasPermission("customreactions.command.reactions") && !sender.hasPermission("customreactions.*") && !sender.isOp()){
            Msg.send(sender, "&cYou do not have permission to use this command");
            return true;
        }

        Msg.send(sender, "&l--List of Reactions--", CustomReactions.getPlugin().getPrefix());
        ArrayList<CustomReactionCommand> reactions = CustomReactionCommand.getReactions();
        for(CustomReactionCommand reaction : reactions){
            Msg.send(sender, "&7- &e/"+reaction.getName());
        }


        return true;

    }

}
