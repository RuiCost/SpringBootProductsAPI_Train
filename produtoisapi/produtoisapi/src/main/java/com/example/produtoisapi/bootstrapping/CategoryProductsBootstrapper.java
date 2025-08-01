package com.example.produtoisapi.bootstrapping;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("bootstrap")
public class CategoryProductsBootstrapper implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.findByName("DRINKS").isEmpty()) {
            final Category c1 = new Category("DRINKS");
            categoryRepository.save(c1);
            if (productRepository.findByName("Água").isEmpty()) {
                final Product product1 = new Product("Água", 1.10, c1);
                product1.setDescription("H2O");
                productRepository.save(product1);
            }

            if (productRepository.findByName("Wine").isEmpty()) {
                final Product product11 = new Product("Wine", 1.50, c1);
                product11.setDescription("Drunk bastard");
                productRepository.save(product11);
            }
        }

        if (categoryRepository.findByName("MEAT").isEmpty()) {
            final Category c2 = new Category("MEAT");
            categoryRepository.save(c2);
            if (productRepository.findByName("Beef").isEmpty()) {
                final Product product2 = new Product("Beef", 5.00, c2);
                product2.setDescription("It is beef, dude");
                productRepository.save(product2);
            }

            if (productRepository.findByName("Lamb").isEmpty()) {
                final Product product22 = new Product("Lamb", 7.00, c2);
                product22.setDescription("Is lamb, man");
                productRepository.save(product22);
            }
        }
        if (categoryRepository.findByName("FRUITS").isEmpty()) {
            final Category c3 = new Category("FRUITS");
            categoryRepository.save(c3);
            if (productRepository.findByName("Apple").isEmpty()) {
                final Product product3 = new Product("Apple", 5.00, c3);
                product3.setDescription("You know what the doctors say, right?");
                productRepository.save(product3);
            }

            if (productRepository.findByName("Orange").isEmpty()) {
                final Product product33 = new Product("Orange", 7.00, c3);
                product33.setDescription("Vitamin-C");
                productRepository.save(product33);
            }
        }
    }


}
