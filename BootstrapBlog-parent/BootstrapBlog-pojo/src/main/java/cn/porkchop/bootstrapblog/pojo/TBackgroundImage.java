package cn.porkchop.bootstrapblog.pojo;

public class TBackgroundImage {
    private Integer id;

    private String imageCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode == null ? null : imageCode.trim();
    }
}