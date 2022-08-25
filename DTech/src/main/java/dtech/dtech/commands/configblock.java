package dtech.dtech.commands;

import dtech.dtech.DTech;
import dtech.dtech.language;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class configblock implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 3){
            sender.sendMessage(language.getMessage("parameter.error"));
            return true;
        }
        if(!DTech.AddBlocks.getKeys(false).contains(args[0])){
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        if(!Objects.equals(args[1], "useTexture") & !Objects.equals(args[1], "useLore")){
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        boolean tmp = false;
        if(Objects.equals(args[2], "true")){
            tmp = true;
        }
        if(Objects.equals(args[2], "false")){
            tmp = false;
        }
        if(Objects.equals(args[1], "useTexture")){
            DTech.AddBlocks.set(args[0] + ".useTexture",tmp);
        }
        if(Objects.equals(args[1], "useLore")){
            DTech.AddBlocks.set(args[0] + ".useLore",tmp);
        }
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
        if(args.length == 2){
            List<String> tmp = new ArrayList<>();
            tmp.add("useLore");
            tmp.add("useTexture");
            return tmp;
        }
        if(args.length == 3){
            List<String> tmp = new ArrayList<>();
            tmp.add("true");
            tmp.add("false");
            return tmp;
        }
        return new ArrayList<>();
    }
}
