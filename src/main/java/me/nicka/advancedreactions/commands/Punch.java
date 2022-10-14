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

public class Punch {
    public Punch(){

        final FileConfiguration config = AdvancedReactions.getPlugin().getConfig();
        new CommandBase("punch", 1, 1, true){
            @Override
            public boolean onCommand(CommandSender sender, String[] arguments){
                Player p_sender = (Player) sender;
                Player p_receiver = Bukkit.getPlayer(arguments[0]);


                Particle p = Particle.valueOf(config.getString("Messages.Punch.Particle"));
                Sound s = Sound.valueOf(config.getString("Messages.Punch.Sound"));

                if(config.getString("Messages.Punch.Sound").equals(""))
                    s = Sound.ENTITY_PUFFER_FISH_AMBIENT;
                if(config.getString("Messages.Punch.Particle").equals(""))
                    p = Particle.BUBBLE_POP;

                commandEffects(p_receiver, p, s);
                Msg.send(p_receiver, config.getString("Messages.Punch.Message").replace("%SENDER%", p_sender.getName()));

                if(config.getBoolean("Messages.Punch.Damage"))
                    p_receiver.damage(config.getDouble("ReactionDamage"));

                return true;
            }

            @Override
            public String getUsage(){
                return "/punch {PLAYER}";
            }
        }.enableDelay(config.getInt("ReactionCooldown"));
    }
}
