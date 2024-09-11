package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.AdvancedReactions;
import me.nicka.advancedreactions.Msg;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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

        Msg.send(sender, "&l--List of Reactions--", AdvancedReactions.getPlugin().getPrefix());
        Msg.send(sender, "/highfive");
        Msg.send(sender, "/hug");
        Msg.send(sender, "/kiss");
        Msg.send(sender, "/kiss");
        Msg.send(sender, "/lick");
        Msg.send(sender, "/pet");
        Msg.send(sender, "/pinch");
        Msg.send(sender, "/punch");
        Msg.send(sender, "/slap");
        Msg.send(sender, "/stab");
        Msg.send(sender, "/stare");

        return true;

    }

}
