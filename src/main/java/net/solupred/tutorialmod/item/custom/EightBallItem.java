package net.solupred.tutorialmod.item.custom;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EightBallItem extends Item {
    private int uses = 0;
    private int total = 0;
    public EightBallItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient && hand == Hand.MAIN_HAND){
            if(!Screen.hasShiftDown()) {
                outputRandomNumner(user);
                user.getItemCooldownManager().set(this, 20);
                uses++;
            } else {
                user.sendMessage(Text.literal("You have used the eight ball " + uses + " times and the total is " + total + " (average: " + (total / uses) + ")"));
                user.getItemCooldownManager().set(this, 5);
            }
        }

        return super.use(world, user, hand);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(Screen.hasShiftDown()){
            tooltip.add(Text.literal("Right click to get a random number!").formatted(Formatting.AQUA));
        } else {
            tooltip.add(Text.literal("Press shift for more info!").formatted(Formatting.YELLOW));
        }

        super.appendTooltip(stack, world, tooltip, context);
    }

    private void outputRandomNumner(PlayerEntity player){
        int number = getRandomNumber();
        player.sendMessage(Text.literal("Your number is: " + number));
        total += number;
    }

    private int getRandomNumber(){
        return Random.createLocal().nextInt(10);
    }
}
