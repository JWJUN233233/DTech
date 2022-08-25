package dtech.dtech.commands;

import dtech.dtech.language;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class summon implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            sender.sendMessage(language.getMessage("command.cannot-run-in-player", (Player) sender));
            return true;
        }
        if(args.length != 5){
            sender.sendMessage(language.getMessage("parameter.error"));
            return true;
        }
        EntityType entity;
        try{
            entity = EntityType.valueOf(args[0]);
        } catch (IllegalArgumentException e) {
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        Objects.requireNonNull(Bukkit.getWorld(args[4])).spawnEntity(new Location(Bukkit.getWorld(args[4]),Double.parseDouble(args[1]),Double.parseDouble(args[2]),Double.parseDouble(args[3])),entity);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            return new ArrayList<>();
        }
        else if(args.length == 2 || args.length == 3 || args.length == 4){
            return new ArrayList<>();
        }
        else if(args.length == 5){
            List<World> worlds = Bukkit.getWorlds();
            List<String> strings = new ArrayList<>();
            for(World w : worlds){
                strings.add(w.getName());
            }
            return strings;
        }
        else {
            return new ArrayList<>();
        }
    }
}
