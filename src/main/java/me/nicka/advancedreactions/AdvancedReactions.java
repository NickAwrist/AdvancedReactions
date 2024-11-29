package me.nicka.advancedreactions;

import lombok.Getter;
import me.nicka.advancedreactions.commands.*;
import me.nicka.advancedreactions.files.IgnoreList;
import me.nicka.advancedreactions.models.Reaction;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.CommandMap;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Objects;

public final class AdvancedReactions extends JavaPlugin {

    private static final String permissionPrefix = "advancedreactions.";

    @Getter
    private static AdvancedReactions plugin;

    @Getter
    private final String prefix = "&8[&6AR&8]";

    @Override
    public void onEnable() {
        plugin=this;
        getConfig().options().copyDefaults();
        this.saveDefaultConfig();

        IgnoreList.setup();
        IgnoreList.get().options().copyDefaults();
        IgnoreList.save();

        double VERSION = 2.0;

        if(getConfig().getInt("ConfigVersion") != VERSION){
            Bukkit.getLogger().warning("[AR] OUT-OF-DATE CONFIG. DELETE OLD ONE AND RESTART SERVER");
        }

        Bukkit.getLogger().info("[AR] Loading reactions...");
        loadReactionsFromConfig();

        Bukkit.getLogger().info("[AR] Loading commands...");
        registerReactionCommands();

        Bukkit.getLogger().info("------------AdvancedReactions HAS BEEN ENABLED!!!------------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("------------AdvancedReactions HAS BEEN DISABLED!!!------------");
    }

    // Load reactions from config
    private void loadReactionsFromConfig(){
        ConfigurationSection reactionSection = getConfig().getConfigurationSection("reactions");

        if(reactionSection != null){
            for(String reactionName: reactionSection.getKeys(false)){
                ConfigurationSection section = reactionSection.getConfigurationSection(reactionName);
                if(section != null){
                    final String messageToReceiver = section.getString("messageToReceiver", "");
                    final String messageToSender = section.getString("messageToSender", "");
                    final String particleName = section.getString("particle", null);
                    final String soundName = section.getString("sound", null);
                    final float damage = (float) section.getDouble("damage", 0.0);

                    Particle particle = null;
                    if(particleName != null && !particleName.isEmpty()){
                         particle = Particle.valueOf(particleName);
                    }

                    Sound sound = null;
                    if(soundName != null && !soundName.isEmpty()){
                        sound = Sound.valueOf(soundName);
                    }

                    new Reaction(reactionName, messageToReceiver, messageToSender, particle, sound, damage);

                    Bukkit.getLogger().info("Loaded reaction: "+reactionName);
                }
            }
        }
    }

    // Register reaction commands
    private void registerReactionCommands(){
        Reaction.getReactions().forEach((reactionName, reaction) -> {
            Objects.requireNonNull(getCommandMap()).register(reactionName, new CustomReactionCommand(
                    reactionName,
                    new ReactionCommand(reactionName),
                    "/"+reactionName+" <player>",
                    permissionPrefix+reactionName
            ));
            Bukkit.getLogger().info("Registered command: "+reactionName);
        });
    }

    // Get the command map
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
