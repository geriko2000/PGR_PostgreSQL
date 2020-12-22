package com.PostgreSQLRGR.controller;

import com.PostgreSQLRGR.models.Client;
import com.PostgreSQLRGR.models.Order;
import com.PostgreSQLRGR.models.Product;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public String product(@RequestParam(required = false, defaultValue = "") String search,
                          @RequestParam(required = false, defaultValue = "") String filter,
                          @RequestParam(required = false, defaultValue = "") String minCost,
                          @RequestParam(required = false, defaultValue = "") String maxCost,
                          @RequestParam(required = false, defaultValue = "false") boolean availability,
                          Model model) {

        Iterable<Product> product = productRepo.findAll();

        //Фильтры по характеристикам

        if (!search.isEmpty() && filter.equals("name")) {
            if (!availability) {
                product = productRepo.findByName(search);
            } else {
                product = productRepo.findByNameAndAvailabilityGreaterThan(search, 0);
            }
        } else if (!search.isEmpty() && filter.equals("material")) {
            if (!availability) {
                product = productRepo.findByMaterial(search);
            } else {
                product = productRepo.findByMaterialAndAvailabilityGreaterThan(search, 0);
            }
        } else if (!search.isEmpty() && filter.equals("type")) {
            if (!availability) {
                product = productRepo.findByType(search);
            } else {
                product = productRepo.findByTypeAndAvailabilityGreaterThan(search, 0);
            }
        } else if (search.isEmpty() && availability) {
            product = productRepo.findByAvailabilityGreaterThan(0);
        }

        //Фильтры по цене

        if (!minCost.isEmpty() && !maxCost.isEmpty()) {
            int isMinCostInt, isMaxCostInt;
            try {
                isMinCostInt = Integer.parseInt(minCost);
                isMaxCostInt = Integer.parseInt(maxCost);
            } catch (NumberFormatException ex) {
                model.addAttribute("message", "Введите корректную цену (число)");
                model.addAttribute("products", product);
                return "product";
            }

            product = productRepo.findByCostLessThanEqualAndCostGreaterThanEqual(isMaxCostInt, isMinCostInt);
            model.addAttribute("products", product);
            return "product";
        } else if (!minCost.isEmpty()) {
            int isMinCostInt;
            try {
                isMinCostInt = Integer.parseInt(minCost);
            } catch (NumberFormatException ex) {
                model.addAttribute("message", "Введите корректную цену (число)");
                model.addAttribute("products", product);
                return "product";
            }

            product = productRepo.findByCostGreaterThanEqual(isMinCostInt);
            model.addAttribute("products", product);
            return "product";
        } else if (!maxCost.isEmpty()) {
            int isMaxCostInt;
            try {
                isMaxCostInt = Integer.parseInt(maxCost);
            } catch (NumberFormatException ex) {
                model.addAttribute("message", "Введите корректную цену (число)");
                model.addAttribute("products", product);
                return "product";
            }

            product = productRepo.findByCostLessThanEqual(isMaxCostInt);
            model.addAttribute("products", product);
            return "product";
        }

        //Тесты кеками

//        product.forEach(System.out::println);
//        System.out.println("id = " + id);
//        System.out.println("name = " + name);
//        System.out.println("material = " + material);
//        System.out.println("type = " + type);
//        System.out.println("avail = " + availability);

        //Вывод модели

        model.addAttribute("products", product);

        return "product";
    }

    @GetMapping("/order") /*Гет маппинг для вывода таблицы заказов*/
    public String order(@RequestParam(required = false, defaultValue = "") String search,
                        @RequestParam(required = false, defaultValue = "") String filter,
                        Model model) {
        Iterable<Order> order;

        //Фильтры

        if (filter.equals("client") && !search.isEmpty()) {
            order = orderRepo.findByClient(search);
        } else if (filter.equals("product") && !search.isEmpty()) {
            order = orderRepo.findByProduct(search);
        } else if (filter.equals("id") && !search.isEmpty()) {
            int isIdInt;
            try {
                isIdInt = Integer.parseInt(search);
            } catch (NumberFormatException ex) {
                model.addAttribute("message", "Введите корректный номер заказа (число)");
                model.addAttribute("orders", orderRepo.findAll());
                return "order";
            }
            order = orderRepo.findById(isIdInt);
        } else {
            order = orderRepo.findAll();
        }

        //Вывод модели

        model.addAttribute("orders", order);

        return "order";
    }

    @GetMapping("/client") /*Гет маппинг для вывода таблицы клиентов*/
    public String client(@RequestParam(required = false, defaultValue = "") String search,
                         @RequestParam(required = false, defaultValue = "") String filter,
                         Model model) {
        Iterable<Client> client;

        //Фильтры

        System.out.println("search = " + search + " filter = " + filter);
        if (filter.equals("name") && !search.isEmpty()) {
            client = clientRepo.findByName(search);
        } else if (filter.equals("address") && !search.isEmpty()) {
            client = clientRepo.findByAddress(search);
        } else if (filter.equals("city") && !search.isEmpty()) {
            client = clientRepo.findByCity(search);
        } else {
            client = clientRepo.findAll();
        }

        //Вывод модели

        model.addAttribute("clients", client);

        return "client";
    }

    @GetMapping("/addproduct") /*Гет маппинг для добавления продукта*/
    public String addProduct(Model model) {
        return viewProductTable(model);
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
            return viewProductTable(model);
        } else if (type.isEmpty()) {
            model.addAttribute("message", "Поле с типом продукта не должно быть пустым");
            return viewProductTable(model);
        } else if (material.isEmpty()) {
            model.addAttribute("message", "Поле с материалом продукта не должно быть пустым");
            return viewProductTable(model);
        } else if (availability.isEmpty()) {
            model.addAttribute("message", "Поле с количеством продукта не должно быть пустым");
            return viewProductTable(model);
        } else if (releaseDate.isEmpty()) {
            model.addAttribute("message", "Поле с датой производства продукта не должно быть пустым");
            return viewProductTable(model);
        } else if (cost.isEmpty()) {
            model.addAttribute("message", "Поле с количеством продукта не должно быть пустым");
            return viewProductTable(model);
        }

        int isAvailabilityInt;
        try {
            isAvailabilityInt = Integer.parseInt(availability);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректное количество (число)");
            return viewProductTable(model);
        }

        int isCostInt;
        try {
            isCostInt = Integer.parseInt(cost);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректную цену (число)");
            return viewProductTable(model);
        }

        LocalDate isDateLocalDate;
        try {
            isDateLocalDate = LocalDate.parse(releaseDate);
        } catch (DateTimeException ex) {
            model.addAttribute("message", "Введите корректную дату производства (yyyy-mm-dd)");
            return viewProductTable(model);
        }

        Product product = new Product(name, type, material, isAvailabilityInt, isDateLocalDate, isCostInt);

        List<Product> productFromDb = productRepo.findByName(name);

        if (!productFromDb.isEmpty()) {
            model.addAttribute("message", "Товар с таким именем уже существует!");
            return viewProductTable(model);
        }

        productRepo.save(product);

        model.addAttribute("message", "Запись успешно добавлена!");

        return viewProductTable(model);
    }

    @GetMapping("/addclient") /*Гет маппинг для добавления клиента*/
    public String addClient(Model model) {
        return viewClientTable(model);
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
            return viewClientTable(model);
        } else if (email.isEmpty()) {
            model.addAttribute("message", "Поле с email не должно быть пустым");
            return viewClientTable(model);
        } else if (phone.isEmpty()) {
            model.addAttribute("message", "Поле с номером телефона не должно быть пустым");
            return viewClientTable(model);
        } else if (address.isEmpty()) {
            model.addAttribute("message", "Поле с адресом не должно быть пустым");
            return viewClientTable(model);
        } else if (city.isEmpty()) {
            model.addAttribute("message", "Поле с городом не должно быть пустым");
            return viewClientTable(model);
        }

        long isPhoneLong;
        try {
            isPhoneLong = Long.parseLong(phone);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректный номер телефона (цифрами без знаков и пробелов)");
            return viewClientTable(model);
        }

        Pattern pattern = Pattern.compile("\\S+@\\w+\\.(ru|com|net|kek)");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            model.addAttribute("message", "Введите почту в формате example@domain.ru");
            return viewProductTable(model);
        }

        Client client = new Client(name, email, isPhoneLong, address, city);

        List<Client> clientFromDb = clientRepo.findByName(name);

        if (!clientFromDb.isEmpty()) {
            model.addAttribute("message", "Клиент с таким именем уже существует!");
            return viewClientTable(model);
        }

        clientRepo.save(client);

        model.addAttribute("message", "Запись успешно добавлена!");

        return viewClientTable(model);
    }

    @GetMapping("/addorder") /*Гет маппинг для добавления заказа*/
    public String addOrder(Model model) {
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        model.addAttribute("orders", orderRepo.findAll());
        return "addorder";
    }

    @PostMapping("/addorder") /*Пост маппинг для добавления заказа*/
    public String addOrderInTable(
            @RequestParam(required = false) String client,
            @RequestParam(required = false) String product,
            @RequestParam(required = false) String quantity,
            Model model
    ) {

        if (quantity.isEmpty()) {
            model.addAttribute("message", "Введите количество заказанных изделий");
            return viewOrderTable(model);
        }

        int isQuantityInt;
        try {
            isQuantityInt = Integer.parseInt(quantity);
        } catch (NumberFormatException ex) {
            model.addAttribute("message", "Введите корректное количество (число)");
            return viewOrderTable(model);
        }

        if(isQuantityInt < 1){
            model.addAttribute("message", "Количество заказа не может быть равно нулю!");
            return viewOrderTable(model);
        }

        LocalDate date = LocalDate.now();

        Order order = new Order(client, product, isQuantityInt, date);

        orderRepo.save(order);

        model.addAttribute("message", "Запись успешно добавлена!");

        return viewOrderTable(model);
    }

    private String viewProductTable(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "addproduct";
    }

    private String viewClientTable(Model model) {
        model.addAttribute("clients", clientRepo.findAll());
        return "addclient";
    }

    private String viewOrderTable(Model model) {
        model.addAttribute("orders", orderRepo.findAll());
        model.addAttribute("clients", clientRepo.findAll());
        model.addAttribute("products", productRepo.findAll());
        return "addorder";
    }

}
