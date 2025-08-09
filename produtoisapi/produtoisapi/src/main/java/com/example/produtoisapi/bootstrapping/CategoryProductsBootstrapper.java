package com.example.produtoisapi.bootstrapping;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import com.example.produtoisapi.userManagement.services.FileStorageProperties;
import com.example.produtoisapi.userManagement.services.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.example.produtoisapi.productManagement.model.Category;
import com.example.produtoisapi.productManagement.model.Product;
import com.example.produtoisapi.productManagement.repositories.CategoryRepository;
import com.example.produtoisapi.productManagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Component
@RequiredArgsConstructor
@Profile("bootstrap")
public class CategoryProductsBootstrapper implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final FileStorageProperties fileStorageProperties;

    private final String BASE_IMAGE_URL = "http://localhost:8080/api/product/photo/";

    @Override
    public void run(String... args) throws Exception {

        copyImageToStorage("agua.png");
        copyImageToStorage("wine.png");
        copyImageToStorage("beef.png");
        copyImageToStorage("lamb.png");
        copyImageToStorage("apple.png");
        copyImageToStorage("orange.png");

        if (categoryRepository.findByName("DRINKS").isEmpty()) {
            final Category c1 = new Category("DRINKS");
            categoryRepository.save(c1);

            if (productRepository.findByName("Água").isEmpty()) {
                final Product product1 = new Product("Água", 1.10, 100, c1);
                product1.setDescription("H2O");
                product1.setImageURL(BASE_IMAGE_URL + "agua.png");
                productRepository.save(product1);
            }

            if (productRepository.findByName("Wine").isEmpty()) {
                final Product product11 = new Product("Wine", 1.50, 50, c1);
                product11.setDescription("Drunk bastard");
                product11.setImageURL(BASE_IMAGE_URL + "wine.png");
                productRepository.save(product11);
            }
        }

        if (categoryRepository.findByName("MEAT").isEmpty()) {
            final Category c2 = new Category("MEAT");
            categoryRepository.save(c2);

            if (productRepository.findByName("Beef").isEmpty()) {
                final Product product2 = new Product("Beef", 5.00, 20, c2);
                product2.setDescription("It is beef, dude");
                product2.setImageURL(BASE_IMAGE_URL + "beef.png");
                productRepository.save(product2);
            }

            if (productRepository.findByName("Lamb").isEmpty()) {
                final Product product22 = new Product("Lamb", 7.00, 25, c2);
                product22.setDescription("Is lamb, man");
                product22.setImageURL(BASE_IMAGE_URL + "lamb.png");
                productRepository.save(product22);
            }
        }

        if (categoryRepository.findByName("FRUITS").isEmpty()) {
            final Category c3 = new Category("FRUITS");
            categoryRepository.save(c3);

            if (productRepository.findByName("Apple").isEmpty()) {
                final Product product3 = new Product("Apple", 5.00, 70, c3);
                product3.setDescription("You know what the doctors say, right?");
                product3.setImageURL(BASE_IMAGE_URL + "apple.png");
                productRepository.save(product3);
            }

            if (productRepository.findByName("Orange").isEmpty()) {
                final Product product33 = new Product("Orange", 7.00, 40, c3);
                product33.setDescription("Vitamin-C");
                product33.setImageURL(BASE_IMAGE_URL + "orange.png");
                productRepository.save(product33);
            }
        }
    }

    private void copyImageToStorage(String filename) {
        try {
            // Caminho para onde o serviço de ficheiros guarda as imagens (geralmente "uploads/")
            Path targetLocation = Path.of(fileStorageProperties.getUploadDir()).resolve(filename);

            if (Files.notExists(targetLocation)) {
                // Caminho da imagem no resources
                ClassPathResource resource = new ClassPathResource("imagesProducts/" + filename);
                InputStream inputStream = resource.getInputStream();

                // Copiar imagem para o diretório de upload
                Files.copy(inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao copiar imagem " + filename, e);
        }
    }
}
