/**
 * 
 * Copyright 2011 MilkBowl (https://github.com/MilkBowl)
 * 
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 3.0 Unported License. To view a copy of
 * this license, visit http://creativecommons.org/licenses/by-nc-sa/3.0/ or send
 * a letter to Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 * 
 */

package net.milkbowl.localshops.customvault;

import net.milkbowl.vault.modules.permission.Permission;

import org.bukkit.entity.Player;


public class LocalShopsVaultPermission implements Permission {
    private String name = "Local Fallback Permissions";

    @Override
    public boolean isEnabled() {
        // This method is essentially static, it is always enabled if LS is!
        return true;
    }

    @Override
    public boolean hasPermission(Player player, String permission) {
        // Allow OPs to everything
        if(player.isOp()) {
            return true;
        }
        
        // Allow everyone user & manager
        if(permission.startsWith("localshops.user") || permission.startsWith("localshops.manager")) {
            return true;
        }
        
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    /* 
     * @see com.milkbukkit.localshops.modules.permission.Permission#inGroup(java.lang.String, java.lang.String, java.lang.String)
     * Users can never be in groups without a permissions plugin - always resolves false if checked.
     */
    @Override
    public boolean inGroup(String worldName, String playerName, String groupName) {
        return false;
    }

    /* 
     *
     */
    @Override
    public int getInfoInt(String world, String playerName, String node) {
        return -1;
    }

}