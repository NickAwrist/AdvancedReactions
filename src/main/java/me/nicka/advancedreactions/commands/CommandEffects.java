package me.nicka.advancedreactions.commands;

import org.bukkit.*;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

public class CommandEffects {

    public static void commandEffects(Player p_receiver, Particle particle, Sound sound){

        if(particle.equals(Particle.BLOCK_DUST)){
            BlockData blood = Material.REDSTONE_BLOCK.createBlockData();

            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX()+1, p_receiver.getLocation().getY()+1, p_receiver.getLocation().getZ(), 3, blood);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX()-1, p_receiver.getLocation().getY()+1, p_receiver.getLocation().getZ(), 3, blood);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX(), p_receiver.getLocation().getY()+1, p_receiver.getLocation().getZ()+1, 3, blood);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX(), p_receiver.getLocation().getY()+1, p_receiver.getLocation().getZ()-1, 3, blood);
        }else {
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX() + 1, p_receiver.getLocation().getY() + 1, p_receiver.getLocation().getZ(), 1);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX() - 1, p_receiver.getLocation().getY() + 1, p_receiver.getLocation().getZ(), 1);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX(), p_receiver.getLocation().getY() + 1, p_receiver.getLocation().getZ() + 1, 1);
            p_receiver.getWorld().spawnParticle(particle, p_receiver.getLocation().getX(), p_receiver.getLocation().getY() + 1, p_receiver.getLocation().getZ() - 1, 1);
        }
        

        p_receiver.playSound(p_receiver.getLocation(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
    }
}
