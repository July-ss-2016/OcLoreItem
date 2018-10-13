package vip.ourcraft.mcserverplugins.ocloreitem;

public enum ItemStatType {
    EXP_ADDITION;

    private String chineseName;

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        if (chineseName == null) {
            throw new IllegalArgumentException("chinese name can not be null");
        }

        this.chineseName = chineseName;
    }
}
