package me.nicka.customreactions.commands;

import lombok.Getter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CustomReactionCommand extends Command {

    @Getter
    private static final ArrayList<CustomReactionCommand> reactions = new ArrayList<>();
    private final CommandExecutor executor;
    @Getter
    private final String name;

    public CustomReactionCommand(String name, CommandExecutor executor, String usage, String permission) {
        super(name);
        this.name = name;
        this.executor = executor;
        this.setUsage(usage);
        this.setPermission(permission);
        reactions.add(this);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return executor.onCommand(sender, this, commandLabel, args);
    }


}
