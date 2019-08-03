package cn.lliiooll.data;

import cn.lliiooll.GoodArms;
import cn.lliiooll.exception.ThrowException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class Arms implements ArmsInterface {


    private final Player p;
    private final Programme programme;
    private JSONObject json;

    public Arms(Player player, Programme programme) {
        this.p = player;
        this.programme = programme;
        File f = new File(new GoodArms().ga.getDataFolder(), "arms_data.//" + p.getUniqueId().toString() + ".json");
        if (!(new Data().isFirst(p))) {
            JSONObject jj = JSON.parseObject(new Data().getFile(f));
            this.json = jj;
        } else {
            this.create(f);
            this.json = JSON.parseObject(new Data().getFile(f));
        }

    }

    @Override
    public Player getPlayer() {
        return this.p;
    }

    @Override
    public int getHurt() {
        return 0;
    }

    @Override
    public int getCrit() {
        return 0;
    }

    @Override
    public int getCritEffect() {
        return 0;
    }

    @Override
    public int getResist() {
        return 0;
    }

    @Override
    public String getSign() {
        return null;
    }

    @Override
    public boolean hasSign() {
        return false;
    }

    @Override
    public String createSign() {
        return null;
    }

    @Override
    public void create(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
                JSONObject j = new JSONObject();
                JSONObject j1 = new JSONObject();
                JSONArray ja = new JSONArray();
                j.put("name", this.p.getName());
                j1.put("sign", this.createSign());
                j1.put("hurt", this.programme.getFirstHurt());
                j1.put("crit", this.programme.getFirstCrit());
                j1.put("critEffect", this.programme.getFirstCritEffect());
                j1.put("resist", this.programme.getFirstResist());
                ja.add(j1);
                j.put("list", ja);
                new Data().setFile(j.toJSONString(), file);
            } else {
                if (JSON.parseObject(new Data().getFile(file)) == null) {
                    JSONObject j = new JSONObject();
                    JSONObject j1 = new JSONObject();
                    JSONArray ja = new JSONArray();
                    j.put("name", this.p.getName());
                    j1.put("sign", this.createSign());
                    j1.put("hurt", this.programme.getFirstHurt());
                    j1.put("crit", this.programme.getFirstCrit());
                    j1.put("critEffect", this.programme.getFirstCritEffect());
                    j1.put("resist", this.programme.getFirstResist());
                    ja.add(j1);
                    j.put("list", ja);
                    new Data().setFile(j.toJSONString(), file);
                } else {
                    JSONObject j = JSON.parseObject(new Data().getFile(file));
                    JSONObject j1 = new JSONObject();
                    JSONArray ja = j.getJSONArray("list");
                    j.put("name", this.p.getName());
                    j1.put("sign", this.createSign());
                    j1.put("hurt", this.programme.getFirstHurt());
                    j1.put("crit", this.programme.getFirstCrit());
                    j1.put("critEffect", this.programme.getFirstCritEffect());
                    j1.put("resist", this.programme.getFirstResist());
                    ja.add(j1);
                    j.put("list", ja);
                    new Data().setFile(j.toJSONString(), file);
                }
            }
        } catch (IOException e) {
            new ThrowException(e, "文件创建失败");
        }
    }
}
