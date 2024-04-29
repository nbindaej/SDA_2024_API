package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PetStorePojo {
    private Integer id;
    private PetCategoryPojo category;
    private String name;
    private List<String> photoUrls;
    private List<PetTagsPojo> tags;
    private String status;

    public PetStorePojo() {
    }

    public PetStorePojo(Integer id, PetCategoryPojo category, String name, List<String> photoUrls, List<PetTagsPojo> tags, String status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PetCategoryPojo getCategory() {
        return category;
    }

    public void setCategory(PetCategoryPojo category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public List<PetTagsPojo> getTags() {
        return tags;
    }

    public void setTags(List<PetTagsPojo> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}