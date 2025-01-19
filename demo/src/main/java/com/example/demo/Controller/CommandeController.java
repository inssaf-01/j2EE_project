package com.example.demo.Controller;

import com.example.demo.clients.ProductClient;
import com.example.demo.configurations.ApplicationPropertiesConfiguration;
import com.example.demo.model.Commande;
import com.example.demo.reposetory.CommandeRepository;
import com.mproduits.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope // Rafraîchit dynamiquement les configurations
public class CommandeController implements HealthIndicator {

    @Autowired
    private CommandeRepository commandeDao;

    @Autowired
    private ApplicationPropertiesConfiguration appProperties;

    @Autowired
    private ProductClient productClient;

    /**
     * Tester la configuration dynamique.
     * @return limite des commandes configurée.
     */
    @GetMapping("/test-config")
    public String testConfig() {
        return "Limite des commandes : " + appProperties.getLimitDeCommandes();
    }

    /**
     * Récupère la liste des commandes, limitée à la configuration.
     * @return liste des commandes.
     * @throws Exception si aucune commande n'est disponible.
     */
    @GetMapping("/Commandes")
    public List<Commande> listeDesCommandes() throws Exception {
        List<Commande> commandes = commandeDao.findAll();

        if (commandes.isEmpty()) {
            throw new Exception("Aucune commande n'est disponible.");
        }

        // Limiter le nombre de commandes retournées.
        int limit = Math.min(commandes.size(), appProperties.getLimitDeCommandes());
        return commandes.subList(0, limit);
    }

    /**
     * Récupère une commande spécifique par son ID.
     * @param id ID de la commande.
     * @return commande correspondante.
     * @throws Exception si la commande n'existe pas.
     */
    @GetMapping("/Commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable Long id) throws Exception {
        Optional<Commande> commande = commandeDao.findById(id);

        if (!commande.isPresent()) {
            throw new Exception("La commande correspondant à l'ID " + id + " n'existe pas.");
        }

        return commande;
    }

    /**
     * Indicateur de santé pour Actuator.
     * @return statut UP si des commandes existent, sinon DOWN.
     */
    @Override
    public Health health() {
        List<Commande> commandes = commandeDao.findAll();

        if (commandes.isEmpty()) {
            return Health.down().withDetail("message", "Aucune commande disponible.").build();
        }

        return Health.up().withDetail("message", "Des commandes sont disponibles.").build();
    }

    @PostMapping("/commandes")
    public Commande passerUneCommande(@RequestBody Commande commande) {
        // Vérifie si le produit existe via le microservice des produits
        Product product = productClient.getProductById(commande.getProductId());
        if (product == null) {
            throw new RuntimeException("Produit introuvable avec l'ID " + commande.getProductId());
        }

        // Valider la commande
        commande.setPrix(product.getPrix() * commande.getQuantite());
        return commandeDao.save(commande);
    }
}
