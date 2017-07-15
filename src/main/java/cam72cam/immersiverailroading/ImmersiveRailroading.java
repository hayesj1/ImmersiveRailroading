package cam72cam.immersiverailroading;

import org.apache.logging.log4j.Logger;

import cam72cam.immersiverailroading.blocks.BlockRail;
import cam72cam.immersiverailroading.blocks.BlockRailGag;
import cam72cam.immersiverailroading.items.ItemRail;
import cam72cam.immersiverailroading.library.TrackItems;
import cam72cam.immersiverailroading.tile.TileRail;
import cam72cam.immersiverailroading.tile.TileRailGag;
import cam72cam.immersiverailroading.tile.TileRailTESR;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@Mod(modid = ImmersiveRailroading.MODID, name="ImmersiveRailroading", version = ImmersiveRailroading.VERSION)
public class ImmersiveRailroading
{
    public static final String MODID = "immersiverailroading";
    public static final String VERSION = "0.1";
    
	@ObjectHolder(BlockRailGag.NAME)
	public static final BlockRailGag BLOCK_RAIL_GAG = new BlockRailGag();
	@ObjectHolder(BlockRail.NAME)
	public static BlockRail BLOCK_RAIL = new BlockRail();
	
	public static Logger logger;
	public static ImmersiveRailroading instance;
	
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        instance = this;
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    }
    
    @Mod.EventBusSubscriber(modid = MODID)
    public static class Registration
    {
        @SubscribeEvent
        public static void registerBlocks(RegistryEvent.Register<Block> event)
        {
    		event.getRegistry().register(BLOCK_RAIL_GAG);
    		event.getRegistry().register(BLOCK_RAIL);
        	GameRegistry.registerTileEntity(TileRailGag.class, BlockRailGag.NAME);
        	GameRegistry.registerTileEntity(TileRail.class, BlockRail.NAME);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event)
        {
        	event.getRegistry().register(new ItemRail(BLOCK_RAIL).setRegistryName(BLOCK_RAIL.getRegistryName()));
        }

        @SubscribeEvent
        public static void registerModels(ModelRegistryEvent event)
        {
            OBJLoader.INSTANCE.addDomain(MODID.toLowerCase());
            
            ClientRegistry.bindTileEntitySpecialRenderer(TileRail.class, new TileRailTESR());
            for (TrackItems item : TrackItems.values()) {
	            ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLOCK_RAIL), item.getMeta(), new ModelResourceLocation(BLOCK_RAIL.getRegistryName(), "inventory"));
            }
        }
    }
}
