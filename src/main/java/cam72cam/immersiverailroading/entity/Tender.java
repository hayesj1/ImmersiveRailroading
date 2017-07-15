package cam72cam.immersiverailroading.entity;

import cam72cam.immersiverailroading.library.GuiTypes;
import net.minecraft.world.World;
import net.minecraftforge.fluids.*;
import net.minecraftforge.fluids.capability.IFluidHandler;

public abstract class Tender extends FreightTank implements IFluidHandler {

	public Tender(World world) {
		super(world, new Fluid[]{FluidRegistry.WATER});
	}
	
	@Override
	public int getInventorySize() {
		return 16;
	}
	
	@Override
	public GuiTypes guiType() {
		return GuiTypes.TENDER;
	}
}