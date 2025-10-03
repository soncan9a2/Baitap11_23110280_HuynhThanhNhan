package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class ProductController {

    private final List<String> products = new ArrayList<>(List.of("iPhone", "Galaxy", "Pixel"));

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", products);
        return "index";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        return "new_product";
    }

    @PostMapping("/new")
    public String createProduct(String name) {
        if (name != null && !name.isBlank()) {
            products.add(name.trim());
        }
        return "redirect:/";
    }

    @GetMapping("/edit/{idx}")
    public String editProduct(@PathVariable int idx, Model model) {
        model.addAttribute("index", idx);
        model.addAttribute("name", products.get(idx));
        return "edit_product";
    }

    @PostMapping("/edit/{idx}")
    public String updateProduct(@PathVariable int idx, String name) {
        if (idx >= 0 && idx < products.size() && name != null && !name.isBlank()) {
            products.set(idx, name.trim());
        }
        return "redirect:/";
    }

    @PostMapping("/delete/{idx}")
    public String deleteProduct(@PathVariable int idx) {
        if (idx >= 0 && idx < products.size()) {
            products.remove(idx);
        }
        return "redirect:/";
    }
}


