package fr.samlegamer.moremushrooms.registry;

import com.google.common.base.Supplier;
import fr.samlegamer.moremushrooms.MoreMushrooms;
import fr.samlegamer.moremushrooms.block.BlockBaseMushroom;
import fr.samlegamer.moremushrooms.effects.MMMobEffects;
import fr.samlegamer.moremushrooms.enums.MushroomsTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MMRegistry
{
	public static class MMBlocks
	{
		public static final DeferredRegister<Block> MM_BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, MoreMushrooms.MODID);
		
		public static final RegistryObject<Block> PARIS_MUSHROOM = add("paris_mushroom", () -> new BlockBaseMushroom(), MushroomsTypes.CLASSIC);
		public static final RegistryObject<Block> AMANITE = add("amanite", () -> new BlockBaseMushroom(), MushroomsTypes.POISONOUS);
		public static final RegistryObject<Block> AMANITE_PHALLOID = add("amanite_phalloid", () -> new BlockBaseMushroom(), MushroomsTypes.POISONOUS);
		public static final RegistryObject<Block> SATAN_BOLET = add("satan_bolet", () -> new BlockBaseMushroom(), MushroomsTypes.POISONOUS);
		public static final RegistryObject<Block> AMANITE_PANTHER = add("amanite_panther", () -> new BlockBaseMushroom(), MushroomsTypes.POISONOUS);
		public static final RegistryObject<Block> YELLOW_BOLET = add("yellow_bolet", () -> new BlockBaseMushroom(), MushroomsTypes.CLASSIC);

		public static RegistryObject<Block> add(String name, Supplier<? extends Block> supplier, MushroomsTypes types)
	    {
	        RegistryObject<Block> block = MM_BLOCK.register(name, supplier);
	        
	        switch(types)
	        {
	        case CLASSIC :
		        MMRegistry.MMItems.MM_ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(3).saturationMod(2).build())));
	        	break;
	        	
	        case HUNGERED :
		        MMRegistry.MMItems.MM_ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(1).saturationMod(0.5f)
		        		.effect(() -> new MobEffectInstance(MobEffects.HUNGER, 75 * 30, 0), 0.9f).build())));
	        	break;
	        	
	        case POISONOUS :
		        MMRegistry.MMItems.MM_ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(2).saturationMod(2)
		        		.effect(() -> new MobEffectInstance(MMEffects.MORTAL_POISON.get(), 75 * 30, 0), 1.0f).build())));
	        	break;
	        	
	        case MORTAL :
		        MMRegistry.MMItems.MM_ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(0).saturationMod(0)
		        		.effect(() -> new MobEffectInstance(MobEffects.HARM, 75 * 30, 255), 1.0f).build())));
	        	break;
	        	
	         default:
	 	        MMRegistry.MMItems.MM_ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(new FoodProperties.Builder().nutrition(0).saturationMod(0).build())));
	        	 break;
	        }
	        return block;
	    }
	}
	
	public static class MMItems
	{
		public static final DeferredRegister<Item> MM_ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, MoreMushrooms.MODID);
	}
	
	public static class MMEffects
	{
		public static final DeferredRegister<MobEffect> MM_EFF = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MoreMushrooms.MODID);

		public static final RegistryObject<MMMobEffects> MORTAL_POISON = MM_EFF.register("mortal_poison", () -> new MMMobEffects(MobEffectCategory.HARMFUL, 11946491));
	}
	
	public static void init(IEventBus bus)
	{
		MMRegistry.MMItems.MM_ITEM.register(bus);
		MMRegistry.MMBlocks.MM_BLOCK.register(bus);
		MMRegistry.MMEffects.MM_EFF.register(bus);
	}
}