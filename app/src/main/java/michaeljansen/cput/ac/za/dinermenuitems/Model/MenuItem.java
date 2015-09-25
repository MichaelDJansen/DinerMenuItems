package michaeljansen.cput.ac.za.dinermenuitems.Model;

/**
 * Created by Michael on 21/09/2015.
 */
public class MenuItem {

    private int id;

    private String itemName;
    private String type;
    private String description;
    private float price;
    private String extras;

    protected MenuItem(){}

    public MenuItem(Builder builder)
    {
        this.id = builder.id;
        this.itemName = builder.itemName;
        this.type = builder.type;
        this.description = builder.description;
        this.price = builder.price;
        this.extras = builder.extras;
    }

    public static class Builder {
        private int id;
        private String itemName;
        private String type;
        private String description;
        private float price;
        private String extras;

        public Builder(int id,String itemName,String type,String description,float price) {
            this.id = id;
            this.itemName = itemName;
            this.type = type;
            this.description = description;
            this.price = price;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder itemName(String itemName) {
            this.itemName = itemName;
            return this;
        }
        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder price(float price) {
            this.price = price;
            return this;
        }

        public Builder extras(String extras) {
            this.extras = extras;
            return this;
        }
        public Builder copy(MenuItem menuItem) {
            this.id = menuItem.id;
            this.itemName = menuItem.itemName;
            this.type = menuItem.type;
            this.description = menuItem.description;
            this.price = menuItem.price;
            this.extras = menuItem.extras;
            return this;
        }

        public MenuItem build() {
            return new MenuItem(this);
        }
    }


    public int getMenuItemId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getExtras() {
        return extras;
    }



}
