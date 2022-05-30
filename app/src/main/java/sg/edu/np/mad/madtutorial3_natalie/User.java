package sg.edu.np.mad.madtutorial3_natalie;

public class User {
    private String name;
    private String description;
    private Integer id;
    private Boolean followed;

    public User(String Name, String Description, Integer Id, Boolean Followed) {
        name = Name;
        description = Description;
        id = Id;
        followed = Followed;
    }
    public User(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }
}
