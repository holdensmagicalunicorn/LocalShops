package com.milkbukkit.localshops.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import com.milkbukkit.localshops.ItemInfo;
import com.milkbukkit.localshops.LocalShops;
import com.milkbukkit.localshops.Search;

public class CommandShopSearch extends Command {

    public CommandShopSearch(LocalShops plugin, String commandLabel, CommandSender sender, String command, boolean isGlobal) {
        super(plugin, commandLabel, sender, command);
    }
    
    public CommandShopSearch(LocalShops plugin, String commandLabel, CommandSender sender, String[] command, boolean isGlobal) {
        super(plugin, commandLabel, sender, command);
    }

    public boolean process() {
        Pattern pattern = Pattern.compile("(?i)search\\s+(.*)");
        Matcher matcher = pattern.matcher(command);
        if (matcher.find()) {
            String name = matcher.group(1);
            ItemInfo found = Search.itemByName(name);
            if (found == null) {
                sender.sendMessage(String.format("No item was not found matching \"%s\"", name));
            } else {
                sender.sendMessage(found.toString());
            }
            return true;            
        }

        // Show search stuff
        sender.sendMessage(ChatColor.WHITE + "   /" + commandLabel + " search [item name]" + ChatColor.DARK_AQUA + " - Searches for and displays information about an item.");
        return true;
    }
}