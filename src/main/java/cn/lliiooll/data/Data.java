package cn.lliiooll.data;

import cn.lliiooll.GoodArms;
import cn.lliiooll.exception.ThrowException;
import org.bukkit.entity.Player;

import java.io.*;
import java.util.List;

public class Data {
    public List<String> getList() {
        return (List<String>) new GoodArms().ga.getConfig().getList("list");
    }

    public boolean has(String arg) {
        return this.getList().contains(arg);
    }

    public Programme get(String arg, Player player) {
        return new Programme(arg, player);
    }

    public String getFile(File file) {
        File f = file;
        if (f.exists()) {
            byte b[] = new byte[1024];
            int len = 0;
            int temp = 0;          //所有读取的内容都使用temp接收
            try {
                InputStream in = new FileInputStream(f);
                while ((temp = in.read()) != -1) {    //当没有读取完时，继续读取
                    b[len] = (byte) temp;
                    len++;
                }
                in.close();
            } catch (IOException e) {
                new ThrowException(e, "文件读取失败");
            }
            return new String(b, 0, len);
        } else {
            new GoodArms().error("找不到指定文件");
        }
        return null;
    }

    public void setFile(String text, File file) {
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                new ThrowException(e, "文件创建失败");
            }
        }
        try {
            //成功，则设置下面的量
            PrintStream ps = new PrintStream(new FileOutputStream(file));
            ps.println(text);//在这里写入文件
        } catch (FileNotFoundException e) {
            // TODO 自动生成的 catch 块
            //失败，抛错
            e.printStackTrace();
        }

    }


    public void getLore(List<String> lore) {

    }

    public List<String> getLore(List<String> lore, List<String> lore1) {
        String o[] = (String[]) lore.toArray();
        List<String> l11 = lore1;
        for (String oo : o) {
            l11.add(oo);
        }
        return l11;
    }

    public boolean isFirst(Player player) {
        return new File(new GoodArms().ga.getDataFolder(), "armsdata//" + player.getUniqueId().toString() + ".json").exists();
    }
}
