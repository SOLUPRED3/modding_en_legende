package net.solupred.tutorialmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.solupred.tutorialmod.TutorialMod;

public class ModItemGroup {
    public static final ItemGroup TANZANITE = FabricItemGroupBuilder.build(
            new Identifier(TutorialMod.MOD_ID, "tanzanite"),
            () -> new ItemStack(ModItems.TANZANITE));
}
