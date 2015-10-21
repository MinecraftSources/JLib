package com.j0ach1mmall3.jlib.inventory;

import org.bukkit.inventory.ItemStack;

/**
 * Created by j0ach1mmall3 on 17:14 9/10/2015 using IntelliJ IDEA.
 */
public class GuiItem {
    private ItemStack item;
    private int position;

    public GuiItem(ItemStack item, int position) {
        this.item = item;
        this.position = position;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getPosition() {
        return position;
    }
}