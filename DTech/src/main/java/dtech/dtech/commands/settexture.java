package dtech.dtech.commands;

import dtech.dtech.DTech;
import dtech.dtech.language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

public class settexture implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 2){
            sender.sendMessage(language.getMessage("parameter.error"));
            return true;
        }
        if(!DTech.AddBlocks.getKeys(false).contains(args[0])){
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        DTech.AddBlocks.set(args[0] + ".texture",args[1]);
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
