package oeug.enforcer;


import com.craftmend.openaudiomc.api.interfaces.AudioApi;
import com.craftmend.openaudiomc.api.interfaces.Client;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;
import java.util.UUID;

public class VoiceKickTask extends BukkitRunnable {

    private final Player player;
    public final AudioApi api;
    public String kickReason;

    VoiceKickTask(Player newPlayer, AudioApi newApi, String newKickReason) {
        this.player = newPlayer;
        this.api = newApi;
        this.kickReason = newKickReason;
    }

    @Override
    public void run() {
        UUID player_uuid;

        boolean playerClientStatus = true;
        player_uuid = player.getUniqueId();
        Client playerClient = api.getClient(player_uuid);


        if (playerClient != null) {
            playerClientStatus = playerClient.isConnected();

        }

        if (!playerClientStatus) {
            player.kickPlayer(this.kickReason);
            System.out.println("[MilkyVoice] Kicked " + player.getName() + "due to failing to join the voice client.");
        }
    }
}
