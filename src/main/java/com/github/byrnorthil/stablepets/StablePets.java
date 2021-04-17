package com.github.byrnorthil.stablepets;

import com.github.byrnorthil.stablepets.events.PetListener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.java.JavaPlugin;

public class StablePets extends JavaPlugin {
    @Override
    public void onEnable() {
        super.onEnable();
        PetListener petListener = new PetListener();
        petListener.plugin = this;

        getServer().getPluginManager().registerEvents(petListener, this);
    }
}
