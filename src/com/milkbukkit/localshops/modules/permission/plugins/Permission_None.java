package com.milkbukkit.localshops.modules.permission.plugins;


import java.util.List;

import org.bukkit.entity.Player;

import com.milkbukkit.localshops.modules.permission.Permission;

public class Permission_None implements Permission {
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

    /* (non-Javadoc)
     * @see com.milkbukkit.localshops.modules.permission.Permission#numChestsAllowed(java.lang.String, java.lang.String)
     */
    @Override
    public int getInfoIntLow(List<String> worlds, String playerName, String node) {
        return -1;
    }

}
