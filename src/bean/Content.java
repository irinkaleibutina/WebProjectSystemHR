package bean;

import java.io.Serializable;

public class Content implements Serializable{
    private int newsId;
    private String newsTitle;
    private String newsDescription;

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (newsId != content.newsId) return false;
        if (!newsTitle.equals(content.newsTitle)) return false;
        return newsDescription.equals(content.newsDescription);
    }

    @Override
    public int hashCode() {
        int result = newsId;
        result = 31 * result + newsTitle.hashCode();
        result = 31 * result + newsDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "newsId=" + newsId +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsDescription='" + newsDescription + '\'' +
                '}';
    }
}
