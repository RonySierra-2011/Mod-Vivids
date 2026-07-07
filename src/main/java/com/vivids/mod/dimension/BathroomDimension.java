package com.vivids.mod.dimension;

/**
 * Dimension del Bano ("Bathroom Dimension").
 * ESTADO: ESQUELETO - NO IMPLEMENTADO EN CODIGO. Esto es intencional:
 * una dimension nueva en Forge 1.20.1 se define casi enteramente con
 * DATA PACKS (JSON), no con Java, y necesita:
 *
 *  1) data/vivids/dimension/bathroom.json
 *     -> referencia a un dimension_type + un chunk_generator (noise
 *        settings propios para que genere "azulejos", "banos gigantes", etc.)
 *  2) data/vivids/dimension_type/bathroom_type.json
 *     -> altura del mundo, ciclo dia/noche, si tiene cielo, etc.
 *  3) data/vivids/worldgen/noise_settings/bathroom.json
 *     -> como se genera el terreno (aqui es donde se define que en vez
 *        de piedra/tierra aparezcan bloques de azulejo/porcelana).
 *  4) Bloques nuevos (Azulejo, Porcelana, Espejo, Tuberia) registrados
 *     en ModBlocks (NO incluido aun) para que el generador los use.
 *  5) Estructuras (banos gigantes, templos) como Structure JSON o NBT,
 *     con StructureSet propio para controlar su rareza/dispersion.
 *  6) El portal: un bloque BathroomPortalBlock (NO incluido) similar a
 *     NetherPortalBlock, mas un PortalShape propio, mas logica de
 *     teletransporte con DimensionTransition.
 *
 * Este archivo queda como punto de entrada documentado: el siguiente
 * paso real de desarrollo es crear ModBlocks + los JSON de arriba.
 */
public class BathroomDimension {
    public static final String DIMENSION_ID = "vivids:bathroom";
}
