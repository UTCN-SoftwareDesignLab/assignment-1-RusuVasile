package model;

public class Client  {
    private Long id;

    private String name;
    private String address;
    private Long cnp;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Long getCnp() {
        return cnp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCnp(Long cnp) {
        this.cnp = cnp;
    }
}
