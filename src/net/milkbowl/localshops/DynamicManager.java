/**
 * 
 * Copyright 2011 MilkBowl (https://github.com/MilkBowl)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 * 
 */
package net.milkbowl.localshops;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import net.milkbowl.localshops.objects.Item;

/**
 * @author sleaker
 *
 */
public class DynamicManager {

    @SuppressWarnings("unused")
    private static Map<Item, Double> priceAdjMap = Collections.synchronizedMap(new HashMap<Item, Double>());

    public static Map<Item, Double> getPriceAdjMap() {
        return priceAdjMap;
    }
}
