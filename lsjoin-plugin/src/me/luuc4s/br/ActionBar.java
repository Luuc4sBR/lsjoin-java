package me.luuc4s.br;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar
{
  private static Class<?> CRAFTPLAYERCLASS;
  private static Class<?> PACKET_PLAYER_CHAT_CLASS;
  private static Class<?> ICHATCOMP;
  private static Class<?> CHATMESSAGE;
  private static Class<?> PACKET_CLASS;
  private static Constructor<?> PACKET_PLAYER_CHAT_CONSTRUCTOR;
  private static Constructor<?> CHATMESSAGE_CONSTRUCTOR;
  private static final String SERVER_VERSION;
 
  static
  {
    String name = Bukkit.getServer().getClass().getName();
    name = name.substring(name.indexOf("craftbukkit.") + "craftbukkit.".length());
    name = name.substring(0, name.indexOf("."));
    SERVER_VERSION = name;
    try
    {
      CRAFTPLAYERCLASS = Class.forName("org.bukkit.craftbukkit." + SERVER_VERSION + ".entity.CraftPlayer");
      PACKET_PLAYER_CHAT_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".PacketPlayOutChat");
      PACKET_CLASS = Class.forName("net.minecraft.server." + SERVER_VERSION + ".Packet");
      ICHATCOMP = Class.forName("net.minecraft.server." + SERVER_VERSION + ".IChatBaseComponent");
      PACKET_PLAYER_CHAT_CONSTRUCTOR = PACKET_PLAYER_CHAT_CLASS.getConstructor(new Class[] { ICHATCOMP, Byte.TYPE });
      
      CHATMESSAGE = Class.forName("net.minecraft.server." + SERVER_VERSION + ".ChatMessage");
      
      CHATMESSAGE_CONSTRUCTOR = CHATMESSAGE.getDeclaredConstructor(new Class[] { String.class, Object[].class });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
 
  public static void sendActionBarMessage(Player player, String message)
  {
    try
    {
      Object icb = CHATMESSAGE_CONSTRUCTOR.newInstance(new Object[] { message, new Object[0] });
      
      Object packet = PACKET_PLAYER_CHAT_CONSTRUCTOR.newInstance(new Object[] { icb, Byte.valueOf((byte) 2) });
      
      Object craftplayerInst = CRAFTPLAYERCLASS.cast(player);
      
      Method methodHandle = CRAFTPLAYERCLASS.getMethod("getHandle", new Class[0]);
      
      Object methodhHandle = methodHandle.invoke(craftplayerInst, new Object[0]);
      
      Object playerConnection = methodhHandle.getClass().getField("playerConnection").get(methodhHandle);
      
      playerConnection.getClass().getMethod("sendPacket", new Class[] { PACKET_CLASS }).invoke(playerConnection, new Object[] { packet });
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}