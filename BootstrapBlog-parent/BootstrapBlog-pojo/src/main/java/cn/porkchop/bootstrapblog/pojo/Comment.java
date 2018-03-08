package cn.porkchop.bootstrapblog.pojo;

public class Comment extends TComment{
    private Long blogId;
    private String blogTitle;

    @Override
    public Long getBlogId() {
        return blogId;
    }

    @Override
    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public String getBlogTitle() {
        return blogTitle;
    }

    public void setBlogTitle(String blogTitle) {
        this.blogTitle = blogTitle;
    }
}
