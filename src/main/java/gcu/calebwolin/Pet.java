package gcu.calebwolin;
public class Pet 
{
    private int Id;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
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
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    @Override
    public String toString() {
        return "Pet [Id=" + Id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", categoryId=" + categoryId + "]";
    }

    

}
