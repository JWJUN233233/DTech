package dtech.dtech.commands;

import dtech.dtech.DTech;
import dtech.dtech.language;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class addblock implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 14){
            sender.sendMessage(language.getMessage("parameter.error"));
            return true;
        }
        if(Material.getMaterial(args[2]) == null || Material.getMaterial(args[2]) == Material.AIR){
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        DTech.AddBlocks.set(args[0] + ".id",args[1]);
        DTech.AddBlocks.set(args[0] + ".material",args[2]);
        DTech.AddBlocks.set(args[0] + ".name",args[3]);
        DTech.AddBlocks.set(args[0] + ".lore",args[4]);
        DTech.AddBlocks.set(args[0] + ".useTexture",false);
        DTech.AddBlocks.set(args[0] + ".useLore",false);
        DTech.AddBlocks.set(args[0] + ".recipe.1",args[5]);
        DTech.AddBlocks.set(args[0] + ".recipe.2",args[6]);
        DTech.AddBlocks.set(args[0] + ".recipe.3",args[7]);
        DTech.AddBlocks.set(args[0] + ".recipe.4",args[8]);
        DTech.AddBlocks.set(args[0] + ".recipe.5",args[9]);
        DTech.AddBlocks.set(args[0] + ".recipe.6",args[10]);
        DTech.AddBlocks.set(args[0] + ".recipe.7",args[11]);
        DTech.AddBlocks.set(args[0] + ".recipe.8",args[12]);
        DTech.AddBlocks.set(args[0] + ".recipe.9",args[13]);

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1){
            List<String> tmp = new ArrayList<>();
            Object[] blocks = DTech.AddBlocks.getKeys(false).toArray();
            for(Object blockname : blocks){
                tmp.add((String) blockname);
            }
            return tmp;
        }
        return new ArrayList<>();
    }
}
