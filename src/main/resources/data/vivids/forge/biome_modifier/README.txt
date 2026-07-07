Este biome modifier hace que el Vivid pueda aparecer de forma natural
en TODO el Overworld, con weight muy bajo (1) para que sea raro.

APROXIMACION, no literal: pediste que solo aparezca en aldeas y
campamentos Vivid especificos. Hacerlo literalmente exclusivo de
aldeas requeriria integrar el spawn dentro de las piezas de estructura
de aldea (jigsaw), lo cual necesita archivos .nbt de estructura que no
puedo generar (ver structures/README.txt). Este biome modifier es la
aproximacion data-driven mas cercana que SI es 100% real y funcional
sin necesitar arte 3D: rareza real, pero no atada geograficamente a
una aldea.

Para acotarlo mas (ej: "solo en biomas de pradera/aldea"), cambia
"#minecraft:is_overworld" por un tag mas especifico como
"#minecraft:is_village" (si existe) o una lista explicita de biomas:
["minecraft:plains", "minecraft:savanna", "minecraft:desert", ...]
