package com.mproduits.web.controller;

import com.mproduits.dao.ProductDao;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductDao productDao;

    /**
     * Affiche la page des produits avec une liste de tous les produits.
     * @param model Modèle pour passer les données à la vue.
     * @return le nom de la vue (products.html).
     */
    @GetMapping("/products-page")
    public String afficherProduitsPage(Model model) {
        List<Product> products = productDao.findAll();

        if (products.isEmpty()) {
            throw new ProductNotFoundException("Aucun produit n'est disponible à la vente.");
        }

        // Ajoutez la liste des produits au modèle
        model.addAttribute("products", products);
        return "products"; // Retourne la vue products.html
    }


    /**
     * Récupère un produit par son ID.
     * @param id ID du produit.
     * @return produit correspondant au format JSON.
     */
    @GetMapping("/products/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {
        Optional<Product> product = productDao.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("Le produit correspondant à l'ID " + id + " n'existe pas.");
        }

        return product;
    }

    @GetMapping("/product-details/{id}")
    public String afficherDetailsProduit(@PathVariable int id, Model model) {
        Optional<Product> product = productDao.findById(id);

        if (!product.isPresent()) {
            throw new ProductNotFoundException("Le produit correspondant à l'ID " + id + " n'existe pas.");
        }

        model.addAttribute("product", product.get());
        return "product-details"; // Correspond au fichier product-details.html
    }

}
