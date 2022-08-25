package dtech.dtech.commands;

import dtech.dtech.language;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class takeitem implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length != 1){
            sender.sendMessage(language.getMessage("parameter.error"));
            return true;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null){
            sender.sendMessage(language.getMessage("parameter.error2"));
            return true;
        }
        ItemStack tmp = player.getInventory().getItemInMainHand();
        if(tmp.getType() == Material.AIR){
            sender.sendMessage(language.getMessage("command.takeitem.error.itemTypeError"));
            return true;
        }
        else{
            tmp.setAmount(tmp.getAmount() - 1);
        }
        player.getInventory().setItemInMainHand(tmp);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }
}
