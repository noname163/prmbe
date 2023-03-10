package com.pussinboots.prmproject.data.fakedata;

import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

import com.pussinboots.prmproject.data.repositories.ProductRepository;
import com.pussinboots.prmproject.data.repositories.UserRepositories;
import com.pussinboots.prmproject.data.repositories.CategoryRepository;
import com.pussinboots.prmproject.data.repositories.CustomerRepository;
import com.pussinboots.prmproject.data.repositories.BrandRepository;
import com.pussinboots.prmproject.data.repositories.StockRepository;
import com.pussinboots.prmproject.data.constans.ERole;
import com.pussinboots.prmproject.data.entities.*;

@Configuration
public class Fakedata {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, CustomerRepository customerRepository,
            CategoryRepository categoryRepository, BrandRepository branchRepository, StockRepository stockRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Customer user = Customer.builder().email("danghuudat163@gmail.com")
                        .phone("0123456789")
                        .firstName("Dat")
                        .lastName("Dang")
                        .state("test")
                        .city("HCM")
                        .street("Test")
                        .zipCode(1200l)
                        .role(ERole.USER)
                        .build();

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

                List<Stock> stocks = new ArrayList<>();

                Stock stock1 = Stock.builder()
                        .quantity(10)
                        .build();
                Stock stock2 = Stock.builder()
                        .quantity(10)
                        .build();
                Stock stock3 = Stock.builder()
                        .quantity(10)
                        .build();
                stocks.add(stock3);
                stocks.add(stock2);
                stocks.add(stock1);

                List<Product> products = new ArrayList<>();
                products.add(Product.builder()
                        .name("Iphone 8 plush")
                        .brand(brands.get(2))
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179419/iphone8_ifopyk.png")
                        .category(categories.get(1))
                        .price(4200000.0)
                        .modelYear(2019)
                        .description(
                                "The iPhone 8 and iPhone 8 Plus are smartphones designed, developed, and marketed by Apple Inc. They are the eleventh generation of the iPhone.")
                        .stock(stock3)
                        .build());
                products.add(Product.builder()
                        .name("Iphone 7 plush")
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179481/iphone7_jdffch.jpg")
                        .brand(brands.get(2))
                        .category(categories.get(1))
                        .price(3200000.0)
                        .modelYear(2018)
                        .stock(stock2)
                        .description(
                                "The iPhone 7 and iPhone 7 Plus are smartphones designed, developed, and marketed by Apple Inc. They are the eleventh generation of the iPhone. ")
                        .build());
                products.add(Product.builder()
                        .name("Iphone 6 plush")
                        .brand(brands.get(2))
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179537/iphone6_i00ekx.jpg")
                        .category(categories.get(1))
                        .price(2200000.0)
                        .modelYear(2017)
                        .description(
                                "The iPhone 6 and iPhone 6 Plus are smartphones designed, developed, and marketed by Apple Inc. They are the eleventh generation of the iPhone.")
                        .stock(stock1)
                        .build());
                products.add(Product.builder()
                        .name("Iphone 5")
                        .brand(brands.get(2))
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179619/iphone5_zsin8o.jpg")
                        .category(categories.get(1))
                        .price(1500000.0)
                        .stock(stock2)
                        .modelYear(2016)
                        .description(
                                "The iPhone 5 is a smartphone that was designed and marketed by Apple Inc. It is the 6th generation iPhone, succeeding both the iPhone 4 and iPhone 4S, and preceding both the iPhone 5S and 5C")
                        .build());
                products.add(Product.builder()
                        .name("Iphone 4")
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179675/iphone4_ag0bx9.jpg")
                        .brand(brands.get(2))
                        .category(categories.get(1))
                        .price(820000.0)
                        .modelYear(2015)
                        .stock(stock1)
                        .description(
                                "The iPhone 4 is a smartphone that was designed and marketed by Apple Inc. It is the fourth generation of the iPhone lineup, succeeding the iPhone 3GS and preceding the 4S.")
                        .build());
                products.add(Product.builder()
                        .name("Macbook 2020 pro")
                        .brand(brands.get(2))
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179853/macbook2020_vdosra.jpg")
                        .category(categories.get(0))
                        .price(42000000.0)
                        .modelYear(2020)
                        .stock(stock3)
                        .description(
                                "The MacBook Pro is a line of Mac laptops made by Apple Inc. Introduced in January 2006, it is the higher-end lineup in the MacBook family, sitting above the consumer-focused MacBook Air")
                        .build());
                products.add(Product.builder()
                        .name("Macbook 2018 pro")
                        .brand(brands.get(2))
                        .image("https://res.cloudinary.com/dyvrvbcxx/image/upload/v1678179899/macbook2018_ysftfj.jpg")
                        .category(categories.get(0))
                        .price(32000000.0)
                        .modelYear(2018)
                        .stock(stock3)
                        .description(
                                "With the 2018 MacBook Pro, it'll depend—making it a hard decision. The model I tried was well-equipped with the Core i9 processor.")
                        .build());

                customerRepository.save(user);
                System.out.println("Insert user name " + user.getPhone());
                branchRepository.saveAll(brands);
                System.out.println("Insert brand");
                categoryRepository.saveAll(categories);
                System.out.println("Insert Categories");
                stockRepository.saveAll(stocks);
                System.out.println("Insert stocks");
                productRepository.saveAll(products);
                System.out.println("Insert Products ");
            }
        };
    }
}
