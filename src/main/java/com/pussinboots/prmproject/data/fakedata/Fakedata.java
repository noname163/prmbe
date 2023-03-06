package com.pussinboots.prmproject.data.fakedata;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import com.pussinboots.prmproject.data.repositories.ProductRepository;
import com.pussinboots.prmproject.data.repositories.CategoryRepository;
import com.pussinboots.prmproject.data.repositories.BrandRepository;
import com.pussinboots.prmproject.data.entities.*;

@Configuration
public class Fakedata {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository,
            CategoryRepository categoryRepository, BrandRepository branchRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {

                List<Brand> brands = new ArrayList<>();
                brands.add(Brand.builder().brandName("Samsung").build());
                brands.add(Brand.builder().brandName("Sony").build());
                brands.add(Brand.builder().brandName("Apple").build());
                brands.add(Brand.builder().brandName("LG").build());
                brands.add(Brand.builder().brandName("Panasonic").build());
                brands.add(Brand.builder().brandName("Microsoft").build());
                brands.add(Brand.builder().brandName("Philips").build());
                brands.add(Brand.builder().brandName("HP").build());
                brands.add(Brand.builder().brandName("Lenovo").build());
                brands.add(Brand.builder().brandName("Dell").build());

                List<Category> categories = new ArrayList<>();
                categories.add(Category.builder()
                .name("Computers")
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925288/computer_rjqrao.png")
                .build());
                categories.add(Category.builder()
                .name("Smartphones")
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925419/smartphone_ywdq3z.jpg")
                .build());
                categories.add(Category.builder()
                .name("Tablets")
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925497/tablet_mpjrul.jpg")
                .build());
                categories.add(Category.builder()
                .name("Televisions")
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925577/tv_igtg87.jpg")
                .build());
                categories.add(Category.builder()
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925770/headphone_twopyo.jpg")
                .name("Headphone")
                .build());
                categories.add(Category.builder()
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677925965/camera_zhmnbm.jpg")
                .name("Cameras")
                .build());
                categories.add(Category.builder()
                .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1677926069/keyboard_fv5hk5.jpg")
                .name("Keyboard").build());

                List<Product> products = new ArrayList<>();
                products.add(Product.builder()
                .name("Iphone 8 plush")
                .brand(brands.get(2))
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .category(categories.get(1))
                .price(4200000.0)
                .modelYear(2019)
                .build());
                products.add(Product.builder()
                .name("Iphone 7 plush")
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .brand(brands.get(2))
                .category(categories.get(1))
                .price(3200000.0)
                .modelYear(2018)
                .build());
                products.add(Product.builder()
                .name("Iphone 6 plush")
                .brand(brands.get(2))
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .category(categories.get(1))
                .price(2200000.0)
                .modelYear(2017)
                .build());
                products.add(Product.builder()
                .name("Iphone 5")
                .brand(brands.get(2))
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .category(categories.get(1))
                .price(1500000.0)
                .modelYear(2016)
                .build());
                products.add(Product.builder()
                .name("Iphone 4 plush")
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .brand(brands.get(2))
                .category(categories.get(1))
                .price(820000.0)
                .modelYear(2015)
                .build());
                products.add(Product.builder()
                .name("Macbook 2021 pro")
                .brand(brands.get(2))
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .category(categories.get(0))
                .price(42000000.0)
                .modelYear(2021)
                .build());
                products.add(Product.builder()
                .name("Macbook 2020 pro")
                .brand(brands.get(2))
                .image("https://firebasestorage.googleapis.com/v0/b/team-project-mobile.appspot.com/o/laptop-hp-15s.jpg?alt=media&token=51200d2f-7563-4bfb-ab7f-3d0808825fc0")
                .category(categories.get(0))
                .price(32000000.0)
                .modelYear(2020)
                .build());

                branchRepository.saveAll(brands);
                System.out.println("Insert brand");
                categoryRepository.saveAll(categories);
                System.out.println("Insert Categories");
                productRepository.saveAll(products);
                System.out.println("Insert Products ");
            }
        };
    }
}
