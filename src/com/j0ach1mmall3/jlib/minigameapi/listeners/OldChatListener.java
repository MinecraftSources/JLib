package com.j0ach1mmall3.jlib.minigameapi.listeners;

import com.j0ach1mmall3.jlib.Main;
import com.j0ach1mmall3.jlib.minigameapi.game.Game;
import com.j0ach1mmall3.jlib.minigameapi.game.GameChatType;
import com.j0ach1mmall3.jlib.minigameapi.team.Team;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author j0ach1mmall3 (business.j0ach1mmall3@gmail.com)
 * @since 19/09/2015
 */
public class OldChatListener implements Listener {
    private final Main plugin;
    public OldChatListener(Main plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * The old PlayerChatEvent Listener, for legacy purposes
     * @see Game
     * @see GameChatType
     */
    @EventHandler(priority = EventPriority.LOWEST)
    @SuppressWarnings("deprecation")
    public void onPlayerChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        if(this.plugin.getApi().isInGame(p)) {
            Game game = this.plugin.getApi().getGame(p);
            GameChatType type = game.getChatType();
            Team team = game.getTeam(p);
            if(type == GameChatType.DISABLED || !team.canChat()) {
                e.setCancelled(true);
                return;
            }
            if(type == GameChatType.PLAYER) {
                e.getRecipients().clear();
                e.getRecipients().add(p);
                return;
            }
            if(type == GameChatType.TEAM) {
                List<Player> recipients = new ArrayList<>(e.getRecipients());
                for(Player recipient : recipients) {
                    if(!team.containsMember(recipient)) recipients.remove(recipient);
                }
            }
        }
    }
}
