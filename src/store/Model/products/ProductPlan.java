/**
 * Submitted by:
 * Maayan Gueta – ID 327554143
 * Avishag Almakaies – ID 325684678
 */
package store.Model.products;

public interface ProductPlan {
    public void setName(String name);
    public boolean setPrice(double price);
    public void setStock(int stock);
    public void setDescription(String description);
    public void setCategory(Category category);
    public void setColor(java.awt.Color color);
    public void  setImagePath(String imagePath);

}
