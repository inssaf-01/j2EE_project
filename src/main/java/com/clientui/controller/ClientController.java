package com.clientui.controller;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private MicroserviceProduitsProxy produitsProxy;

    @Autowired
    private MicroserviceCommandeProxy commandesProxy;

    @GetMapping("/")
    public String accueil(Model model) {
        List<ProductBean> produits = produitsProxy.listeDesProduits();
        model.addAttribute("produits", produits);
        return "Accueil";
    }

    @GetMapping("/produit/{id}")
    public String ficheProduit(@PathVariable int id, Model model) {
        ProductBean produit = produitsProxy.recupererUnProduit(id);
        model.addAttribute("produit", produit);
        return "FicheProduit";
    }

    @PostMapping("/commande")
    public String passerCommande(@RequestParam int productId, @RequestParam int quantite, Model model) {
        ProductBean produit = produitsProxy.recupererUnProduit(productId);
        CommandeBean commande = new CommandeBean();
        commande.setProductId(productId);
        commande.setQuantite(quantite);
        commande.setPrix(produit.getPrix() * quantite);
        commandesProxy.ajouterCommande(commande);

        model.addAttribute("produit", produit);
        model.addAttribute("quantite", quantite);
        return "Confirmation";
    }
}
