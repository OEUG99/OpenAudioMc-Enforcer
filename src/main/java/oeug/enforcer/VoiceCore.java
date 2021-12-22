package oeug.enforcer;



import com.craftmend.openaudiomc.api.interfaces.AudioApi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class VoiceCore extends JavaPlugin {

    private JoinListener joinListener;
    private DisconnectListener disconnectListener;

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("MilkyCore has been enabled!");

        AudioApi api = AudioApi.getInstance();

        // Starting listener for voice kicking
        joinListener = new JoinListener(this, api);
        disconnectListener = new DisconnectListener(this, api);

        Bukkit.getPluginManager().registerEvents(joinListener, this);
    }

    @Override
    public void onDisable() {

        Bukkit.getLogger().info("MilkyCore has been disabled");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("global")) {

            // Turning the String Array into a single string, adding space between each element in string and inserting
            // it into the user_message variable.
            StringBuilder user_message = new StringBuilder();
            for (String x : args){
                user_message.append(" ").append(x);
            }

            String user_name = sender.getName();
            String msg = "[Global] " + user_name + ":" + user_message;
            Bukkit.broadcastMessage(msg);
        }
        return false;
    }

}