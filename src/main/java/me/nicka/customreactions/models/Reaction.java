package me.nicka.customreactions.models;

import lombok.Getter;
import me.nicka.customreactions.Msg;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Getter
public class Reaction {

    private static final HashMap<String, Reaction> REACTIONS = new HashMap<>();

    private final String reactionName;
    private final String messageToTarget;
    private final String messageToSender;
    private final Particle particle;
    private final Sound sound;
    private final float damageNum;

    public Reaction(String reactionName, String messageToTarget, String messageToSender, Particle particle, Sound sound, float damageNum){
        this.reactionName = reactionName;
        this.messageToTarget = messageToTarget;
        this.messageToSender = messageToSender;
        this.particle = particle;
        this.sound = sound;
        this.damageNum = damageNum;

        REACTIONS.put(reactionName.toLowerCase(), this);
    }

    public static HashMap<String, Reaction> getReactions(){
        return REACTIONS;
    }

    public static void executeReaction(String reactionName, Player sender, Player target) {
        Reaction reaction = REACTIONS.get(reactionName);

        // Check if the reaction exists
        if (reaction == null) {
            throw new IllegalArgumentException("Reaction with name \"" + reactionName + "\" does not exist.");
        }

        // Edit the messages to include the sender and target names
        String targetName = target.getName();
        String senderName = sender.getName();

        String targetMessage = reaction.getMessageToTarget().replace("%SENDER%", senderName).replace("%TARGET%", targetName);
        String senderMessage = reaction.getMessageToSender().replace("%SENDER%", senderName).replace("%TARGET%", targetName);

        // Summon the particles
        Particle particle = reaction.getParticle();
        if (particle != null) {
            displayParticles(target, particle);
        }

        // Play the sound
        Sound sound = reaction.getSound();
        if (sound != null) {
            target.playSound(target.getLocation(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);
        }

        if (reaction.getDamageNum() > 0.0) {
            target.damage(reaction.damageNum);
        }

        // Send the player messages
        Msg.send(target, targetMessage);
        Msg.send(sender, senderMessage);
    }

    private static void displayParticles(Player target, Particle particle) {
        if (particle.equals(Particle.BLOCK_DUST)) {
            BlockData blood = Material.REDSTONE_BLOCK.createBlockData();

            target.getWorld().spawnParticle(particle, target.getLocation().getX() + 1, target.getLocation().getY() + 1, target.getLocation().getZ(), 3, blood);
            target.getWorld().spawnParticle(particle, target.getLocation().getX() - 1, target.getLocation().getY() + 1, target.getLocation().getZ(), 3, blood);
            target.getWorld().spawnParticle(particle, target.getLocation().getX(), target.getLocation().getY() + 1, target.getLocation().getZ() + 1, 3, blood);
            target.getWorld().spawnParticle(particle, target.getLocation().getX(), target.getLocation().getY() + 1, target.getLocation().getZ() - 1, 3, blood);
        } else {
            target.getWorld().spawnParticle(particle, target.getLocation().getX() + 1, target.getLocation().getY() + 1, target.getLocation().getZ(), 1);
            target.getWorld().spawnParticle(particle, target.getLocation().getX() - 1, target.getLocation().getY() + 1, target.getLocation().getZ(), 1);
            target.getWorld().spawnParticle(particle, target.getLocation().getX(), target.getLocation().getY() + 1, target.getLocation().getZ() + 1, 1);
            target.getWorld().spawnParticle(particle, target.getLocation().getX(), target.getLocation().getY() + 1, target.getLocation().getZ() - 1, 1);
        }
    }

}
