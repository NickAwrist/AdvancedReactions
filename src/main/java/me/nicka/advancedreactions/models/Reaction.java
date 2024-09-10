package me.nicka.advancedreactions.models;

import lombok.Getter;
import me.nicka.advancedreactions.Msg;
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
    private final String messageToReceiver;
    private final String messageToSender;
    private final Particle particle;
    private final Sound sound;
    private final boolean dealsDamage;

    public Reaction(String reactionName, String messageToReceiver, String messageToSender, Particle particle, Sound sound, boolean dealsDamage){
        this.reactionName = reactionName;
        this.messageToReceiver = messageToReceiver;
        this.messageToSender = messageToSender;
        this.particle = particle;
        this.sound = sound;
        this.dealsDamage = dealsDamage;

        REACTIONS.put(reactionName.toLowerCase(), this);
    }

    public static void executeReaction(String reactionName, Player sender, Player receiver){
        Reaction reaction = REACTIONS.get(reactionName);

        // Check if the reaction exists
        if(reaction == null){
            throw new IllegalArgumentException("Reaction with name \"" + reactionName + "\" does not exist.");
        }

        // Edit the messages to include the sender and receiver names
        String receiverName = receiver.getName();
        String senderName = sender.getName();

        String receiverMessage = reaction.getMessageToReceiver().replace("%SENDER%", senderName).replace("%RECEIVER%", receiverName);
        String senderMessage = reaction.getMessageToSender().replace("%SENDER%", senderName).replace("%RECEIVER%", receiverName);

        // Summon the particles
        Particle particle = reaction.getParticle();
        if(particle.equals(Particle.BLOCK_DUST)){
            BlockData blood = Material.REDSTONE_BLOCK.createBlockData();

            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX()+1, receiver.getLocation().getY()+1, receiver.getLocation().getZ(), 3, blood);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX()-1, receiver.getLocation().getY()+1, receiver.getLocation().getZ(), 3, blood);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX(), receiver.getLocation().getY()+1, receiver.getLocation().getZ()+1, 3, blood);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX(), receiver.getLocation().getY()+1, receiver.getLocation().getZ()-1, 3, blood);
        }else {
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX() + 1, receiver.getLocation().getY() + 1, receiver.getLocation().getZ(), 1);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX() - 1, receiver.getLocation().getY() + 1, receiver.getLocation().getZ(), 1);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX(), receiver.getLocation().getY() + 1, receiver.getLocation().getZ() + 1, 1);
            receiver.getWorld().spawnParticle(particle, receiver.getLocation().getX(), receiver.getLocation().getY() + 1, receiver.getLocation().getZ() - 1, 1);
        }

        // Play the sound
        Sound sound = reaction.getSound();
        receiver.playSound(receiver.getLocation(), sound, SoundCategory.PLAYERS, 1.0F, 1.0F);

        // Send the player messages
        Msg.send(receiver, receiverMessage);
        Msg.send(sender, senderMessage);
    }

}
