package cn.lliiooll.cmds;

import cn.lliiooll.GoodArms;
import cn.lliiooll.data.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MainCommand implements CommandExecutor {
    GoodArms ga = new GoodArms();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        GoodArms ga = new GoodArms();
        if (args.length <= 0) {
            // 当子指令为空的时候
            if (sender instanceof Player) {
                ga.info(this.getHelpList(), (Player) sender);
                return true;
            }
            ga.info(this.getHelps());
            return true;
        }
        String l = "list";
        if (l.equalsIgnoreCase(args[0])) {
            String[] list = (String[]) new Data().getList().toArray();
            if (sender instanceof Player) {
                ga.info(list, (Player) sender);
                return true;
            }
            ga.info(list);
            return true;
        }
        Data d = new Data();
        if (d.has(args[0])) {
            if (sender instanceof Player) {
                Player p = (Player) sender;
                ItemStack item = p.getItemInHand();
                switch (item.getType()) {
                    //钻石剑
                    case DIAMOND_SWORD: {
                        ItemMeta im = item.getItemMeta();
                        im.setLore(d.getLore(item.getItemMeta().getLore(),d.get(args[0],p).getLore()));
                        item.setItemMeta(im);
                        return true;
                    }
                    //石头剑
                    case STONE_SWORD: {
                        ItemMeta im = item.getItemMeta();
                        im.setLore(d.getLore(item.getItemMeta().getLore(),d.get(args[0],p).getLore()));
                        item.setItemMeta(im);
                        return true;
                    }
                    //木头剑
                    case WOOD_SWORD: {
                        ItemMeta im = item.getItemMeta();
                        im.setLore(d.getLore(item.getItemMeta().getLore(),d.get(args[0],p).getLore()));
                        item.setItemMeta(im);
                        return true;
                    }
                    //金剑
                    case GOLD_SWORD: {
                        ItemMeta im = item.getItemMeta();
                        im.setLore(d.getLore(item.getItemMeta().getLore(),d.get(args[0],p).getLore()));
                        item.setItemMeta(im);
                        return true;
                    }
                    //铁剑
                    case IRON_SWORD: {
                        ItemMeta im = item.getItemMeta();
                        im.setLore(d.getLore(item.getItemMeta().getLore(),d.get(args[0],p).getLore()));
                        item.setItemMeta(im);
                        return true;
                    }
                    default: {
                        ga.info("你的手中没有拿着任何一把剑", p);
                        return true;
                    }
                }
            } else {
                ga.info("控制台不可使用");
            }
            return true;
        }
        ga.info("未知强化方案或子指令，请输入/qh查看帮助。");
        return false;
    }

    public static String[] getHelpList() {
        String[] a = new String[]{
                "&e/qh                   &7=&b>&2插件帮助",
                "&e/qh &3list              &7=&b>&2查看可用的强化",
                "&e/qh &3[名称]            &7=&b>&2使用这个强化",
                "&e/qh &3[名称] &5update     &7=&b>&2升级这个强化"};
        return a;
    }
    public String getHelps(){
        String a=MainCommand.getHelpList()[1]+MainCommand.getHelpList()[2]+MainCommand.getHelpList()[3]+MainCommand.getHelpList()[4];
        return a;
    }
}
