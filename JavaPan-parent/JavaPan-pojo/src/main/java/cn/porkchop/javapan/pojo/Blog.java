package cn.porkchop.javapan.pojo;

import java.util.Date;
import java.util.List;

public class Blog extends TBlog {
    private Long blogCount;
    private String releaseMonth;
    private List<String> imageList;
    private String blogTypeName;
    private List<String> keywordList;

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    private Long commentCount;

    public List<String> getKeywordList() {
        return keywordList;
    }

    public void setKeywordList(List<String> keywordList) {
        this.keywordList = keywordList;
    }


    public String getBlogTypeName() {
        return blogTypeName;
    }

    public void setBlogTypeName(String blogTypeName) {
        this.blogTypeName = blogTypeName;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public String getReleaseMonth() {
        return releaseMonth;
    }

    public void setReleaseMonth(String releaseMonth) {
        this.releaseMonth = releaseMonth;
    }


    public Long getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Long blogCount) {
        this.blogCount = blogCount;
    }

    public Blog() {
    }

    public Blog(Long id, String title, String summary, Date releaseDate, Long clickCount, Integer typeId, String keyword, String content, Long blogCount, String releaseMonth, List<String> imageList) {
        setId(id);
        setTitle(title);
        setSummary(summary);
        setReleaseDate(releaseDate);
        setClickCount(clickCount);
        setTypeId(typeId);
        setKeyword(keyword);
        setContent(content);
        this.blogCount = blogCount;
        this.releaseMonth = releaseMonth;
        this.imageList = imageList;
    }
}