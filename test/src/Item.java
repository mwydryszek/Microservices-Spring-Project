import java.lang.annotation.Documented;

public class Item {

    private final String name;
    private final String lore;

    static ItemBuilder builder(){
        return new ItemBuilder();
    }

    private Item(String name, String lore) {
        this.name = name;
        this.lore= lore;
    }


    static class ItemBuilder {

        private String name;
        private String lore;

        ItemBuilder name(String name) {
            this.name = name;
            return this;
        }

        ItemBuilder lore(String lore) {
            this.lore = lore;
            return this;
        }

        Item build(){
            return new Item(name, lore);
        }
    }


}
