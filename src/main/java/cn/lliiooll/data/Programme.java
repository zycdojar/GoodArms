package cn.lliiooll.data;

import cn.lliiooll.GoodArms;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Programme implements ProgrammeInterface {

    private final JSONObject json;
    private final Player player;

    public Programme(String arg, Player player) {
        File file = new File(new GoodArms().ga.getDataFolder(), arg + ".json");
        this.json = JSON.parseObject(new Data().getFile(file));
        this.player = player;
    }

    @Override
    public String getName() {
        return json.getString("name");
    }

    @Override
    public int getMaxLevel() {
        return json.getIntValue("level.max");
    }

    @Override
    public List<String> getLore() {
        JSONArray ja = json.getJSONArray("lore");
        String a[] = (String[]) ja.toArray();
        List<String> l = new ArrayList<>();
        Arms arms = new Arms(player,this);
        for (String m : a) {
            m = m.replace("*hurt*",arms.getHurt()+"");
            m = m.replace("*crit*",arms.getHurt()+"");
            m = m.replace("*critEffect*",arms.getHurt()+"");
            m = m.replace("*resist*",arms.getHurt()+"");
            l.add(m);
        }
        return l;
    }

    @Override
    public int getFirstHurt() {
        return json.getIntValue("first.hurt");
    }

    @Override
    public int getFirstCrit() {
        return json.getIntValue("first.crit");
    }

    @Override
    public int getFirstCritEffect() {
        return json.getIntValue("first.critEffect");
    }

    @Override
    public int getFirstResist() {
        return json.getIntValue("first.resist");
    }
}
