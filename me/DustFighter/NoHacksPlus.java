package me.DustFighter;

import java.util.ArrayList;
import java.util.logging.Logger;

import net.md_5.bungee.BungeeCord;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoHacksPlus extends JavaPlugin implements Listener{
	public final Logger logger = Logger.getLogger("Minecraft");
	public static NoHacksPlus plugin;
	ArrayList<String> pass = new ArrayList<String>();
	ArrayList<String> frozen = new ArrayList<String>();
	String prefix = ChatColor.GOLD + "[" + ChatColor.DARK_RED + "NHP" + ChatColor.GOLD + "] " + ChatColor.YELLOW;
	
	@Override
	public void onEnable() {
	    getLogger().info("NoHacksPlus has been enabled!");
		plugin = this;
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
	    getLogger().info("NoHacksPlus has been disabled!");
	}
	
	@EventHandler 
	public void onPlayerQuit(PlayerQuitEvent e) {
	Player player = e.getPlayer();
	if(pass.contains(player.getName())){
		pass.remove(player.getName());
		frozen.remove(player.getName());
	}
	} 
	
	 @EventHandler
public void onPlayerJoin(PlayerJoinEvent e) {
	 final Player player = e.getPlayer();
		if(!pass.contains(player.getName())){
			long delayTicks = 50L;
			Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
			    @Override
			    public void run() {
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
					player.sendMessage(prefix + "Please to pass our security system, please say: .");
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
					player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");

			    }
			}, delayTicks);
		pass.add(player.getName());
		frozen.add(player.getName());
	} else if(pass.contains(player.getName())){
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
		player.sendMessage(prefix + "Please to pass our security system, please say: .");
		player.sendMessage(ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123" + ChatColor.RESET + ChatColor.GOLD + "----------------------------------------------" + ChatColor.DARK_RED + "" + ChatColor.MAGIC + "123");
	}
	}
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
	Player player = e.getPlayer();
	if(e.getMessage().startsWith(".")){
		if(pass.contains(player.getName())){
			pass.remove(player.getName());
			frozen.remove(player.getName());
			Bukkit.broadcastMessage(prefix + player.getName() + " Are clean... For now!");
			player.sendMessage(prefix + "Ok you are okay! You may now do what ever you want. BUT Still, hacking isn't allowed...");
			e.setCancelled(true);
	}
	}
		else if(!e.getMessage().startsWith(".")){
			if(pass.contains(player.getName())){
				e.setCancelled(true);
				player.sendMessage(prefix + "You cant talk before you have passed our security system!");
				player.sendMessage(prefix + "To pass our security system, please say: .");
	}
	}
		if(!pass.contains(player.getName())){
			e.setCancelled(false);
}
}	
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		Player player = e.getPlayer();
		 if(pass.contains(player.getName())){
            e.setCancelled(true);
            player.sendMessage(prefix + "You can't write a command before you have passed our security system!");
    		player.sendMessage(prefix + "To pass our security system, please say: .");
		} 
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e){
		Player player = e.getPlayer();
			if(frozen.contains(player.getName())){
				player.teleport(player);
				
	}
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent e){
		Player player = e.getPlayer();
    if(pass.contains(player.getName())){
    	e.setCancelled(true);
        player.sendMessage(prefix + "You can't break blocks before you have passed our security system!");
    }else if(!pass.contains(player.getName())){
    	e.setCancelled(false);
    }
    }
    
	@EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
		Player player = e.getPlayer();
        if(pass.contains(player.getName())){
        	e.setCancelled(true);
            player.sendMessage(prefix + "You can't place blocks before you have passed our security system!");
        }else if(!pass.contains(player.getName())){
        	e.setCancelled(false);
        }

    }
}
