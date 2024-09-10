package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.Msg;
import me.nicka.advancedreactions.models.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReactionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        Player senderPlayer = (Player) sender;
        Player receiverPlayer = Bukkit.getPlayer(args[0]);

        if(receiverPlayer == null){
            senderPlayer.sendMessage("Player not found");
            return true;
        }

        String reactionName = command.getName().toLowerCase();

        try {
            Reaction.executeReaction(reactionName, senderPlayer, receiverPlayer);
        } catch(IllegalArgumentException e){
            Msg.send(senderPlayer, e.getMessage());
        }

        return true;
    }
}
