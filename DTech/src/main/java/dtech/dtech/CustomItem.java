package dtech.dtech;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class CustomItem extends SlimefunItem {

    public CustomItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        BlockUseHandler blockUseHandler = this::onBlockRightClick;
        addItemHandler(blockUseHandler);

        ItemUseHandler itemUseHandler = this::onItemRightClick;
        addItemHandler(itemUseHandler);

    }
    private void onBlockRightClick(PlayerRightClickEvent event) {
        List<String> command = DTech.AddBlocks.getStringList(this.getId() + ".BlockUse");
        Location playerloc = event.getPlayer().getLocation();
        String playerLocation = String.valueOf(playerloc.getX()) + " " + String.valueOf(playerloc.getY()) + " " + String.valueOf(playerloc.getZ());
        for (String c: command){
            //System.out.println(c.replace("[playerWorld]",event.getPlayer().getLocation().getWorld().getName()).replace("[playerLocation]",playerLocation).replace("[playerName]",event.getPlayer().getName()));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),c.replace("[playerWorld]",event.getPlayer().getLocation().getWorld().getName()).replace("[playerLocation]",playerLocation).replace("[playerName]",event.getPlayer().getName()));
        }
        if(command.size() == 0){
            event.cancel();
        }
    }

    private void onItemRightClick(PlayerRightClickEvent event) {
        List<String> command = DTech.AddBlocks.getStringList(this.getId() + ".ItemUse");
        Location playerloc = event.getPlayer().getLocation();
        String playerLocation = String.valueOf(playerloc.getX()) + " " + String.valueOf(playerloc.getY()) + " " + String.valueOf(playerloc.getZ());
        for (String c: command){
            //System.out.println(c.replace("[playerWorld]",event.getPlayer().getLocation().getWorld().getName()).replace("[playerLocation]",playerLocation).replace("[playerName]",event.getPlayer().getName()));
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),c.replace("[playerWorld]",event.getPlayer().getLocation().getWorld().getName()).replace("[playerLocation]",playerLocation).replace("[playerName]",event.getPlayer().getName()));
        }
        if(DTech.AddBlocks.getBoolean(this.getId() + ".cannotPlace")){
            event.cancel();
        }
    }

}
