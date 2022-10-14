package me.nicka.advancedreactions.commands;

import me.nicka.advancedreactions.AdvancedReactions;
import me.nicka.advancedreactions.CommandBase;
import me.nicka.advancedreactions.Msg;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static me.nicka.advancedreactions.commands.CommandEffects.commandEffects;

public class Pet{

    final FileConfiguration config = AdvancedReactions.getPlugin().getConfig();
    public Pet(){
        new CommandBase("pet", 1, 1, true){


            @Override
            public boolean onCommand(CommandSender sender, String[] arguments){
                Player p_sender = (Player) sender;
                Player p_receiver = Bukkit.getPlayer(arguments[0]);

                Particle p = Particle.valueOf(config.getString("Messages.Pet.Particle"));
                Sound s = Sound.valueOf(config.getString("Messages.Pet.Sound"));

                if(config.getString("Messages.Pet.Sound").equals(""))
                    s = Sound.ENTITY_PUFFER_FISH_AMBIENT;
                if(config.getString("Messages.Pet.Particle").equals(""))
                    p = Particle.BUBBLE_POP;

                commandEffects(p_receiver, p, s);

                Msg.send(p_receiver, config.getString("Messages.Pet.MessageToReceiver").replace("%SENDER%", p_sender.getName()));
                Msg.send(p_sender, config.getString("Messages.Pet.MessageToSender").replace("%RECEIVER%", p_receiver.getName()));


                return true;
            }

            @Override
            public String getUsage(){
                return "/pet {PLAYER}";
            }
        }.enableDelay(config.getInt("ReactionCooldown"));
    }
}
