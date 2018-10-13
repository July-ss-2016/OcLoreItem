package vip.ourcraft.mcserverplugins.ocloreitem;

import java.util.HashMap;
import java.util.List;

public class Settings {
    private String myItemsStatsFormatValue;
    private String statsIdentifierExp;
    private HashMap<String, List<String>> particles = new HashMap<>();


    public String getMyItemsStatsFormatValue() {
        return myItemsStatsFormatValue;
    }

    public void setMyItemsStatsFormatValue(String myItemsStatsFormatValue) {
        this.myItemsStatsFormatValue = myItemsStatsFormatValue;
    }

    public String getStatsIdentifierExp() {
        return statsIdentifierExp;
    }

    public void setStatsIdentifierExp(String statsIdentifierExp) {
        this.statsIdentifierExp = statsIdentifierExp;
    }

    public HashMap<String, List<String>> getParticles() {
        return particles;
    }

    public void addParticle(String particleName, List<String> itemCode) {
        particles.put(particleName, itemCode);
    }
}
