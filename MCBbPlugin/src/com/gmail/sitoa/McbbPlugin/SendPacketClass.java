package com.gmail.sitoa.McbbPlugin;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;;

public class SendPacketClass {
	public static void sendPacketClass(Player p ,Packet packet){
		PlayerConnection con = ((CraftPlayer)p).getHandle().playerConnection;
		con.sendPacket(packet);
	}
	
	public static  void MakeTitlePacket(Player player,Integer fadein,Integer stay,Integer fadeout,String title,String subtitle){
		 PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
		 PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(EnumTitleAction.TIMES, null, fadein, stay, fadeout);
		 connection.sendPacket(packetPlayOutTimes);
		 if (subtitle != null) {
		 subtitle = subtitle.replaceAll("%player%", player.getDisplayName());
		 subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
		 IChatBaseComponent titleSub = ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
		 PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, titleSub);
		 connection.sendPacket(packetPlayOutSubTitle);
		 }
		 if (title != null) {
		 title = title.replaceAll("%player%", player.getDisplayName());
		 title = ChatColor.translateAlternateColorCodes('&', title);
		 IChatBaseComponent titleMain = ChatSerializer.a("{\"text\": \"" + title + "\"}");
		 PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, titleMain);
		 connection.sendPacket(packetPlayOutTitle);
		 }
		 }
}
