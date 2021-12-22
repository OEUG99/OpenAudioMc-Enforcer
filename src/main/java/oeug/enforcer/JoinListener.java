package oeug.enforcer;

import com.craftmend.openaudiomc.api.interfaces.AudioApi;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

public class JoinListener implements Listener {

    private final Plugin plugin;
    public final AudioApi api;

    public JoinListener(VoiceCore plugin, AudioApi NewAPI) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.api = NewAPI;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        String kickReason = "[MilkyVoice] You failed to connect to the voice chat within 2 minutes of connecting";
        // Create the task and schedule to run it once, after 30 seconds/600 ticks (
        BukkitTask task = new VoiceKickTask(event.getPlayer(), this.api, kickReason).runTaskLater(this.plugin, 2400);
    }
}
