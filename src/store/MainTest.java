//package store;
//
//import store.cart.Cart;
//import store.core.Customer;
//import store.engine.StoreEngine;
//import store.products.BookProduct;
//import store.products.Category;
//import store.products.ElectronicsProduct;
//import store.products.Category;
//import java.awt.Color;
//import store.products.ClothingProduct;
//import java.util.ArrayList;
//
//public class MainTest {
//
//    public static void main(String[] args) {

//        BookProduct b = new BookProduct(
//                "Clean Code",
//                99.90,
//                10,
//                "Software engineering best practices",
//                Category.BOOKS,
//                Color.BLUE,
//                "Robert C. Martin",
//                464
//        );
//        // בדיקת toString()
//        System.out.println("=== Testing toString() ===");
//        System.out.println(b.toString());
//        System.out.println();
//
//        // בדיקת getDisplayDetails()
//        System.out.println("=== Testing getDisplayDetails() ===");
//        System.out.println(b.getDisplayDetails());
//        System.out.println();
//
//        // בדיקת פונקציות ירושות מה־Product (אם יש)
//        System.out.println("=== Testing inherited getters ===");
//        System.out.println("Price: " + b.getPrice());
//        System.out.println("Stock: " + b.getStock());


//        // יצירת אובייקט לבדיקה
//        ElectronicsProduct ep = new ElectronicsProduct(
//                "iPhone 15",
//                3999.90,
//                12,
//                "Latest Apple smartphone",
//                Category.ELECTRONICS,
//                Color.BLACK,
//                24,
//                "Apple"
//        );
//
//        // בדיקת toString()
//        System.out.println("=== Testing toString() ===");
//        System.out.println(ep.toString());
//        System.out.println();
//
//        // בדיקת getDisplayDetails()
//        System.out.println("=== Testing getDisplayDetails() ===");
//        System.out.println(ep.getDisplayDetails());
//        System.out.println();
//    }
        // יצירת אובייקט לבדיקה
//        ClothingProduct cp = new ClothingProduct(
//                "T-Shirt",
//                59.90,
//                20,
//                "Cotton unisex t-shirt",
//                Category.CLOTHING,
//                Color.RED,
//                "M"
//        );
//
//        // בדיקת toString()
//        System.out.println("=== Testing toString() ===");
//        System.out.println(cp.toString());
//        System.out.println();
//
//        // בדיקת getDisplayDetails()
//        System.out.println("=== Testing getDisplayDetails() ===");
//        System.out.println(cp.getDisplayDetails());
//        System.out.println();
//    }




//        System.out.println("=== Creating product ===");
//        ClothingProduct p = new ClothingProduct(
//                "T-Shirt",
//                50.0,
//                10,
//                "Cotton shirt",
//                Category.CLOTHING,
//                Color.BLUE,
//                "M"
//        );
//
//        // --- getPrice ---
//        System.out.println("\n=== Testing getPrice() ===");
//        System.out.println("Price: " + p.getPrice());
//
//        // --- setPrice ---
//        System.out.println("\n=== Testing setPrice() ===");
//        System.out.println("Set to 80: " + p.setPrice(80));
//        System.out.println("New price: " + p.getPrice());
//        System.out.println("Set to -5 (should fail): " + p.setPrice(-5));
//        System.out.println("Price after fail: " + p.getPrice());
//
//        // --- getStock ---
//        System.out.println("\n=== Testing getStock() ===");
//        System.out.println("Stock: " + p.getStock());
//
//        // --- increaseStock ---
//        System.out.println("\n=== Testing increaseStock() ===");
//        System.out.println("Increase by 5: " + p.increaseStock(5));
//        System.out.println("Stock after increase: " + p.getStock());
//        System.out.println("Increase by -1 (should fail): " + p.increaseStock(-1));
//
//        // --- decreaseStock ---
//        System.out.println("\n=== Testing decreaseStock() ===");
//        System.out.println("Decrease by 3: " + p.decreaseStock(3));
//        System.out.println("Stock after decrease: " + p.getStock());
//        System.out.println("Decrease by 100 (should fail): " + p.decreaseStock(100));
//
//        // --- getDisplayName ---
//        System.out.println("\n=== Testing getDisplayName() ===");
//        System.out.println("Display Name: " + p.getDisplayName());
//
//        // --- getDisplayDetails ---
//        System.out.println("\n=== Testing getDisplayDetails() ===");
//        System.out.println(p.getDisplayDetails());
//
//        // --- toString ---
//        System.out.println("\n=== Testing toString() ===");
//        System.out.println(p.toString());
//
//        // --- equals ---
//        System.out.println("\n=== Testing equals() ===");
//        ClothingProduct p2 = new ClothingProduct(
//                "T-Shirt",
//                100,
//                20,
//                "Another desc",
//                Category.CLOTHING,
//                Color.RED,
//                "L"
//        );
//
//        ClothingProduct p3 = new ClothingProduct(
//                "Jeans",
//                120,
//                15,
//                "Pants",
//                Category.CLOTHING,
//                Color.BLACK,
//                "M"
//        );
//
//        System.out.println("p.equals(p2) (should be true): " + p.equals(p2));
//        System.out.println("p.equals(p3) (should be false): " + p.equals(p3));
//    }

//        StoreEngine engine = new StoreEngine();
//        Cart cart = new Cart();
//
//        // Create products
//        ClothingProduct p1 = new ClothingProduct(
//                "T-Shirt", 50.0, 10,
//                "Cotton shirt", Category.CLOTHING,
//                Color.BLUE, "M"
//        );
//
//        ClothingProduct p2 = new ClothingProduct(
//                "Jeans", 120.0, 5,
//                "Blue jeans", Category.CLOTHING,
//                Color.BLACK, "L"
//        );
//
//        // Add products to store
//        engine.addProduct(p1);
//        engine.addProduct(p2);
//
//        // Create customer
//        Customer c = new Customer(
//                "avi", "avi@mail.com",
//                cart, new ArrayList<>(), engine
//        );
//
//        System.out.println("=== TEST 1: addToCart ===");
//        System.out.println("Add 2 T-Shirts: " + c.addToCart(p1, 2));
//        System.out.println("Add 1 Jeans: " + c.addToCart(p2, 1));
//        System.out.println();
//
//        System.out.println("=== TEST 2: removeFromCart ===");
//        System.out.println("Remove Jeans: " + c.removeFromCart(p2));
//        System.out.println();
//
//        System.out.println("=== TEST 3: checkout ===");
//        System.out.println("Checkout: " + c.checkout());
//        System.out.println();
//
//        System.out.println("=== TEST 4: Order details ===");
//
//    }





//}





