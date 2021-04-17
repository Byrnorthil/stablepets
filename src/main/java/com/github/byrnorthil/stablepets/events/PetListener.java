package com.github.byrnorthil.stablepets.events;

import com.github.byrnorthil.stablepets.StablePets;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.metadata.FixedMetadataValue;

public class PetListener implements Listener {

    public StablePets plugin;

    @EventHandler
    public void onPlayerLoginEvent(PlayerLoginEvent event) {
        event.getPlayer().setMetadata("loginTime", new FixedMetadataValue(plugin, event.getPlayer().getWorld().getFullTime()));
    }

    @EventHandler
    public void onPetTeleportEvent(EntityTeleportEvent event) {
        if (event.getEntity() instanceof Tameable) {
            Player owner = Bukkit.getPlayer(((Tameable) event.getEntity()).getOwnerUniqueId());
            if (owner == null || owner.getWorld().getFullTime() - owner.getMetadata("loginTime").get(0).asLong() < 500) {
                event.setCancelled(true);
            }
        }
    }
}
