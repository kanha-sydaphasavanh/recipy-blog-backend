package fr.web.recipy.dto;


public class IngredientDto {
    private long id;
    private String ingredientName;

    public IngredientDto() {
    }

    public IngredientDto(long id, String ingredientName) {
        this.id = id;
        this.ingredientName = ingredientName;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}
