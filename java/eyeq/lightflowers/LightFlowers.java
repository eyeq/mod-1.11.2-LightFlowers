package eyeq.lightflowers;

import com.google.common.collect.Lists;
import eyeq.lightflowers.item.ItemBlockLight;
import eyeq.util.block.UBlockDummy;
import eyeq.util.client.renderer.ResourceLocationFactory;
import eyeq.util.client.renderer.block.statemap.StateMapper;
import eyeq.util.client.renderer.block.statemap.StateMapperNormal;
import eyeq.util.client.resource.ULanguageCreator;
import eyeq.util.client.resource.lang.LanguageResourceManager;
import eyeq.util.creativetab.UCreativeTab;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.File;

import static eyeq.lightflowers.LightFlowers.MOD_ID;
import static net.minecraft.block.BlockLeaves.CHECK_DECAY;

@Mod(modid = MOD_ID, version = "1.0", dependencies = "after:eyeq_util")
@Mod.EventBusSubscriber
public class LightFlowers {
    public static final String MOD_ID = "eyeq_lightflowers";

    @Mod.Instance(MOD_ID)
    public static LightFlowers instance;

    private static final ResourceLocationFactory resource = new ResourceLocationFactory(MOD_ID);

    public static final CreativeTabs TAB_LIGHT_FLOWERS = new UCreativeTab("LightFlowers", () -> new ItemStack(Blocks.DOUBLE_PLANT, 1, BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta()));

    public static UBlockDummy lightLeaves;
    public static UBlockDummy lightLeaves2;
    public static UBlockDummy lightSapling;
    public static UBlockDummy lightTallgrass;
    public static UBlockDummy lightDeadbush;
    public static UBlockDummy lightYellowFlower;
    public static UBlockDummy lightRedFlower;
    public static UBlockDummy lightDoublePlant;
    public static UBlockDummy lightWaterlily;
    public static UBlockDummy lightBrownMushroom;
    public static UBlockDummy lightRedMushroom;
    public static UBlockDummy lightVine;
    public static UBlockDummy lightCactus;
    public static UBlockDummy lightReeds;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        addRecipes();
        if(event.getSide().isServer()) {
            return;
        }
        renderBlockModels();
        renderItemModels();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(event.getSide().isServer()) {
            return;
        }
        registerBlockColors();
        registerItemColors();
    }

    @SubscribeEvent
    protected static void registerBlocks(RegistryEvent.Register<Block> event) {
        lightLeaves = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.LEAVES;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightLeaves").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightLeaves2 = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.LEAVES2;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightLeaves").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightSapling = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.SAPLING;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightSapling").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightTallgrass = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.TALLGRASS;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightTallgrass").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightDeadbush = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.DEADBUSH;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightDeadbush").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightYellowFlower = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.YELLOW_FLOWER;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightYellowFlower").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightRedFlower = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.RED_FLOWER;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightRedFlower").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightDoublePlant = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.DOUBLE_PLANT;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightDoublePlant").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightWaterlily = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.WATERLILY;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightWaterlily").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightBrownMushroom = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.BROWN_MUSHROOM;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightMushroom").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightRedMushroom = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.RED_MUSHROOM;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightMushroom").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightVine = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.VINE;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightVine").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightCactus = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.CACTUS;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightCactus").setCreativeTab(TAB_LIGHT_FLOWERS);
        lightReeds = (UBlockDummy) new UBlockDummy(Material.GLASS) {
            @Override
            public Block getOriginalBlock() {
                return Blocks.REEDS;
            }
        }.setLightLevel(1.0F).setUnlocalizedName("lightReeds").setCreativeTab(TAB_LIGHT_FLOWERS);

        GameRegistry.register(lightLeaves, resource.createResourceLocation("light_leaves"));
        GameRegistry.register(lightLeaves2, resource.createResourceLocation("light_leaves2"));
        GameRegistry.register(lightSapling, resource.createResourceLocation("light_sapling"));
        GameRegistry.register(lightTallgrass, resource.createResourceLocation("light_tallgrass"));
        GameRegistry.register(lightDeadbush, resource.createResourceLocation("light_deadbush"));
        GameRegistry.register(lightYellowFlower, resource.createResourceLocation("light_yellow_flower"));
        GameRegistry.register(lightRedFlower, resource.createResourceLocation("light_red_flower"));
        GameRegistry.register(lightDoublePlant, resource.createResourceLocation("light_double_plant"));
        GameRegistry.register(lightWaterlily, resource.createResourceLocation("light_waterlily"));
        GameRegistry.register(lightBrownMushroom, resource.createResourceLocation("light_brown_mushroom"));
        GameRegistry.register(lightRedMushroom, resource.createResourceLocation("light_red_mushroom"));
        GameRegistry.register(lightVine, resource.createResourceLocation("light_vine"));
        GameRegistry.register(lightCactus, resource.createResourceLocation("light_cactus"));
        GameRegistry.register(lightReeds, resource.createResourceLocation("light_reeds"));
    }

    @SubscribeEvent
    protected static void registerItems(RegistryEvent.Register<Item> event) {
        GameRegistry.register(new ItemBlockLight(lightLeaves), lightLeaves.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightLeaves2) {
            @Override
            public int getMetadata(int damage)
            {
                return damage | 4;
            }
        }, lightLeaves2.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightSapling), lightSapling.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightTallgrass), lightTallgrass.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightDeadbush), lightDeadbush.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightYellowFlower), lightYellowFlower.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightRedFlower), lightRedFlower.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightDoublePlant), lightDoublePlant.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightWaterlily), lightWaterlily.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightBrownMushroom), lightBrownMushroom.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightRedMushroom), lightRedMushroom.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightVine), lightVine.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightCactus), lightCactus.getRegistryName());
        GameRegistry.register(new ItemBlockLight(lightReeds), lightReeds.getRegistryName());
    }

    public static void addRecipes() {
        for(int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
            if(i < 4) {
                GameRegistry.addShapelessRecipe(new ItemStack(lightLeaves, 1, i),
                        Items.GLOWSTONE_DUST, new ItemStack(Blocks.LEAVES, 1, i));
            } else {
                GameRegistry.addShapelessRecipe(new ItemStack(lightLeaves2, 1, i - 4),
                        Items.GLOWSTONE_DUST, new ItemStack(Blocks.LEAVES2, 1, i - 4));
            }
        }
        for(int i = 0; i < BlockPlanks.EnumType.values().length; i++) {
            GameRegistry.addShapelessRecipe(new ItemStack(lightSapling, 1, i),
                    Items.GLOWSTONE_DUST, new ItemStack(Blocks.SAPLING, 1, i));
        }
        for(int i = 0; i < BlockTallGrass.EnumType.values().length; i++) {
            GameRegistry.addShapelessRecipe(new ItemStack(lightTallgrass, 1, i),
                    Items.GLOWSTONE_DUST, new ItemStack(Blocks.TALLGRASS, 1, i));
        }
        GameRegistry.addShapelessRecipe(new ItemStack(lightDeadbush),
                Items.GLOWSTONE_DUST, Blocks.DEADBUSH);
        for(int i = 0; i < BlockFlower.EnumFlowerType.getTypes(BlockFlower.EnumFlowerColor.YELLOW).length; i++) {
            GameRegistry.addShapelessRecipe(new ItemStack(lightYellowFlower),
                    Items.GLOWSTONE_DUST, new ItemStack(Blocks.YELLOW_FLOWER));
        }
        for(int i = 0; i < BlockFlower.EnumFlowerType.getTypes(BlockFlower.EnumFlowerColor.RED).length; i++) {
            GameRegistry.addShapelessRecipe(new ItemStack(lightRedFlower, 1, i),
                    Items.GLOWSTONE_DUST, new ItemStack(Blocks.RED_FLOWER, 1, i));
        }
        for(int i = 0; i < BlockDoublePlant.EnumPlantType.values().length; i++) {
            GameRegistry.addShapelessRecipe(new ItemStack(lightDoublePlant, 2, i),
                    Items.GLOWSTONE_DUST, new ItemStack(Blocks.DOUBLE_PLANT, 1, i));
        }
        GameRegistry.addShapelessRecipe(new ItemStack(lightWaterlily),
                Items.GLOWSTONE_DUST, Blocks.WATERLILY);
        GameRegistry.addShapelessRecipe(new ItemStack(lightBrownMushroom),
                Items.GLOWSTONE_DUST, Blocks.BROWN_MUSHROOM);
        GameRegistry.addShapelessRecipe(new ItemStack(lightRedMushroom),
                Items.GLOWSTONE_DUST, Blocks.RED_MUSHROOM);
        GameRegistry.addShapelessRecipe(new ItemStack(lightVine),
                Items.GLOWSTONE_DUST, Blocks.VINE);
        GameRegistry.addShapelessRecipe(new ItemStack(lightCactus),
                Items.GLOWSTONE_DUST, Blocks.CACTUS);
        GameRegistry.addShapelessRecipe(new ItemStack(lightReeds),
                Items.GLOWSTONE_DUST, Items.REEDS);
    }

    @SideOnly(Side.CLIENT)
    public static void renderBlockModels() {
        // BlockModelShapes ## private void registerAllBlocks()
        ResourceLocationFactory mc = ResourceLocationFactory.mc;
        ModelLoader.setCustomStateMapper(lightLeaves, new StateMapper(mc, BlockOldLeaf.VARIANT, "_leaves", Lists.newArrayList(new IProperty[]{CHECK_DECAY, BlockLeaves.DECAYABLE})));
        ModelLoader.setCustomStateMapper(lightLeaves2, new StateMapper(mc, BlockNewLeaf.VARIANT, "_leaves", Lists.newArrayList(new IProperty[]{CHECK_DECAY, BlockLeaves.DECAYABLE})));
        ModelLoader.setCustomStateMapper(lightSapling, new StateMapper(mc, BlockSapling.TYPE, "_sapling"));
        ModelLoader.setCustomStateMapper(lightTallgrass, new StateMapper(mc, BlockTallGrass.TYPE, ""));
        ModelLoader.setCustomStateMapper(lightDeadbush, new StateMapperNormal(mc.createModelResourceLocation("dead_bush")));
        ModelLoader.setCustomStateMapper(lightYellowFlower, new StateMapper(mc, Blocks.YELLOW_FLOWER.getTypeProperty(), ""));
        ModelLoader.setCustomStateMapper(lightRedFlower, new StateMapper(mc, Blocks.RED_FLOWER.getTypeProperty(), ""));
        ModelLoader.setCustomStateMapper(lightDoublePlant, new StateMapper(mc, BlockDoublePlant.VARIANT, "", Lists.newArrayList(new IProperty[]{BlockDoublePlant.FACING})));
        ModelLoader.setCustomStateMapper(lightWaterlily, new StateMapperNormal(lightWaterlily.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(lightBrownMushroom, new StateMapperNormal(lightBrownMushroom.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(lightRedMushroom, new StateMapperNormal(lightRedMushroom.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(lightVine, new StateMapperNormal(new ModelResourceLocation(lightVine.getOriginalBlock().getRegistryName(), "east=true,north=true,south=true,up=true,west=true")));
        ModelLoader.setCustomStateMapper(lightCactus, new StateMapperNormal(lightCactus.getOriginalBlock().getRegistryName()));
        ModelLoader.setCustomStateMapper(lightReeds, new StateMapperNormal(lightReeds.getOriginalBlock().getRegistryName()));
    }

    @SideOnly(Side.CLIENT)
    public static void renderItemModels() {
        // RenderItem ## private void registerItems()
        ResourceLocationFactory mc = ResourceLocationFactory.mc;
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves), BlockPlanks.EnumType.BIRCH.getMetadata(), mc.createModelResourceLocation("birch_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves), BlockPlanks.EnumType.JUNGLE.getMetadata(), mc.createModelResourceLocation("jungle_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves), BlockPlanks.EnumType.OAK.getMetadata(), mc.createModelResourceLocation("oak_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves), BlockPlanks.EnumType.SPRUCE.getMetadata(), mc.createModelResourceLocation("spruce_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves2), 0, mc.createModelResourceLocation("acacia_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightLeaves2), 1, mc.createModelResourceLocation("dark_oak_leaves"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.ACACIA.getMetadata(), mc.createModelResourceLocation("acacia_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.BIRCH.getMetadata(), mc.createModelResourceLocation("birch_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.DARK_OAK.getMetadata(), mc.createModelResourceLocation("dark_oak_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.JUNGLE.getMetadata(), mc.createModelResourceLocation("jungle_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.OAK.getMetadata(), mc.createModelResourceLocation("oak_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightSapling), BlockPlanks.EnumType.SPRUCE.getMetadata(), mc.createModelResourceLocation("spruce_sapling"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightTallgrass), BlockTallGrass.EnumType.DEAD_BUSH.getMeta(), mc.createModelResourceLocation("dead_bush"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightTallgrass), BlockTallGrass.EnumType.FERN.getMeta(), mc.createModelResourceLocation("fern"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightTallgrass), BlockTallGrass.EnumType.GRASS.getMeta(), mc.createModelResourceLocation("tall_grass"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDeadbush), 0, mc.createModelResourceLocation("dead_bush"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightYellowFlower), BlockFlower.EnumFlowerType.DANDELION.getMeta(), mc.createModelResourceLocation("dandelion"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.ALLIUM.getMeta(), mc.createModelResourceLocation("allium"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.BLUE_ORCHID.getMeta(), mc.createModelResourceLocation("blue_orchid"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.HOUSTONIA.getMeta(), mc.createModelResourceLocation("houstonia"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.ORANGE_TULIP.getMeta(), mc.createModelResourceLocation("orange_tulip"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.OXEYE_DAISY.getMeta(), mc.createModelResourceLocation("oxeye_daisy"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.PINK_TULIP.getMeta(), mc.createModelResourceLocation("pink_tulip"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.POPPY.getMeta(), mc.createModelResourceLocation("poppy"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.RED_TULIP.getMeta(), mc.createModelResourceLocation("red_tulip"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedFlower), BlockFlower.EnumFlowerType.WHITE_TULIP.getMeta(), mc.createModelResourceLocation("white_tulip"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.FERN.getMeta(), mc.createModelResourceLocation("double_fern"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.GRASS.getMeta(), mc.createModelResourceLocation("double_grass"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.PAEONIA.getMeta(), mc.createModelResourceLocation("paeonia"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.ROSE.getMeta(), mc.createModelResourceLocation("double_rose"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.SUNFLOWER.getMeta(), mc.createModelResourceLocation("sunflower"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightDoublePlant), BlockDoublePlant.EnumPlantType.SYRINGA.getMeta(), mc.createModelResourceLocation("syringa"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightWaterlily), 0, mc.createModelResourceLocation("waterlily"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightBrownMushroom), 0, mc.createModelResourceLocation("brown_mushroom"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightRedMushroom), 0, mc.createModelResourceLocation("red_mushroom"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightVine), 0, mc.createModelResourceLocation("vine"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightCactus), 0, mc.createModelResourceLocation("cactus"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(lightReeds), 0, mc.createModelResourceLocation("reeds"));
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockColors() {
        BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();

        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> {
            BlockDoublePlant.EnumPlantType type = state.getValue(BlockDoublePlant.VARIANT);
            return world == null || pos == null || type != BlockDoublePlant.EnumPlantType.GRASS && type != BlockDoublePlant.EnumPlantType.FERN ? -1 : BiomeColorHelper.getGrassColorAtPos(world, pos);
        }, lightDoublePlant);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> {
            BlockPlanks.EnumType type = state.getValue(BlockOldLeaf.VARIANT);
            return type == BlockPlanks.EnumType.SPRUCE ? ColorizerFoliage.getFoliageColorPine() : (type == BlockPlanks.EnumType.BIRCH ? ColorizerFoliage.getFoliageColorBirch() : (world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic()));
        }, lightLeaves);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic(), lightLeaves2);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : -1, lightReeds);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColorHelper.getGrassColorAtPos(world, pos) : (state.getValue(BlockTallGrass.TYPE) == BlockTallGrass.EnumType.DEAD_BUSH ? 16777215 : ColorizerGrass.getGrassColor(0.5D, 1.0D)), lightTallgrass);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? BiomeColorHelper.getFoliageColorAtPos(world, pos) : ColorizerFoliage.getFoliageColorBasic(), lightVine);
        blockColors.registerBlockColorHandler((state, world, pos, tintIndex) -> world != null && pos != null ? 2129968 : 7455580, lightWaterlily);
    }

    @SideOnly(Side.CLIENT)
    public static void registerItemColors() {
        final BlockColors blockColors = FMLClientHandler.instance().getClient().getBlockColors();
        ItemColors itemColors = FMLClientHandler.instance().getClient().getItemColors();

        itemColors.registerItemColorHandler((itemStack, tintIndex) -> {
            BlockDoublePlant.EnumPlantType type = BlockDoublePlant.EnumPlantType.byMetadata(itemStack.getMetadata());
            return type != BlockDoublePlant.EnumPlantType.GRASS && type != BlockDoublePlant.EnumPlantType.FERN ? -1 : ColorizerGrass.getGrassColor(0.5D, 1.0D);
        }, lightDoublePlant);
        itemColors.registerItemColorHandler((itemStack, tintIndex) -> {
            IBlockState iblockstate = ((ItemBlock) itemStack.getItem()).getBlock().getStateFromMeta(itemStack.getMetadata());
            return blockColors.colorMultiplier(iblockstate, null, null, tintIndex);
        }, lightTallgrass, lightVine, lightLeaves, lightLeaves2, lightWaterlily);
    }

    public static void createFiles() {
        File project = new File("../1.11.2-LightFlowers");

        LanguageResourceManager language = new LanguageResourceManager();

        language.register(LanguageResourceManager.EN_US, TAB_LIGHT_FLOWERS, "Light Flowers");
        language.register(LanguageResourceManager.JA_JP, TAB_LIGHT_FLOWERS, "装飾用植物");

        language.register(LanguageResourceManager.EN_US, lightLeaves, "Light Leaves");
        language.register(LanguageResourceManager.JA_JP, lightLeaves, "光る葉");
        language.register(LanguageResourceManager.EN_US, lightSapling, "Light Sapling");
        language.register(LanguageResourceManager.JA_JP, lightSapling, "光る苗木");
        language.register(LanguageResourceManager.EN_US, lightTallgrass, "Light Grass");
        language.register(LanguageResourceManager.JA_JP, lightTallgrass, "光る枯れ木");
        language.register(LanguageResourceManager.EN_US, lightDeadbush, "Light Dead Bush");
        language.register(LanguageResourceManager.JA_JP, lightDeadbush, "光る草");
        language.register(LanguageResourceManager.EN_US, lightYellowFlower, "Light Flower");
        language.register(LanguageResourceManager.JA_JP, lightYellowFlower, "光る花");
        language.register(LanguageResourceManager.EN_US, lightRedFlower, "Light Flower");
        language.register(LanguageResourceManager.JA_JP, lightRedFlower, "光る花");
        language.register(LanguageResourceManager.EN_US, lightDoublePlant, "Light Double Plant");
        language.register(LanguageResourceManager.JA_JP, lightDoublePlant, "光る花");
        language.register(LanguageResourceManager.EN_US, lightWaterlily, "Light Waterlily");
        language.register(LanguageResourceManager.JA_JP, lightWaterlily, "光るスイレンの葉");
        language.register(LanguageResourceManager.EN_US, lightBrownMushroom, "Light Mushroom");
        language.register(LanguageResourceManager.JA_JP, lightBrownMushroom, "光るキノコ");
        language.register(LanguageResourceManager.EN_US, lightVine, "Light Vine");
        language.register(LanguageResourceManager.JA_JP, lightVine, "光るつる");
        language.register(LanguageResourceManager.EN_US, lightCactus, "Light Cactus");
        language.register(LanguageResourceManager.JA_JP, lightCactus, "光るサボテン");
        language.register(LanguageResourceManager.EN_US, lightReeds, "Light Reeds");
        language.register(LanguageResourceManager.JA_JP, lightReeds, "光るサトウキビ");

        ULanguageCreator.createLanguage(project, MOD_ID, language);
    }
}
