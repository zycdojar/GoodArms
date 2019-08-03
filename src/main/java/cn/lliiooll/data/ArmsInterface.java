package cn.lliiooll.data;

import org.bukkit.entity.Player;

import java.io.File;

public interface ArmsInterface {

    public Player getPlayer();

    public int getHurt();

    public int getCrit();

    public int getCritEffect();

    public int getResist();

    public String getSign();

    public boolean hasSign();

    String createSign();

    public void create(File file);
}
