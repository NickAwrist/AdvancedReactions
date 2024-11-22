package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.AdvancedReactions;
import me.nicka.advancedreactions.Msg;
import me.nicka.advancedreactions.models.Reaction;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ARList implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(!(commandSender instanceof Player)){
            Msg.send(commandSender, "&cYou must be a player to use this command.");
            return true;
        }

        Player sender = (Player) commandSender;

        // Check if the player has proper permissions
        if(!sender.hasPermission("advancedreactions.list")){
            Msg.send(sender, "&cYou do not have permission to use this command");
            return true;
        }

        HashMap<String, Reaction> reactionHashMap = Reaction.getReactions();

        Msg.send(sender, "&l--List of Reactions--", AdvancedReactions.getPlugin().getPrefix());
        for(String reactionName : reactionHashMap.keySet()){
            Msg.send(sender, "&7- &e" + reactionName, AdvancedReactions.getPlugin().getPrefix());
        }

        return true;
    }

}
