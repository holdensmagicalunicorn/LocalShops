package net.centerleft.localshops.commands;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

import net.centerleft.localshops.InventoryItem;
import net.centerleft.localshops.LocalShops;
import net.centerleft.localshops.PlayerData;
import net.centerleft.localshops.Shop;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandShopDestroy extends Command {

    public CommandShopDestroy(LocalShops plugin, String commandLabel, CommandSender sender, String command) {
        super(plugin, commandLabel, sender, command);
    }

    public CommandShopDestroy(LocalShops plugin, String commandLabel, CommandSender sender, String[] command) {
        super(plugin, commandLabel, sender, command);
    }

    public boolean process() {
        if (!(sender instanceof Player) || !canUseCommand(CommandTypes.DESTROY)) {
            sender.sendMessage(LocalShops.CHAT_PREFIX + ChatColor.DARK_AQUA + "You don't have permission to use this command");
            return false;
        }

        /*
         * Available formats: /lshop destroy
         */

        Player player = (Player) sender;
        String playerName = player.getName();

        // get the shop the player is currently in
        if (plugin.getPlayerData().get(playerName).shopList.size() == 1) {
            UUID shopUuid = plugin.getPlayerData().get(playerName).shopList.get(0);
            Shop shop = plugin.getShopData().getShop(shopUuid);

            if (!shop.getOwner().equalsIgnoreCase(player.getName()) && !canUseCommand(CommandTypes.ADMIN)) {
                player.sendMessage(ChatColor.DARK_AQUA + "You must be the shop owner to destroy it.");
                return false;
            }

            Iterator<PlayerData> it = plugin.getPlayerData().values().iterator();
            while (it.hasNext()) {
                PlayerData p = it.next();
                if (p.shopList.contains(shop.getUuid())) {
                    Player thisPlayer = plugin.getServer().getPlayer(p.playerName);
                    p.removePlayerFromShop(thisPlayer, shop.getUuid());
                    thisPlayer.sendMessage(LocalShops.CHAT_PREFIX + ChatColor.WHITE + shop.getName() + ChatColor.DARK_AQUA + " has been destroyed");
                }
            }

            Collection<InventoryItem> shopItems = shop.getItems();

            if (plugin.getShopData().deleteShop(shop)) {
                // return items to player (if a player)
                if (sender instanceof Player) {
                    for (InventoryItem item : shopItems) {
                        givePlayerItem(item.getInfo().toStack(), item.getStock());
                    }
                }
            } else {
                // error message :(
                sender.sendMessage("Could not return shop inventory!");
            }

        } else {
            player.sendMessage(ChatColor.DARK_AQUA + "You must be inside a shop to use /" + commandLabel + " destroy");
        }

        return true;
    }

}