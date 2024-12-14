package me.nicka.customreactions.commands;

import me.nicka.customreactions.files.IgnoreList;
import org.bukkit.entity.Player;

import java.util.List;

public class CheckIgnore {

    public static boolean checkIgnore(Player p_sender, Player p_receiver){
        String p_receiverName = p_receiver.getName();

        if(!(IgnoreList.get().contains("players."+p_receiverName))) {
            return false;
        }

        List<String> ignoreList = IgnoreList.get().getStringList("players."+p_receiverName);

        return ignoreList.contains(p_sender.getName());
    }
}


