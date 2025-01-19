package com.clientui.proxies;

import com.clientui.beans.CommandeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "zuul-server", url = "http://localhost:9002")
public interface MicroserviceCommandeProxy {
    //@PostMapping(value = "/microservice-commandes/commandes")
    //CommandeBean ajouterCommande(@RequestBody CommandeBean commande);

    @PostMapping("/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);

    @GetMapping(value = "/microservice-commandes/Commandes")
    List<CommandeBean> listeDesCommandes();

    @GetMapping(value = "/microservice-commandes/Commandes/{id}")
    Optional<CommandeBean> recupererUneCommande(@PathVariable Long id);

    @PutMapping("/microservice-commandes/Commandes/{id}")
    ResponseEntity<CommandeBean> modifierCommande(@PathVariable Long id, @RequestBody CommandeBean commandeDetails);

    @DeleteMapping("/microservice-commandes/Commandes/{id}")
    ResponseEntity<Void> supprimerCommande(@PathVariable Long id);
}

