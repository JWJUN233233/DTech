package dtech.dtech;

import dtech.dtech.commands.*;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class DTech extends JavaPlugin implements SlimefunAddon {
    public static FileConfiguration Saves;
    public static FileConfiguration AddBlocks;
    public static FileConfiguration language;
    public static FileConfiguration config;
    public FileConfiguration GetConfig(String ConfigName){
        File tmp = new File(DTech.getPlugin(DTech.class).getDataFolder(),ConfigName + ".yml");
        if(!tmp.exists()){
            try {
                saveResource(ConfigName + ".yml", false);
            } finally {
                try {
                    tmp.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return YamlConfiguration.loadConfiguration(tmp);
    }
    public static void SaveConfig(FileConfiguration configuration, String ConfigName){
        try {
            configuration.save(new File(DTech.getPlugin(DTech.class).getDataFolder(),ConfigName + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        DTech.getProvidingPlugin(DTech.class).getDataFolder().mkdir();
        config = DTech.getProvidingPlugin(DTech.class).getConfig();
        Saves = GetConfig("Saves");
        AddBlocks = GetConfig("Blocks");
        saveResource(config.getString("language") + ".yml", config.getBoolean("repice-language-file"));
        language = GetConfig(config.getString("language"));
        registerItem();
        getCommand("summon").setExecutor(new summon());
        getCommand("takeitem").setExecutor(new takeitem());
        getCommand("addblock").setExecutor(new addblock());
        getCommand("configblock").setExecutor(new configblock());
        getCommand("settexture").setExecutor(new settexture());
        System.out.println(dtech.dtech.language.getMessage("PluginLoad.Load"));
    }
    public void SaveConfig(){
        SaveConfig(Saves,"Saves");
        SaveConfig(AddBlocks,"Blocks");
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(dtech.dtech.language.getMessage("Save.Saving"));
        SaveConfig();
        System.out.println(dtech.dtech.language.getMessage("Save.Saved"));
        System.out.println(dtech.dtech.language.getMessage("PluginLoad.UnLoad"));
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nullable
    @Override
    public String getBugTrackerURL() {
        return null;
    }
    public void registerItem(){
        NamespacedKey categoryId = new NamespacedKey(this, "Dtech_add");

        CustomItemStack categoryItem = new CustomItemStack(Material.getMaterial(config.getString("category.Material")), dtech.dtech.language.getMessage("category.add.displayname"));
        ItemGroup additem = new ItemGroup(categoryId, categoryItem);
        Object[] blocks = AddBlocks.getKeys(false).toArray();
        for(int i = 0;i < blocks.length;i++){
            SlimefunItemStack itemStack;
            if(AddBlocks.getBoolean(blocks[i] + ".useTexture")){
                if(AddBlocks.getBoolean(blocks[i] + ".useLore")) {
                    itemStack = new SlimefunItemStack(AddBlocks.getString(blocks[i] + ".id"), AddBlocks.getString(blocks[i] + ".texture"), AddBlocks.getString(blocks[i] + ".name"), AddBlocks.getString(blocks[i] + ".lore"));
                }
                else{
                    itemStack = new SlimefunItemStack(AddBlocks.getString(blocks[i] + ".id"), AddBlocks.getString(blocks[i] + ".texture"), AddBlocks.getString(blocks[i] + ".name"));
                }
            }
            else {
                if(AddBlocks.getBoolean(blocks[i] + ".useLore")) {
                    itemStack = new SlimefunItemStack(AddBlocks.getString(blocks[i] + ".id"), Material.getMaterial(Objects.requireNonNull(AddBlocks.getString(blocks[i] + ".material"))), AddBlocks.getString(blocks[i] + ".name"), AddBlocks.getString(blocks[i] + ".lore"));
                }
                else{
                    itemStack = new SlimefunItemStack(AddBlocks.getString(blocks[i] + ".id"), Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(AddBlocks.getString(blocks[i] + ".material")))), AddBlocks.getString(blocks[i] + ".name"));
                }
            }
            // A 3x3 shape representing our recipe
            ItemStack[] recipe = {
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.1"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.2"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.3"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.4"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.5"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.6"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.7"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.8"))),
                    new ItemStack(Material.getMaterial(AddBlocks.getString(blocks[i] + ".recipe.9"))),
            };
            System.out.println(dtech.dtech.language.getMessage("block.loadMessage").replace("{0}",(String) blocks[i]).replace("{1}", Objects.requireNonNull(AddBlocks.getString(blocks[i] + ".id"))));
            SlimefunItem sfItem = new CustomItem(additem, itemStack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
            sfItem.register(this);
        }
    }
}
