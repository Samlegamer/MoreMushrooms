package fr.samlegamer.moremushrooms;

import fr.samlegamer.moremushrooms.registry.MMRegistry;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreMushrooms.MODID)
public class MoreMushrooms
{
	public static final String MODID = "moremushrooms";
	
	public static final DamageSource MORTAL_POISON = (new DamageSource(MODID + ":" + "mortal_poison")).bypassArmor().setMagic();

	public MoreMushrooms()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::common);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);

		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		MMRegistry.init(bus);
	}
	
	private void common(FMLCommonSetupEvent common)
	{
		
	}
	
	private void client(FMLClientSetupEvent client)
	{
		
	}
}