package com.vivids.mod.world;

/**
 * Spawn de Vivids en aldeas y campamentos.
 * ESTADO: ESQUELETO DOCUMENTADO.
 *
 * Insertar 1-2 Vivids por aldea vanilla requiere anadir una
 * "processor list" (StructureProcessor) al pool de jigsaw de
 * minecraft:village/*, o usar un data pack que agregue estructuras
 * adicionales al pool existente. Esto NO se puede resolver solo con
 * codigo Java: necesita archivos de datapack en
 * data/minecraft/worldgen/template_pool/village/... apuntando a una
 * nueva pieza de estructura nuestra (una casita Vivid) definida en
 * data/vivids/structures/.
 *
 * TODO:
 *  1) Crear la estructura NBT de la "casa Vivid" (con editor in-game
 *     con structure block) y guardarla en
 *     data/vivids/structures/vivid_house.nbt
 *  2) Anadir un processor_list en
 *     data/vivids/worldgen/processor_list/vivid_house.json
 *  3) Registrar la pieza en el pool de aldea correspondiente via
 *     data/minecraft/worldgen/template_pool/village/plains/houses.json
 *     (merge, no reemplazo).
 *  4) Poner MobSpawnSettings con 1-2 VIVID de weight bajo dentro de esa
 *     pieza de estructura.
 *
 * Para los "Campamentos Vivid" (estructura independiente en el Overworld,
 * 3-10 casas con cofres/fogatas/mesas): se debe crear como una Structure
 * completa propia (StructureType + StructurePiece + biome tag de spawn),
 * ver data/vivids/worldgen/ como carpeta ya preparada para esos archivos.
 */
public class VillageSpawns {
    // Intencionalmente vacio: ver documentacion de la clase.
}
