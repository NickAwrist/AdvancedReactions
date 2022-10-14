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

public class Hug {

    final FileConfiguration config = AdvancedReactions.getPlugin().getConfig();
    public Hug(){
        new CommandBase("hug", 1, 1, true){



            @Override
            public boolean onCommand(CommandSender sender, String[] arguments){
                Player p_sender = (Player) sender;
                Player p_receiver = Bukkit.getPlayer(arguments[0]);

                Particle p = Particle.valueOf(config.getString("Messages.Hug.Particle"));
                Sound s = Sound.valueOf(config.getString("Messages.Hug.Sound"));

                if(config.getString("Messages.Hug.Sound").equals(""))
                    s = Sound.ENTITY_PUFFER_FISH_AMBIENT;
                if(config.getString("Messages.Hug.Particle").equals(""))
                    p = Particle.BUBBLE_POP;

                commandEffects(p_receiver, p, s);
                Msg.send(p_receiver, config.getString("Messages.Hug.MessageToReceiver").replace("%SENDER%", p_sender.getName()));
                Msg.send(p_sender, config.getString("Messages.Hug.MessageToSender").replace("%RECEIVER%", p_receiver.getName()));

                return true;
            }

            @Override
            public String getUsage(){
                return "/hug {PLAYER}";
            }
        }.enableDelay(config.getInt("ReactionCooldown"));
    }
}
