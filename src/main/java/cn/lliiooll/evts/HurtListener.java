package cn.lliiooll.evts;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class HurtListener implements Listener {

    @EventHandler
    public void onEntityHurt(EntityDamageByEntityEvent e){
        Entity e1 = e.getDamager();
        if (e1.equals(EntityType.PLAYER)){
            Player p = (Player) e1;
            switch (p.getItemInHand().getType()){
                //钻石剑
                case DIAMOND_SWORD:{
                    break;
                }
                //石头剑
                case STONE_SWORD:{
                    break;
                }
                //木头剑
                case WOOD_SWORD:{
                    break;
                }
                //金剑
                case GOLD_SWORD:{
                    break;
                }
                //铁剑
                case IRON_SWORD:{
                    break;
                }
                default:{
                    break;
                }
            }

        }
    }
}
