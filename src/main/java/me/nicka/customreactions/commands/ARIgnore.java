package me.nicka.customreactions.commands;

import me.nicka.customreactions.Msg;
import me.nicka.customreactions.files.IgnoreList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ARIgnore implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        Player p_sender = (Player) commandSender;

        if(p_sender.getName().equalsIgnoreCase(strings[0])){
            Msg.send(p_sender, "&cYou cannot ignore yourself");
            return true;
        }

        if(!(IgnoreList.get().contains("players."+p_sender.getName()))){
            List<String> ignoreList = new ArrayList<>();
            ignoreList.add(strings[0]);
            IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
            Msg.send(p_sender, "&c&l"+strings[0]+" is now being ignored. Do /arignore "+strings[0]+" to remove listen to them again.");
            IgnoreList.save();
            return true;
        }

        List<String> ignoreList = IgnoreList.get().getStringList("players."+p_sender.getName());
        if(ignoreList.contains(strings[0])){
            ignoreList.remove(strings[0]);
            IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
            IgnoreList.save();
            Msg.send(p_sender, "&a&l"+strings[0]+" is no longer being ignored.");
            return true;
        }

        ignoreList.add(strings[0]);
        IgnoreList.get().set("players."+p_sender.getName(), ignoreList);
        Msg.send(p_sender, "&c&l"+strings[0]+" is now being ignored. Do /arignore "+strings[0]+" to remove listen to them again.");
        IgnoreList.save();
        return true;
    }
}
