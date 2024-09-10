package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.models.Reaction;
import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class CommandEffects {

    public static void commandEffects(Player p_receiver, Particle particle, Sound sound){

        Reaction.part(p_receiver, particle);


        p_receiver.playSound(p_receiver.getLocation(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}
