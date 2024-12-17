package me.nicka.customreactions.commands;

import me.nicka.customreactions.Msg;
import me.nicka.customreactions.models.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReactionCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        // Check if the sender is a player
        if(!(sender instanceof Player)){
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        // Get the player objects and reaction name
        Player senderPlayer = (Player) sender;
        Player targetPlayer = Bukkit.getPlayer(args[0]);
        String reactionName = command.getName().toLowerCase();

        // Check if the player has proper permissions
        if (!senderPlayer.hasPermission("customreactions.reaction." + reactionName)
                && !senderPlayer.hasPermission("customreactions.reaction.*")
                && !senderPlayer.isOp()) {
            Msg.send(senderPlayer, "&cYou do not have permission to use this reaction");
            return true;
        }


        // Check if the target exists
        if(targetPlayer == null){
            senderPlayer.sendMessage("Player not found");
            return true;
        }

        // Execute the reaction
        try {
            Reaction.executeReaction(reactionName, senderPlayer, targetPlayer);
        } catch(IllegalArgumentException e){
            Msg.send(senderPlayer, e.getMessage());
        }

        return true;
    }
}
