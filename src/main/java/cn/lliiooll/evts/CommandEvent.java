package cn.lliiooll.evts;

import org.apache.commons.lang.StringUtils;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.Listener;
import cn.lliiooll.*;
import cn.lliiooll.cmds.*;
import org.bukkit.event.EventHandler;

public class CommandEvent implements Listener{
    GoodArms ga = new GoodArms();
    @EventHandler
    public void CommandProcess(PlayerCommandPreprocessEvent event){
        if(StringUtils.equals(event.getMessage(),"/GoodArms help for using")) {
            event.getPlayer().sendMessage("Next I will tell you how for using");
            ga.info(MainCommand.getHelpList()[1]+MainCommand.getHelpList()[2]+MainCommand.getHelpList()[3]+MainCommand.getHelpList()[4]);
        }
        if(StringUtils.equals(event.getMessage(),"/GoodArms authors")) {
            event.getPlayer().sendMessage("Authors: lliiooll with zycdojar");
        }
    }
}