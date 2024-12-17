package me.nicka.customreactions;

import lombok.Getter;
import me.nicka.customreactions.commands.*;
import me.nicka.customreactions.models.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public final class CustomReactions extends JavaPlugin {

    @Getter
    private static CustomReactions plugin;

    @Getter
    private final String prefix = "&8[&6AR&8]";

    @Override
    public void onEnable() {
        plugin=this;
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        double version = 2.0;

        if(getConfig().getDouble("ConfigVersion") != version){
            Bukkit.getLogger().warning("[CR] OUT-OF-DATE CONFIG. DELETE OLD ONE AND RESTART SERVER");
        }

        Bukkit.getLogger().info("[CR] Loading reactions...");
        loadReactionsFromConfig();

        Bukkit.getLogger().info("[CR] Loading commands...");
        registerReactionCommands();

        // Register /reactions command
        getCommand("reactions").setExecutor(new ReactionList());

        Bukkit.getLogger().info("------------CustomReactions HAS BEEN ENABLED!!!------------");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("------------CustomReactions HAS BEEN DISABLED!!!------------");
    }


   private void loadReactionsFromConfig(){
       ConfigurationSection reactionSection = getConfig().getConfigurationSection("reactions");

       int totalReaction = 0;
       if(reactionSection != null){
           for(String reactionName: reactionSection.getKeys(false)){
               ConfigurationSection section = reactionSection.getConfigurationSection(reactionName);
               if(section != null){
                   String messageToTarget = section.getString("messageToTarget", "");
                   String messageToSender = section.getString("messageToSender", "");
                   String particleName = section.getString("particle", null);
                   String soundName = section.getString("sound", null);
                   float damageNum = (float) section.getDouble("damageNum", 0.0);

                   Particle particle = null;
                   if(particleName != null && !particleName.isEmpty()){
                        particle = Particle.valueOf(particleName);
                   }

                   Sound sound = null;
                   if(soundName != null && !soundName.isEmpty()){
                       sound = Sound.valueOf(soundName);
                   }

                   new Reaction(reactionName, messageToTarget, messageToSender, particle, sound, damageNum);

                   Bukkit.getLogger().info("Loaded reaction: "+reactionName);
                     totalReaction++;
               }
           }
       }
       Bukkit.getLogger().info("[CR] Loaded "+totalReaction+" reactions");
   }

   private void registerReactionCommands(){
        Reaction.getReactions().forEach((reactionName, reaction) -> {
            getCommandMap().register(reactionName, new CustomReactionCommand(
                    reactionName,
                    new ReactionCommand(),
                    "/"+reactionName+" <player>",
                    "customreactions."+reactionName
            ));
            Bukkit.getLogger().info("Registered command: "+reactionName);
        });
   }

    private CommandMap getCommandMap() {
        try {
            final Field bukkitCommandMap = getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            return (CommandMap) bukkitCommandMap.get(getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }

}
