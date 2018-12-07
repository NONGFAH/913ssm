package base.enums;

import java.util.HashMap;
import java.util.Map;

public enum User {
    SUCCESS(0,"成功"),
    fin(0,"成功");





    private Integer id;
    private String desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    User(Integer id, String desc) {
        this.id = id;
        this.desc = desc;
    }

    private static Map<Integer, User> idMap = new HashMap<Integer, User>();

    static {
        for (User enums : User.values()) {
            idMap.put(enums.getId(), enums);
        }
    }

    /**
     * getDesc()查找描述
     *
     * @param id
     * @return
     */
    public static User valueOf(Integer id) {
        return idMap.get(id);
    }

    public static Map<Integer, User> getIdMap() {
        return idMap;
    }

}
