package oeug.enforcer;

import com.craftmend.openaudiomc.api.impl.event.events.ClientDisconnectEvent;
import com.craftmend.openaudiomc.api.interfaces.AudioApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

public class DisconnectListener {

    private final Plugin plugin;
    public final AudioApi api;
    private Player player;

    public DisconnectListener(Plugin plugin, @NotNull AudioApi api) {
        this.plugin = plugin;
        this.api = api;

        // Event Driver: When a voice client disconnects it gets kicked.
        api.getEventDriver().on(ClientDisconnectEvent.class).setHandler(event ->
        {
            String kickReason = "[MilkyVoice] Kicked for disconnecting from the voice chat.";
            this.player = Bukkit.getPlayer(event.getClient().getOwner().getUniqueId());
            BukkitTask task = new VoiceKickTask(this.player, this.api, kickReason).runTaskLater(this.plugin, 600);
        });
    }}
