package me.nicka.advancedreactions.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CustomReactionCommand extends Command {

    private final CommandExecutor executor;

    public CustomReactionCommand(String name, CommandExecutor executor, String usage, String permission) {
        super(name);
        this.executor = executor;
        this.setUsage(usage);
        this.setPermission(permission);
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        return executor.onCommand(sender, this, commandLabel, args);
    }


}
