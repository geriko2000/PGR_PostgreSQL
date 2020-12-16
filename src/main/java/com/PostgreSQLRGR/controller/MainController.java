package com.PostgreSQLRGR.controller;

import com.PostgreSQLRGR.domain.Client;
import com.PostgreSQLRGR.domain.Order;
import com.PostgreSQLRGR.domain.Product;
import com.PostgreSQLRGR.repos.ClientRepo;
import com.PostgreSQLRGR.repos.OrderRepo;
import com.PostgreSQLRGR.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/")
    public String greeting() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/product") /*Гет маппинг для вывода таблицы изделий*/
    public String product(@RequestParam(required = false, defaultValue = "") Integer id,
                          @RequestParam(required = false, defaultValue = "") String name,
                          @RequestParam(required = false, defaultValue = "") String material,
                          @RequestParam(required = false, defaultValue = "") String type,
                          @RequestParam(required = false, defaultValue = "false") boolean availability,
                          Model model) {
        Iterable<Product> product;
        if (id != null) {
            product = productRepo.findById(id);
        } else if (!name.isEmpty()) {
            product = productRepo.findByName(name);
        } else if (!material.isEmpty()) {
            product = productRepo.findByMaterial(material);
        } else if (!type.isEmpty()) {
            product = productRepo.findByType(type);
        } else if (availability) {
            //boolean isAvailable = Boolean.getBoolean(availability);
            //System.out.println(isAvailable);
            if (availability) {
                product = productRepo.findByAvailabilityGreaterThan(0);
            } else {
                product = productRepo.findAll();
            }
        } else {
            product = productRepo.findAll();
        }

//        product.forEach(System.out::println);
//        System.out.println("id = " + id);
//        System.out.println("name = " + name);
//        System.out.println("material = " + material);
//        System.out.println("type = " + type);
//        System.out.println("avail = " + availability);

        model.addAttribute("products", product);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("material", material);
        model.addAttribute("type", type);
        model.addAttribute("availability", availability);

        return "product";
    }

    @GetMapping("/order") /*Гет маппинг для вывода таблицы заказов*/
    public String order(@RequestParam(required = false, defaultValue = "") Integer id,
                        @RequestParam(required = false, defaultValue = "") String client,
                        @RequestParam(required = false, defaultValue = "") String product,
                        @RequestParam(required = false, defaultValue = "") String date,
                        Model model) {
        Iterable<Order> order;
        if (id != null) {
            order = orderRepo.findById(id);
        } else if (!client.isEmpty()) {
            order = orderRepo.findByClient(client);
        } else if (!product.isEmpty()) {
            order = orderRepo.findByProduct(product);
        } else if (!date.isEmpty()) {
            order = orderRepo.findByDate(LocalDate.parse(date));
        } else {
            order = orderRepo.findAll();
        }

        model.addAttribute("orders", order);
        model.addAttribute("id", id);
        model.addAttribute("client", client);
        model.addAttribute("product", product);
        model.addAttribute("date", date);

        return "order";
    }

    @GetMapping("/client") /*Гет маппинг для вывода таблицы клиентов*/
    public String client(@RequestParam(required = false, defaultValue = "") Integer id,
                         @RequestParam(required = false, defaultValue = "") String name,
                         @RequestParam(required = false, defaultValue = "") String city,
                         Model model) {
        Iterable<Client> client;
        if (id != null) {
            client = clientRepo.findById(id);
        } else if (!name.isEmpty()) {
            client = clientRepo.findByName(name);
        } else if (!city.isEmpty()) {
            client = clientRepo.findByCity(city);
        } else {
            client = clientRepo.findAll();
        }

        model.addAttribute("clients", client);
        model.addAttribute("id", id);
        model.addAttribute("name", name);
        model.addAttribute("city", city);

        return "client";
    }

    @GetMapping("/addproduct") /*Гет маппинг для добавления продукта*/
    public String addProduct(Model model) {
        Iterable<Product> product = productRepo.findAll();
        model.addAttribute("products", product);
        return "addproduct";
    }

    @PostMapping("/addproduct") /*Пост маппинг для добавления продукта*/
    public String addProductInTable(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String material,
            @RequestParam(required = false) String availability,
            @RequestParam(required = false) String releaseDate,
            @RequestParam(required = false) String cost,
            Model model
    ) {

        if (name.isEmpty()) {
            model.addAttribute("message", "Поле с названием продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        } else if (type.isEmpty()) {
            model.addAttribute("message", "Поле с типом продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        } else if (material.isEmpty()) {
            model.addAttribute("message", "Поле с материалом продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        } else if (availability.isEmpty()) {
            model.addAttribute("message", "Поле с количеством продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        } else if (releaseDate.isEmpty()) {
            model.addAttribute("message", "Поле с датой производства продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        } else if (cost.isEmpty()) {
            model.addAttribute("message", "Поле с количеством продукта не должно быть пустым");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        }

        Integer isAvailabilityInt;
        try {
            isAvailabilityInt = Integer.parseInt(availability);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректное количество (число)");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        }

        Integer isCostInt;
        try {
            isCostInt = Integer.parseInt(cost);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректную цену (число)");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        }

        LocalDate isDateLocalDate;
        try {
            isDateLocalDate = LocalDate.parse(releaseDate);
        } catch (DateTimeException ex) {
            model.addAttribute("message", "Введите корректную дату производства (yyyy-mm-dd)");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        }

        Product product = new Product(name, type, material, isAvailabilityInt, isDateLocalDate, isCostInt);

        List<Product> productFromDb = productRepo.findByName(name);

        if (!productFromDb.isEmpty()) {
            model.addAttribute("message", "Товар с таким именем уже существует!");
            Iterable<Product> products = productRepo.findAll();
            model.addAttribute("products", products);
            return "addproduct";
        }

        productRepo.save(product);

        model.addAttribute("message", "Запись успешно добавлена!");

        Iterable<Product> products = productRepo.findAll();
        model.addAttribute("products", products);

        return "addproduct";
    }

    @GetMapping("/addclient") /*Гет маппинг для добавления клиента*/
    public String addClient(Model model) {
        Iterable<Client> client = clientRepo.findAll();
        model.addAttribute("clients", client);
        return "addclient";
    }

    @PostMapping("/addclient") /*Пост маппинг для добавления клиента*/
    public String addClientInTable(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String city,
            Model model
    ) {

        if (name.isEmpty()) {
            model.addAttribute("message", "Поле с именем клиента не должно быть пустым");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        } else if (email.isEmpty()) {
            model.addAttribute("message", "Поле с email не должно быть пустым");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        } else if (phone.isEmpty()) {
            model.addAttribute("message", "Поле с номером телефона не должно быть пустым");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        } else if (address.isEmpty()) {
            model.addAttribute("message", "Поле с адресом не должно быть пустым");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        } else if (city.isEmpty()) {
            model.addAttribute("message", "Поле с городом не должно быть пустым");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        }

        Long isPhoneLong;
        try {
            isPhoneLong = Long.parseLong(phone);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректный номер телефона (цифрами без знаков и пробелов)");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        }

        Client client = new Client(name, email, isPhoneLong, address, city);

        List<Client> clientFromDb = clientRepo.findByName(name);

        if (!clientFromDb.isEmpty()) {
            model.addAttribute("message", "Клиент с таким именем уже существует!");
            Iterable<Client> clients = clientRepo.findAll();
            model.addAttribute("clients", clients);
            return "addclient";
        }

        clientRepo.save(client);

        model.addAttribute("message", "Запись успешно добавлена!");

        Iterable<Client> clients = clientRepo.findAll();
        model.addAttribute("clients", clients);

        return "addclient";
    }

    @GetMapping("/addorder") /*Гет маппинг для добавления заказа*/
    public String addOrder(Model model) {
        Iterable<Order> order = orderRepo.findAll();
        model.addAttribute("orders", order);
        return "addorder";
    }

    @PostMapping("/addorder") /*Пост маппинг для добавления заказа*/
    public String addOrderInTable(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String quantity,
            @RequestParam(required = false) String date,
            Model model
    ) {

        if (client.isEmpty()) {
            model.addAttribute("message", "Поле с именем клиента не должно быть пустым");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        } else if (product.isEmpty()) {
            model.addAttribute("message", "Поле с типом продукта не должно быть пустым");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        } else if (quantity.isEmpty()) {
            model.addAttribute("message", "Поле с материалом продукта не должно быть пустым");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        } else if (date.isEmpty()) {
            model.addAttribute("message", "Поле с количеством продукта не должно быть пустым");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        }

        Integer isQuantityInt;
        try {
            isQuantityInt = Integer.parseInt(quantity);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректное количество (число)");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        }

        LocalDate isDateLocalDate;
        try {
            isDateLocalDate = LocalDate.parse(date);
        } catch (DateTimeException ex) {
            model.addAttribute("message", "Введите корректную дату заказа (yyyy-mm-dd)");
            Iterable<Order> orders = orderRepo.findAll();
            model.addAttribute("orders", orders);
            return "addorder";
        }

        Order order = new Order(client, product, isQuantityInt, isDateLocalDate);

        orderRepo.save(order);

        model.addAttribute("message", "Запись успешно добавлена!");

        Iterable<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);

        return "addorder";
    }

}
