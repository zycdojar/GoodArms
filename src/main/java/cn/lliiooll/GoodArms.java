package cn.lliiooll;

import cn.lliiooll.cmds.MainCommand;
import cn.lliiooll.evts.*;
import cn.lliiooll.exception.ThrowException;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;


/**
 * @author lliiooll
 */
public class GoodArms extends JavaPlugin {
    public static void api_message(String info){
        Bukkit.broadcastMessage(info);
    }

    private String pre = "§c§l[§bGoodArms§7/*LEVEL*§c§l]§f ";
    public GoodArms ga;
    private boolean vaultEnable;
    public boolean debug = false;

    @Override
    public void onLoad() {

        ga = this;
        this.pre = this.getConfig().getString("prefix").replace("&", "§");
        getServer().getLogger().info("Loading...");

    }

    @Override
    public void onEnable() {
        // 连接Vault
        this.setupVault();
        // 检查配置
        this.checkConfig("config.yml");
        // 注册事件
        this.registerEvents();
        // 注册指令
        this.registerCommands();
        // 加载完毕
        info("插件加载完毕！");
    }

    @Override
    public void onDisable() {
        getServer().getLogger().info("This plugin is disabled");
    }

    private void registerCommands() {
        Bukkit.getPluginCommand("goodarms").setExecutor(new MainCommand());
        info("指令注册完毕！");
    }

    private void registerEvents() {
        Bukkit.getPluginManager().registerEvents(new HurtListener(),this);
        Bukkit.getPluginManager().registerEvents(new CommandEvent(),this);
        info("事件注册完毕！");
    }

    private static Economy econ = null;

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }


    private void checkConfig(String fileName) {
        File dir = this.getDataFolder();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File configFile = new File(dir, fileName);
        if (!configFile.exists()) {
            warn("配置文件 &7" + fileName + "&e 不存在。");
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                new ThrowException(e, "配置文件 &7" + fileName + "&c 创建失败。");
            }
        } else {
            info("存在配置文件 &7" + fileName);
        }
    }

    private void setupVault() {
        if (!Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            warn("未安装Vault,无法进行金钱扣除.");
            this.vaultEnable = false;
        } else {
            info("连接Vault......");
            if (!setupEconomy()) {
                error("连接错误！请检查您的Vault是否正常启用。");
                this.vaultEnable = false;
            } else {
                info("连接成功！");
                this.vaultEnable = true;
            }
        }
    }

    public Economy getEconomy() {
        return econ;
    }

    public void info(Object msg) {
        Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§2信息") + "§b" + msg.toString().replace("&", "§"));
    }

    public void info(Object[] msg) {
        for (Object o:msg){
            Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§2信息") + "§b" + o.toString().replace("&", "§"));
        }
    }

    public void info(String msg, Player player) {
        player.sendMessage(this.pre.replace("*LEVEL*", "§2信息") + "§b" + msg.toString().replace("&", "§"));
    }

    public void info(String[] msg, Player player) {
        for (String m:msg) {
            player.sendMessage(this.pre.replace("*LEVEL*", "§2信息") + "§b" + m.replace("&", "§"));
        }
    }

    public void warn(Object msg) {
        Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§e警告") + "§b" + msg.toString().replace("&", "§"));
    }

    public void warn(Object[] msg) {
        for (Object o:msg){
            Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§e警告") + "§b" + o.toString().replace("&", "§"));
        }
    }

    public void warn(String msg, Player player) {
        player.sendMessage(this.pre.replace("*LEVEL*", "§e警告") + "§b" + msg.toString().replace("&", "§"));
    }

    public void warn(String[] msg, Player player) {
        for (String m:msg) {
            player.sendMessage(this.pre.replace("*LEVEL*", "§e警告") + "§b" + m.replace("&", "§"));
        }
    }

    public void error(Object msg) {
        Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§c错误") + "§b" + msg.toString().replace("&", "§"));
    }

    public void error(Object[] msg) {
        for (Object o:msg){
            Bukkit.getConsoleSender().sendMessage(this.pre.replace("*LEVEL*", "§c错误") + "§b" + o.toString().replace("&", "§"));
        }
    }

    public void error(String msg, Player player) {
        player.sendMessage(this.pre.replace("*LEVEL*", "§c错误") + "§b" + msg.toString().replace("&", "§"));
    }

    public void error(String[] msg, Player player) {
        for (String m:msg) {
            player.sendMessage(this.pre.replace("*LEVEL*", "§c错误") + "§b" + m.replace("&", "§"));
        }
    }
}
