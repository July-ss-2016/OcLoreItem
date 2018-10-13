package vip.ourcraft.mcserverplugins.ocloreitem;

public class ItemStat {
    private ItemStatType type;
    private int level;

    public ItemStat(ItemStatType type, int level) {
        this.type = type;
        this.level = level;
    }

    public ItemStatType getType() {
        return type;
    }

    public void setType(ItemStatType type) {
        this.type = type;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
